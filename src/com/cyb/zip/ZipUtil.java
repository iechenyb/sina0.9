package com.cyb.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;

public class ZipUtil {  
	public static boolean check = false;
    
    /** 
     * 递归压缩文件夹 
     * @param srcRootDir 压缩文件夹根目录的子路径 
     * @param file 当前递归压缩的文件或目录对象 
     * @param zos 压缩文件存储对象 
     * @throws Exception 
     */  
    private static void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception  
    {  
        if (file == null)   
        {  
            return;  
        }                 
          
        //如果是文件，则直接压缩该文件  
        if (file.isFile())  
        {             
            int count, bufferLen = 1024;  
            byte data[] = new byte[bufferLen];  
              
            //获取文件相对于压缩文件夹根目录的子路径  
            String subPath = file.getAbsolutePath();  
            int index = subPath.indexOf(srcRootDir);  
            if (index != -1)   
            {  
                subPath = subPath.substring(srcRootDir.length() + File.separator.length());  
            }  
            ZipEntry entry = new ZipEntry(subPath);  
            zos.putNextEntry(entry);  
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
            while ((count = bis.read(data, 0, bufferLen)) != -1)   
            {  
                zos.write(data, 0, count);  
            }  
            bis.close();  
            zos.closeEntry();  
        }  
        //如果是目录，则压缩整个目录  
        else   
        {  
            //压缩目录中的文件或子目录  
            File[] childFileList = file.listFiles();  
            if(childFileList!=null){
	            for (int n=0; n<childFileList.length; n++)  
	            {  
	                childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());  
	                zip(srcRootDir, childFileList[n], zos);  
	            }
            }else{
            	throw new Exception("目录"+srcRootDir+"下没有需要压缩的文件！");
            }
        }  
    }  
      
    /** 
     * 对文件或文件目录进行压缩 
     * @param srcPath 要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径 
     * @param zipPath 压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹 
     * @param zipFileName 压缩文件名 
     * @throws Exception 
     */  
    public static void zip(String srcPath, String zipPath, String zipFileName) throws Exception  
    {  
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(zipPath) || StringUtils.isEmpty(zipFileName))  
        {  
            throw new Exception("路径是空！");  
        }  
        CheckedOutputStream cos = null;  
        ZipOutputStream zos = null;                       
        try  
        {  
            File srcFile = new File(srcPath);  
              
            //判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）  
            if (srcFile.isDirectory() && zipPath.indexOf(srcPath)!=-1)   
            {  
                throw new Exception("zipPath must not be the child directory of srcPath.");  
            }  
              
            //判断压缩文件保存的路径是否存在，如果不存在，则创建目录  
            File zipDir = new File(zipPath);  
            if (!zipDir.exists() || !zipDir.isDirectory())  
            {  
                zipDir.mkdirs();  
            }  
              
            //创建压缩文件保存的文件对象  
            String zipFilePath = zipPath + File.separator + zipFileName;  
            File zipFile = new File(zipFilePath);             
            if (zipFile.exists())  
            {  
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException  
               /* SecurityManager securityManager = new SecurityManager();  
                securityManager.checkDelete(zipFilePath);  */
                //删除已存在的目标文件  
                zipFile.delete();                 
            }  
              
            cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());  
            zos = new ZipOutputStream(cos);  
              
            //如果只是压缩一个文件，则需要截取该文件的父目录  
            String srcRootDir = srcPath;  
            if (srcFile.isFile())  
            {  
                int index = srcPath.lastIndexOf(File.separator);  
                if (index != -1)  
                {  
                    srcRootDir = srcPath.substring(0, index);  
                }  
            }  
            //调用递归压缩方法进行目录或文件压缩  
            zip(srcRootDir, srcFile, zos);  
            zos.flush();  
        }  
        catch (Exception e)   
        {  
            throw e;  
        }  
        finally   
        {             
            try  
            {  
                if (zos != null)  
                {  
                    zos.close();  
                }                 
            }  
            catch (Exception e)  
            {  
                e.printStackTrace();  
            }             
        }  
    }  
      
    /** 
     * 解压缩zip包 
     * @param zipFilePath zip文件的全路径 
     * @param unzipFilePath 解压后的文件保存的路径 
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含 
     */  
    @SuppressWarnings("unchecked")  
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception  
    {  
        if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath))  
        {  
            throw new Exception("ICommonResultCode.PARAMETER_IS_NULL");            
        }  
        File zipFile = new File(zipFilePath);  
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径  
        if (includeZipFileName)  
        {  
            String fileName = zipFile.getName();  
            if (StringUtils.isNotEmpty(fileName))  
            {  
                fileName = fileName.substring(0, fileName.lastIndexOf("."));  
            }  
            unzipFilePath = unzipFilePath + File.separator + fileName;  
        }  
        //创建解压缩文件保存的路径  
        File unzipFileDir = new File(unzipFilePath);  
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory())  
        {  
            unzipFileDir.mkdirs();  
        }  
          
        //开始解压  
        ZipEntry entry = null;  
        String entryFilePath = null, entryDirPath = null;  
        File entryFile = null, entryDir = null;  
        int index = 0, count = 0, bufferSize = 1024;  
        byte[] buffer = new byte[bufferSize];  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        @SuppressWarnings("resource")
		ZipFile zip = new ZipFile(zipFile);  
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zip.entries();  
        //循环对压缩包里的每一个文件进行解压       
        while(entries.hasMoreElements())  
        {  
            entry = entries.nextElement();  
            //构建压缩包中一个文件解压后保存的文件全路径  
            entryFilePath = unzipFilePath + File.separator + entry.getName();  
            //构建解压后保存的文件夹路径  
            index = entryFilePath.lastIndexOf(File.separator);  
            if (index != -1)  
            {  
                entryDirPath = entryFilePath.substring(0, index);  
            }  
            else  
            {  
                entryDirPath = "";  
            }             
            entryDir = new File(entryDirPath);  
            //如果文件夹路径不存在，则创建文件夹  
            if (!entryDir.exists() || !entryDir.isDirectory())  
            {  
                entryDir.mkdirs();  
            }  
              
            //创建解压文件  
            entryFile = new File(entryFilePath);  
            if (entryFile.exists())  
            {  
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException  
               /* SecurityManager securityManager = new SecurityManager();  
                securityManager.checkDelete(entryFilePath);  */
                //删除已存在的目标文件  
                entryFile.delete();   
            }  
              
            //写入文件  
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));  
            bis = new BufferedInputStream(zip.getInputStream(entry));  
            while ((count = bis.read(buffer, 0, bufferSize)) != -1)  
            {  
                bos.write(buffer, 0, count);  
            }  
            bos.flush();  
            bos.close();              
        }  
    }  
    /**
     * 
     * @param src  源文件
     * @param entry_ 文件压缩相对目录 com/cyb/
     * @param zipPath 压缩包的绝对路径
     * @return
     */
    public boolean addFile2Zip(String src,String entry_,String zipPath){
    	try {
			if(src==null||"".equals(src)){
				return false;
			}
			File file = new File(src);
			ZipOutputStream zipStream = getZipStrem(zipPath);
			if(file.exists()){
				if (file.isFile())  
			    {             
			        int count, bufferLen = 1024;  
			        byte data[] = new byte[bufferLen]; 
			        if(StringUtils.isEmpty(entry_)){
			        	entry_ = file.getName();
			        }
			        ZipEntry entry = new ZipEntry(entry_);  
			        zipStream.putNextEntry(entry);  
			        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
			        while ((count = bis.read(data, 0, bufferLen)) != -1)   
			        {  
			        	zipStream.write(data, 0, count);  
			        }  
			        bis.close();  
			        zipStream.closeEntry();  
			    }else{
			    	throw new Exception(src+"不是一个文件，请核查！");
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return true;
    }
    @SuppressWarnings("resource")
	public ZipOutputStream getZipStrem(String zipPath) throws FileNotFoundException{
    	ZipOutputStream zos = null;
    	if(check){
    		CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(zipPath), new CRC32());  
    		zos = new ZipOutputStream(cos);  
    	}else{
    		zos = new ZipOutputStream(new FileOutputStream(zipPath));
    	}
    	return zos;
    }
    public boolean addFile2Zip(String src,String entry_,ZipOutputStream zos){
    	try {
			if(src==null||"".equals(src)){
				throw new Exception("文件路径为空！");
			}
			File file = new File(src);
			if(file.exists()){
				if (file.isFile())  
			    {             
			        int count, bufferLen = 1024;  
			        byte data[] = new byte[bufferLen];  
			        //获取文件相对于压缩文件夹根目录的子路径  
			        ZipEntry entry = new ZipEntry(entry_);  
			        zos.putNextEntry(entry);  
			        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
			        while ((count = bis.read(data, 0, bufferLen)) != -1)   
			        {  
			            zos.write(data, 0, count);  
			        }  
			        bis.close();  
			        zos.closeEntry();  
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return true;
    }  
    public static void main(String[] args)   
    {  
    	String baseDir = System.getProperty("user.dir")+"\\src\\com\\cyb\\zip";
        String zipPath = baseDir+"\\gen\\zip";  
        String dir = baseDir+"\\gen\\files";  
        String zipFileName = "zipname.zip";  
        try  
        {  
            zip(dir, zipPath, zipFileName);  
        }   
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        System.out.println("压缩成功！路径"+zipPath+zipFileName);  
        String zipFilePath = baseDir+"\\gen\\zip\\"+zipFileName;  
        String unzipFilePath = baseDir+"\\unzip";  
        try   
        {  
            unzip(zipFilePath, unzipFilePath, true);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        System.out.println("解压成功！路径"+unzipFilePath);  
    }  
} 