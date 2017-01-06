package com.cyb.poi.usercase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelUtils {
  
    private static POIFSFileSystem fs;      
    private static HSSFWorkbook wb;      
    private static HSSFSheet sheet;      
    private static HSSFRow row;      
    /**    
     * 读取Excel表格表头的内容    
     * @param InputStream    
     * @return String 表头内容的数组    
     *     
     */     
    public static String[] readExcelTitle(InputStream is) {      
        try {      
            fs = new POIFSFileSystem(is);      
            wb = new HSSFWorkbook(fs);      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        sheet = wb.getSheetAt(0);      
        row = sheet.getRow(0);      
        //标题总列数      
        int colNum = row.getPhysicalNumberOfCells();      
        String[] title = new String[colNum];      
        for (int i=0; i<colNum; i++) {      
            title[i] = getStringCellValue(row.getCell((short) i));      
        }      
        return title;      
    }      
          
    /**    
     * 读取Excel数据内容    
     * @param InputStream    
     * @return Map 包含单元格数据内容的Map对象    
     */     
    @SuppressWarnings("deprecation")
	public static Map<String,String> readExcelContent(InputStream is) {      
        Map<String,String> content = new LinkedHashMap<String,String>();      
        try {      
            fs = new POIFSFileSystem(is);      
            wb = new HSSFWorkbook(fs);      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        sheet = wb.getSheetAt(0);      
        //得到总行数      
        int rowNum = sheet.getLastRowNum();      
        row = sheet.getRow(0);      
        int colNum = row.getPhysicalNumberOfCells();     
        try{
	        //正文内容应该从第二行开始,第一行为表头的标题      
	        for (int i = 1; i <= rowNum; i++) {      
	            row = sheet.getRow(i);      
	            int j = 0;      
	            while (j<colNum) {      
	            	String curVal = getStringCellValue(row.getCell((short) j)).trim();
	            	if(i==1&&j==0){
	            		System.out.println("标题："+curVal);
	            		content.put("jjmc",curVal.split("___")[1]);
	            		break;
	            	}else if(i==2&&j==0){//获取估值日
	            		String originGzr = getStringCellValue(row.getCell((short) 0)).trim();
	            		content.put("gzrq",originGzr.substring(originGzr.length()-10, originGzr.length()));
	            	}else if(j==0&&(curVal.equals("今日单位净值：")||curVal.equals("今日单位净值:"))){
	            		System.out.println("今日单位净值："+getStringCellValue(row.getCell((short) 1)).trim());
	            		content.put("jrdwjz",getStringCellValue(row.getCell((short) 1)).trim());
	            		break;
	            	}else if(j==0&&(curVal.equals("累计单位净值:")||curVal.equals("累计单位净值："))){
	            		System.out.println("累计单位净值："+getStringCellValue(row.getCell((short) 1)).trim());
	            		content.put("ljdwjz",getStringCellValue(row.getCell((short) 1)).trim());
	            		break;
	            	}
	                j ++;  
	            }  
	        }      
        }catch(Exception e){
        	e.printStackTrace();
        }
        return content;      
    }    
          
    /**    
     * 获取单元格数据内容为字符串类型的数据    
     * @param cell Excel单元格    
     * @return String 单元格数据内容    
     */     
    @SuppressWarnings("unused")
	public static String getStringCellValue(HSSFCell cell) {      
        String strCell = "";      
        try {
			switch (cell.getCellType()) {      
			case HSSFCell.CELL_TYPE_STRING:      
			    strCell = cell.getStringCellValue();      
			    break;      
			case HSSFCell.CELL_TYPE_NUMERIC:      
			    strCell = String.valueOf(cell.getNumericCellValue());      
			    break;      
			case HSSFCell.CELL_TYPE_BOOLEAN:      
			    strCell = String.valueOf(cell.getBooleanCellValue());      
			    break;      
			case HSSFCell.CELL_TYPE_BLANK:      
			    strCell = "";      
			    break;      
			default:      
			    strCell = "";      
			    break;      
			}      
			if (strCell.equals("") || strCell == null) {      
			    return "";      
			}      
			if (cell == null) {      
			    return "";      
			}
		} catch (Exception e) {
			return "";
		}      
        return strCell;      
    }      
          
    /**    
     * 获取单元格数据内容为日期类型的数据    
     * @param cell Excel单元格    
     * @return String 单元格数据内容    
     */     
    @SuppressWarnings("unused")
	private String getDateCellValue(HSSFCell cell) {      
        String result = "";      
        try {      
            int cellType = cell.getCellType();      
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {      
                Date date = cell.getDateCellValue();      
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)       
                + "-" + date.getDate();      
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {      
                String date = getStringCellValue(cell);      
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();      
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {      
                result = "";      
            }      
        } catch (Exception e) {      
            System.out.println("日期格式不正确!");      
            e.printStackTrace();      
        }      
        return result;      
    }      
    public void createExcel() throws IOException{}
    
    public static void displayExcel(String src){
    	 try {      
             InputStream is2 = new FileInputStream(src);      
             Map<String,String> map = ReadExcelUtils.readExcelContent(is2);      
             System.out.println(map);   
         } catch (FileNotFoundException e) {      
             System.out.println("未找到指定路径的文件!");      
             e.printStackTrace();      
         }  
    }
    public static void main(String[] args) {      
    	String path = System.getProperty("user.dir")+"/src/com/cyb/poi/dhqh.xls";
    	displayExcel(path);
    }      
}   