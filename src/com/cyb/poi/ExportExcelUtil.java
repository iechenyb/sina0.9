package com.cyb.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportExcelUtil {
 //private static Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);
 
 private HSSFWorkbook workbook = null;
 @SuppressWarnings("unused")
 private HSSFSheet sheet = null;
 
 public HSSFWorkbook getWorkbook() {
  return workbook;
 }

 public void setWorkbook(HSSFWorkbook workbook) {
  this.workbook = workbook;
 }

// public HSSFSheet getSheet() {
//  return sheet;
// }

// public void setSheet(HSSFSheet sheet) {
//  this.sheet = sheet;
// }
    
 public ExportExcelUtil(HSSFWorkbook workbook){
     this.workbook = workbook;
 }
 
 public ExportExcelUtil(HSSFWorkbook workbook, HSSFSheet sheet) {
  super();
  this.workbook = workbook;
  this.sheet = sheet;
 }
 
 /**
     * 创建通用的Excel空白行信�?
     * @param workbook 如果为空 则没有样�?
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param colNum 报表的�?�列�? (合并)
     */
 public void createExcelRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowNO, int rowHeight, int colNum) {
  createExcelRow(workbook, sheet, rowNO, -1, colNum, null, -1, null, null);
 }
 
 /**
     * 创建通用的Excel带标题行信息
     * @param workbook 如果为空 则没有样�?
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param colNum 报表的�?�列�? (合并)
     * @param fontCaption 报表行中显示的字�?
     */
 public void createExcelRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowNO, int rowHeight, int colNum, String fontCaption) {
  createExcelRow(workbook, sheet, rowNO, -1, colNum, fontCaption, -1, null, null);
 }
 
 /**
     * 创建通用的Excel行信�?
     * @param workbook 如果为空 则没有样�?
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param colNum 报表的�?�列�? (合并)
     * @param fontCaption 报表行中显示的字�?
     * @param fontSize 字体的大�? (字体大小 默认 200)
     * @param fontWeight 报表表头显示的字�?
     * @param align 字体水平位置 (center中间  right�?  left�?)
     * @param colNum 报表的列�?
     */
 @SuppressWarnings("deprecation")
 public void createExcelRow(HSSFWorkbook workbook, HSSFSheet sheet, int rowNO, int rowHeight, int colNum, String fontCaption, int fontSize, String fontWeight, String align) {
        if(colNum < 0) {
   //logger.debug(this.getClass().getName() + " --> Excel column number is null");
   colNum = 100;  
  }
  
  HSSFRow row = sheet.createRow(rowNO);  //创建第一�?
  row.setHeight((short) (rowHeight < 1 ? 300 : rowHeight));  //设置行高
  
     HSSFCell cell = row.createCell(0);//设置第一�?
  cell.setCellType(HSSFCell.ENCODING_UTF_16); //定义单元格为字符串类�?
  cell.setCellValue(new HSSFRichTextString(fontCaption));
  
  sheet.addMergedRegion(new Region(rowNO, (short) 0, rowNO, (short) (colNum - 1)));   //指定合并区域
  
  HSSFCellStyle cellStyle = createCellFontStyle(workbook, fontSize, fontWeight, align);  //设定样式
  if(cellStyle != null){
   cell.setCellStyle(cellStyle);
  }
 }
 
 /**
  * 设置报表列头
  * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param columnHeader 报表行中显示的字�?
  */
 public void createColumnHeader(HSSFSheet sheet, int rowNO, int rowHeight, String[] columnHeader) {
  createColumnHeader(sheet, rowNO, rowHeight, columnHeader, -1, null, null);
 }
 
 /**
  * 设置报表列头
  * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param columnHeader 报表行中显示的字�?
     * @param fontSize 字体的大�? (字体大小 默认 200)
  */
 public void createColumnHeader(HSSFSheet sheet, int rowNO, int rowHeight, String[] columnHeader, int fontSize) {
  createColumnHeader(sheet, rowNO, rowHeight, columnHeader, fontSize, null, null);
 } 
 
    /**
     * 设置报表列头
     * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param columnHeader 报表行中显示的字�?
     * @param fontSize 字体的大�? (字体大小 默认 200)
     * @param fontWeight 报表表头显示的字�?
     * @param align 字体水平位置 (center中间  right�?  left�?)
     */
 public void createColumnHeader(HSSFSheet sheet, int rowNO, int rowHeight, String[] columnHeader, int fontSize, String fontWeight,
   String align) {
  if(columnHeader == null || columnHeader.length < 1){
  // logger.debug(this.getClass().getName() + " --> Excel columnHeader is null");
   return ;
  }
  HSSFRow row = sheet.createRow(rowNO);
  row.setHeight((short) rowHeight);
  
  HSSFCellStyle cellStyle = createCellFontStyle(workbook, fontSize, fontWeight, align);
        if(cellStyle != null){
         // 设置单元格背景色
      cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
      cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        }
         
  HSSFCell cell = null;
  for (int i = 0; i < columnHeader.length; i++) {
   sheet.setColumnWidth(i, 20 * 256);  //设置列宽�?20个字符宽度�?�宽度参数为1/256，故乘以256
   cell = row.createCell(i);
   cell.setCellType(HSSFCell.ENCODING_UTF_16);
   if(cellStyle != null){cell.setCellStyle(cellStyle);}
   cell.setCellValue(new HSSFRichTextString(columnHeader[i]));
  }
 }
 
 /**
  * 创建数据�?
  * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param columnData 报表行中显示的数�?
     * @param maxValue Excel显示的最大上�?
  */
 public HSSFSheet createColumnData(HSSFSheet sheet, int rowNO,  String[][] columnData, int maxValue){
  maxValue = (maxValue < 1 || maxValue > 65535 ) ?  65535 : maxValue ; 
  int currRowNO = rowNO;
  for (int numNO = currRowNO; numNO < columnData.length + currRowNO; numNO++) {
    if (numNO% maxValue == 0) {
     sheet = workbook.createSheet();
     rowNO = 0;
    }
    createColumnDataDesc(sheet, numNO, rowNO, currRowNO, -1, columnData);
    rowNO++;
  }
  return sheet;
 }
 
 /**
  * 创建数据�?
  * @param sheet (创建sheet)
  * @param numNO 序列�?
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param currRowNO 初始行号
     * @param rowHeight 报表的单行行�?
     * @param columnData 报表行中显示的数�?
  */
 private void createColumnDataDesc(HSSFSheet sheet, int numNO, int rowNO, int currRowNO, int rowHeight, String[][] columnData) {
  createColumnDataDesc(sheet, numNO, rowNO, currRowNO, rowHeight, columnData, -1, null, null);
 }
 
 /**
  * 创建数据�?
  * @param sheet (创建sheet)
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param rowHeight 报表的单行行�?
     * @param columnData 报表行中显示的数�?
  * @param fontSize 字体大小 默认 200
  * @param fontWeight 字体粗细 ( 值为bold 为加�?)
  * @param align 字体水平位置 (center中间  right�?  left�?)
  * @param maxValue Excel显示的最大上�?
  */
 public HSSFSheet createColumnData(HSSFSheet sheet, int rowNO, int rowHeight, String[][] columnData, int fontSize, String fontWeight, String align, int maxValue){
  maxValue = (maxValue < 1 || maxValue > 65535 ) ?  65535 : maxValue ; 
  int currRowNO = rowNO;
  for (int numNO = currRowNO; numNO < columnData.length + currRowNO; numNO++) {
    if (numNO% maxValue == 0) {
     sheet = workbook.createSheet();
     rowNO = 0;
    }
    createColumnDataDesc(sheet, numNO, rowNO, currRowNO,  rowHeight, columnData, fontSize, fontWeight, align);
    rowNO++;
  }
  return sheet;
 }
 
 /**
  * 创建数据�?
  * @param sheet (创建sheet)
  * @param numNO 序列�?
     * @param rowNO 报表的单行行�?(创建第几�?)
     * @param currRowNO 初始行号
     * @param rowHeight 报表的单行行�?
     * @param columnData 报表行中显示的数�?
     * @param fontSize 字体的大�? (字体大小 默认 200)
     * @param fontWeight 报表表头显示的字�?
     * @param align 字体水平位置 (center中间  right�?  left�?)
  */
 private void createColumnDataDesc(HSSFSheet sheet, int numNO, int rowNO, int currRowNO, int rowHeight, String[][] columnData, int fontSize, String fontWeight,
   String align) {
  if(columnData == null || columnData.length < 1){
   //logger.debug(this.getClass().getName() + " --> Excel columnData is null");
//   return ;
  }
  HSSFRow row = sheet.createRow(rowNO);
  row.setHeight((short) rowHeight);
  
  HSSFCellStyle cellStyle =null;// createCellFontStyle(workbook, fontSize, fontWeight, align);
  if(cellStyle != null){
   cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对�? 
  } else {
   cellStyle = workbook.createCellStyle();
   cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION); // 指定单元格居中对�?
  }
  HSSFCell cell = null;
  for (int i = 0; i < columnData[numNO - currRowNO].length; i++) {
   sheet.setColumnWidth(i, 20 * 256);  //设置列宽�?20个字符宽度�?�宽度参数为1/256，故乘以256
   cell = row.createCell(i);
   cell.setCellType(HSSFCell.ENCODING_UTF_16);
   if(cellStyle != null){cell.setCellStyle(cellStyle);}
   cell.setCellValue(new HSSFRichTextString(columnData[numNO - currRowNO][i]));
  }
 }
 
    /**
     * 创建内容单元�? 
     * @param workbook  HSSFWorkbook
     * @param row  HSSFRow
     * @param columnNumber  short型的列索�?
     * @param alignType  对齐方式  (默认居中对齐,如果 alignType=true 则左对齐)
     * @param value  列�??
     */
 @SuppressWarnings("deprecation")
 public void cteateDataCell(HSSFWorkbook workbook, HSSFRow row, int columnNumber, boolean alignType, String value) {
  HSSFCell cell = row.createCell(((short) columnNumber));
  cell.setCellType(HSSFCell.ENCODING_UTF_16);
  cell.setCellValue(new HSSFRichTextString(value));
  
  HSSFCellStyle cellstyle = workbook.createCellStyle();
  short align = HSSFCellStyle.ALIGN_CENTER_SELECTION; 
  if(alignType){
   align = HSSFCellStyle.ALIGN_LEFT; 
  }
  cellstyle.setAlignment(align); // 指定单元格居中对�?
  cell.setCellStyle(cellstyle);
 }
 
 /**
     * 创建通用的Excel�?后一行的信息 (创建合计�? (�?后一�?))
     * @param workbook 如果为空 则没有样�?
     * @param sheet 
     * @param colNum 报表的�?�列�? (合并)
     * @param fontCaption 报表行中显示的字�?
     * @param fontSize 字体的大�? (字体大小 默认 200)
     * @param fontWeight 报表表头显示的字�?
     * @param align 字体水平位置 (center中间  right�?  left�?)
     * @param colNum 报表的列�? (�?要合并到的列索引)
     * 
     */
 @SuppressWarnings("deprecation")
 public void createSummaryRow(HSSFWorkbook workbook, HSSFSheet sheet,
   int colNum, String fontCaption, int fontSize, String fontWeight,
   String align) {

  HSSFCellStyle cellStyle = createCellFontStyle(workbook, fontSize, fontWeight, align);

  HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
  HSSFCell sumCell = lastRow.createCell(0);

  sumCell.setCellValue(new HSSFRichTextString(fontCaption));
  if(cellStyle != null){sumCell.setCellStyle(cellStyle);}
  sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0, sheet.getLastRowNum(), (short) (colNum - 1)));// 指定合并区域
 }
 
 /**
  * 设置字体样式   (字体为宋�? ，上下居中对齐，可设置左右对齐，字体粗细，字体大�? )
  * @param workbook 如果为空 则没有样�?
  * @param fontSize 字体大小 默认 200
  * @param fontWeight 字体粗细 ( 值为bold 为加�?)
  * @param align 字体水平位置 (center中间  right�?  left�?)
  */
 public HSSFCellStyle createCellFontStyle(HSSFWorkbook workbook, int fontSize, String fontWeight, String align){
  if(workbook == null){ 
   //logger.debug(this.getClass().getName() + " --> Excel HSSFWorkbook FontStyle is not set");
   return null;
  }
 
        HSSFCellStyle cellStyle = workbook.createCellStyle();
       
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对�?
  if(align != null && align.equalsIgnoreCase("left")){
   cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 指定单元格居中对�?
  }
  if(align != null && align.equalsIgnoreCase("right")){
   cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 指定单元格居中对�?
  }
  
  cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对�?
  cellStyle.setWrapText(true);// 指定单元格自动换�?
  
  // 单元格字�?
  HSSFFont font = workbook.createFont();
  if(fontWeight != null && fontWeight.equalsIgnoreCase("normal")){
   font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
  } else{
   font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
  }
  
  font.setFontName("宋体");
  font.setFontHeight((short) (fontSize < 1 ? 200 : fontSize) );
  cellStyle.setFont(font);
  
    // 设置字体
//        HSSFFont font = workbook.createFont();
//        font.setFontHeightInPoints((short) 20); //字体高度
//        font.setColor(HSSFFont.COLOR_RED); //字体颜色
//        font.setFontName("黑体"); //字体
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //宽度
//        font.setItalic(true); //是否使用斜体
//        font.setStrikeout(true); //是否使用划线
//  // 添加单元格注�?
//        // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
//         HSSFPatriarch patr = sheet.createDrawingPatriarch();
//        // 定义注释的大小和位置,详见文档
//         HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 5));
//        // 设置注释内容
//         comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//        // 设置注释作�??. 当鼠标移动到单元格上是可以在状�?�栏中看到该内容.
//         comment.setAuthor("Xuys.");

  return cellStyle;
 }
 
 /**
  * 导出EXCEL文件
  * @param fileName 文件名称
  * 测试程序
  * 
  * //  private static HSSFWorkbook workbook = new HSSFWorkbook();
//      private static HSSFSheet sheet = workbook.createSheet();
  HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
  ExportExcelUtil eeu = new ExportExcelUtil(workbook, sheet);

  String[] strArr = new String[] { "序号", "姓名", "�? �?", "出生年月", "民族", "籍贯", "备注" };
  int colNum = strArr.length;

  int rowNO = 0;
  //1. titleCaption
  eeu.createExcelRow(workbook, sheet, rowNO, -1, colNum, "统计报表"); // , 250, "bold", "center"
  //2.
  rowNO++;
  eeu.createExcelRow(workbook, sheet, rowNO, 200, colNum,
    " �? �? �?: 赵小�?       �? �? �? �?: " + new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), 180, "normal", "right");
  //3.columnTitleHeader
  rowNO++;
  eeu.createColumnHeader(sheet, rowNO, 300, strArr);
  
  //4.数据�?     循环创建中间的单元格的各项的�?
  rowNO++;
  String[][] columnData = new String[][]{{ "1", "zhangsan", "�?", "1985-10-06 21:00:00", "汉族", "西安", "学生" },
    { "2", "猪猪", "�?", "出生年月", "民族", "籍贯", "备注" },
    { "3", "明明", "�?", "1980-07-08", "汉族", "西安", "学生" },
    { "4", "光光", "�?", "1985-06-30", "汉族", "西安", "学生" },
    { "5", "先民", "�?", "1987-06-06", "汉族", "西安", "学生" },
    { "6", "数据", "�?", "1985-04-06", "汉族", "西安", "学生" },
    { "7", "历史", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "8", "妩媚", "�?", "1990-10-06", "汉族", "西安", "学生" },
    { "9", "李斯", "�?", "1985-06-20", "汉族", "西安", "学生" },
    { "10", "�?�?", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "11", "绅士", "�?", "1985-01-06", "汉族", "西安", "学生" },
    { "12", "先知", "�?", "1984-06-06", "汉族", "西安", "学生" },
    { "13", "精明", "�?", "1985-06-12", "汉族", "西安", "学生" },
    { "14", "科技", "�?", "1972-02-03", "汉族", "西安", "学生" },
    { "15", "软件", "�?", "1985-02-06", "汉族", "西安", "学生" },
    { "16", "世道", "�?", "1999-03-06", "汉族", "西安", "学生" },
    { "17", "明了", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "18", "小明", "�?", "1984-06-09", "汉族", "西安", "学生" },
    { "19", "高娟", "�?", "1985-03-06", "汉族", "西安", "学生" },
    { "20", "小莉", "�?", "1987-12-25", "汉族", "西安", "学生" }} ;
  sheet = eeu.createColumnData(sheet, rowNO,  columnData, 7);
        eeu.createSummaryRow(workbook, sheet, colNum, "合计�?" + columnData.length, 180, "normal", "right");
  eeu.exportExcel("f://Test2011-07-29//test.xls");
  */
 public void exportExcel(String fileName) {
  OutputStream os = null;
  try{
   os = new FileOutputStream(new File(fileName));
   workbook.write(os);
   os.close();
  }catch(Exception e){
   //logger.debug(this.getClass().getName() + " --> export Excel file error :" + e.getMessage());
  }
 }

  /** 
   *   利用模板导出Excel
   *   @param   inputFile   输入模板文件路径 
   *   @param   outputFile   输入文件存放于服务器路径 
   *   @param   dataList   待导出数�? 
   *   @throws   Exception 
   *   @roseuid: 
   */ 
 @SuppressWarnings("deprecation")
 public void exportExcelFile(String inputFileName, String outputFileName,
   List<?> dataList) throws Exception {
  // 用模板文件构造poi
  POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFileName));
  // 创建模板工作�?
  HSSFWorkbook templatewb = new HSSFWorkbook(fs);
  // 直接取模板第�?个sheet对象
  HSSFSheet templateSheet = templatewb.getSheetAt(1);
  if (dataList.size()% 65535 == 0) {
   templateSheet = templatewb.createSheet();
  }
  // 得到模板的第�?个sheet的第�?行对�? 为了得到模板样式
  HSSFRow templateRow = templateSheet.getRow(0);

  // HSSFSheet timplateSheet = templatewb.getSheetAt(1);
  // 取得Excel文件的�?�列�?
  int columns = templateSheet.getRow((short) 0)
    .getPhysicalNumberOfCells();
  System.out.println("columns   is   :   " + columns);
  // 创建样式数组
  HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

  // �?次�?�创建所有列的样式放在数组里
  for (int s = 0; s < columns; s++) {
   // 得到数组实例
   styleArray[s] = templatewb.createCellStyle();
  }
  // 循环对每�?个单元格进行赋�??
  // 定位�?
  for (int rowId = 1; rowId < dataList.size(); rowId++) {
   // 依次取第rowId行数�? 每一个数据是valueList
   List<?> valueList = (List<?>) dataList.get(rowId - 1);
   // 定位�?
   for (int columnId = 0; columnId < columns; columnId++) {
    // 依次取出对应与colunmId列的�?
    // 每一个单元格的�??
    String dataValue = (String) valueList.get(columnId);
    // 取出colunmId列的的style
    // 模板每一列的样式
    HSSFCellStyle style = styleArray[columnId];
    // 取模板第colunmId列的单元格对�?
    // 模板单元格对�?
    HSSFCell templateCell = templateRow.getCell((short) columnId);
    // 创建�?个新的rowId�? 行对�?
    // 新建的行对象
    HSSFRow hssfRow = templateSheet.createRow(rowId);
    // 创建新的rowId�? columnId�? 单元格对�?
    // 新建的单元格对象
    HSSFCell cell = hssfRow.createCell((short) columnId);
    // 如果对应的模板单元格 样式为非锁定
    if (templateCell.getCellStyle().getLocked() == false) {
     // 设置此列style为非锁定
     style.setLocked(false);
     // 设置到新的单元格�?
     cell.setCellStyle(style);
    }
    // 否则样式为锁�?
    else {
     // 设置此列style为锁�?
     style.setLocked(true);
     // 设置到新单元格上
     cell.setCellStyle(style);
    }
    // 设置编码
    // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
    // Debug.println( "dataValue   :   " + dataValue);
    // 设置�? 统一为String
    cell.setCellValue(dataValue);
   }
  }
  // 设置输入�?
  FileOutputStream fOut = new FileOutputStream(outputFileName);
  // 将模板的内容写到输出文件�?
  templatewb.write(fOut);
  fOut.flush();

  // 操作结束，关闭文�?
  fOut.close();

 }
 
 public static void main(String[] args) {
//  private static HSSFWorkbook workbook = new HSSFWorkbook();
//      private static HSSFSheet sheet = workbook.createSheet();
  HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
  ExportExcelUtil eeu = new ExportExcelUtil(workbook, sheet);

  String[] strArr = new String[] { "序号", "姓名", "�? �?", "出生年月", "民族", "籍贯", "备注" };
  int colNum = strArr.length;
  
  int rowNO = 0;
  //1. titleCaption
  eeu.createExcelRow(workbook, sheet, rowNO, -1, colNum, "统计报表"); // , 250, "bold", "center"
  //2.
  rowNO++;
  eeu.createExcelRow(workbook, sheet, rowNO, 200, colNum,
    " �? �? �?: 赵小�?       �? �? �? �?: " + new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), 180, "normal", "right");
  //3.columnTitleHeader
  rowNO++;
  eeu.createColumnHeader(sheet, rowNO, 300, strArr);
  
  //4.数据�?     循环创建中间的单元格的各项的�?
  rowNO++;
  String[][] columnData = new String[][]{{ "1", "zhangsan", "�?", "1985-10-06 21:00:00", "汉族", "西安", "学生" },
    { "2", "猪猪", "�?", "出生年月", "民族", "籍贯", "备注" },
    { "3", "明明", "�?", "1980-07-08", "汉族", "西安", "学生" },
    { "4", "光光", "�?", "1985-06-30", "汉族", "西安", "学生" },
    { "5", "先民", "�?", "1987-06-06", "汉族", "西安", "学生" },
    { "6", "数据", "�?", "1985-04-06", "汉族", "西安", "学生" },
    { "7", "历史", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "8", "妩媚", "�?", "1990-10-06", "汉族", "西安", "学生" },
    { "9", "李斯", "�?", "1985-06-20", "汉族", "西安", "学生" },
    { "10", "�?�?", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "11", "绅士", "�?", "1985-01-06", "汉族", "西安", "学生" },
    { "12", "先知", "�?", "1984-06-06", "汉族", "西安", "学生" },
    { "13", "精明", "�?", "1985-06-12", "汉族", "西安", "学生" },
    { "14", "科技", "�?", "1972-02-03", "汉族", "西安", "学生" },
    { "15", "软件", "�?", "1985-02-06", "汉族", "西安", "学生" },
    { "16", "世道", "�?", "1999-03-06", "汉族", "西安", "学生" },
    { "17", "明了", "�?", "1985-06-06", "汉族", "西安", "学生" },
    { "18", "小明", "�?", "1984-06-09", "汉族", "西安", "学生" },
    { "19", "高娟", "�?", "1985-03-06", "汉族", "西安", "学生" },
    { "20", "小莉", "�?", "1987-12-25", "汉族", "西安", "学生" }} ;
  sheet = eeu.createColumnData(sheet, rowNO,  columnData, 65530);
  eeu.createSummaryRow(workbook, sheet, colNum, "合计�?" + columnData.length, 180, "normal", "right");
  eeu.exportExcel(System.getProperty("user.dir")+"//test.xls");
  //logger.info("success!");
  /* 
  在用java 编写生成报表时发现了个问题，将行，列隐藏，将列隐藏很�?单用
    this.sheet.setColumnHidden((short)12, true);将第13列隐藏注意excel的第�?列用0表示

  隐藏行：

  HSSFRow row     = sheet.getRow(8); 
      row.setZeroHeight(true);

  将第8行隐藏就是将他的高度设为0也等同为隐藏
*/
 }

 
 
}

 
