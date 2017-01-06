package com.cyb.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;

public class FileUtils {
    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static StringBuffer readFileByLines(String path) {
    	StringBuffer content = new StringBuffer("");
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
            //new FileReader(file)
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                content.append(tempString.trim()+",");
               // line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }

    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copyFile(String src, String dest) throws IOException {
        try {
          FileInputStream in = new FileInputStream(src);
          File file = new File(dest);
          if (!file.exists()) {
            file.createNewFile();
          }
          FileOutputStream out = new FileOutputStream(file);

          byte[] buffer = new byte[1024];
          int c;
          while ((c = in.read(buffer)) != -1)
          {
            for (int i = 0; i < c; i++)
              out.write(buffer[i]);
          }
          in.close();
          out.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
      }

      public static void copyFile(File src, String dest) throws IOException {
        try {
          FileInputStream in = new FileInputStream(src);
          File file = new File(dest);
          if (!file.exists())
            file.createNewFile();
          FileOutputStream out = new FileOutputStream(file);

          byte[] buffer = new byte[1024];
          int c;
          while ((c = in.read(buffer)) != -1)
          {
            for (int i = 0; i < c; i++)
              out.write(buffer[i]);
          }
          in.close();
          out.close();
        } catch (Exception e) {
        e.printStackTrace();
        }
      }

      public static void copyFileByStream(InputStream src, String dest) {
        try {
          File file = new File(dest);
          if (!file.exists())
            file.createNewFile();
          FileOutputStream out = new FileOutputStream(dest);

          byte[] buffer = new byte[1024];
          int c;
          while ((c = src.read(buffer)) != -1)
          {
            for (int i = 0; i < c; i++)
              out.write(buffer[i]);
          }
          src.close();
          out.close();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace();
        }
      }
      public static void appendString2File(String content,String dest) {
  		FileOutputStream fop = null;
  		File file;
//  		StringBuffer content = new StringBuffer("This is the text content 中文"+"\n");
//  		content.append("This is the text content 中文是");
  		try {
  			file = new File(dest);
  			//如果第二个参数为true，则将字节写入文件末尾处，而不是写入文件开始处
  			fop = new FileOutputStream(file,true);
  			if (!file.exists()) {
  				file.createNewFile();
  			}
  			byte[] contentInBytes = content.toString().getBytes();
  			fop.write(contentInBytes);
  			fop.flush();
  			fop.close();
  			System.out.println("字符串内容写入到文件完成！");
  		} catch (IOException e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				if (fop != null) {
  					fop.close();
  				}
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
  	}
      public static void overideString2File(String content,String dest) {
    		FileOutputStream fop = null;
    		File file;
//    		StringBuffer content = new StringBuffer("This is the text content 中文"+"\n");
//    		content.append("This is the text content 中文是");
    		try {
    			file = new File(dest);
    			//如果第二个参数为true，则将字节写入文件末尾处，而不是写入文件开始处
    			fop = new FileOutputStream(file,false);
    			if (!file.exists()) {
    				file.createNewFile();
    			}else{
    				file.delete();
    				file.createNewFile();
    			}
    			byte[] contentInBytes = content.toString().getBytes();
    			fop.write(contentInBytes);
    			fop.flush();
    			fop.close();
    			System.out.println("字符串内容写入到文件完成！");
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if (fop != null) {
    					fop.close();
    				}
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    public static void appendString2File1(String content,String to){
    	FileWriter fw = null;
    	try {
	    	//如果文件存在，则追加内容；如果文件不存在，则创建文件
	    	File file = new File(to);
	    	fw = new FileWriter(file, true);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	PrintWriter pw = new PrintWriter(fw);
    	pw.println(content);//写一行
    	pw.flush();
    	try {
	    	fw.flush();
	    	pw.close();
	    	fw.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    public static void appendString2File2(String content,String to){
    	BufferedWriter out = null;
    	try {
    		File file = new File(to);
	    	out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
	    	out.write(content+"\n");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
	    	try {
	    	out.close();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
    	}
    }
    public static void appendString2File3(String content,String to){
    	try {
	    		// 打开一个随机访问文件流，按读写方式
	    		RandomAccessFile randomFile = new RandomAccessFile(to, "rw");
	    		// 文件长度，字节数
	    		long fileLength = randomFile.length();
	    		// 将写文件指针移到文件尾。
	    		randomFile.seek(fileLength);
	    		//String s=new String(content.getBytes("gb2312"), "utf-8");//编码转换    
	    		randomFile.writeChars(content+"\n");
	    		randomFile.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }
    public static void genFile(String path){
    	try {
			File  file = new File(path);
			if(!file.exists()){
				if(file.isFile()){
					file.createNewFile();
				}else if(file.isDirectory()){
					file.mkdirs();
				}else{
					file.mkdirs();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
        String to = "d:\\file\\industry.txt";
        appendString2File1("111汉字1", to);
        appendString2File2("1112汉字", to);
        appendString2File3("1113汉字", to);       
    }
}