package com.huike.base.tools.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

/**
 * <p class="detail">
 * 功能：该类实现了基于模板的导出 如果要导出序号，需要在excel中定义一个标识为sernums
 * 如果要替换信息，需要传入一个Map，这个map中存储着要替换信息的值，在excel中通过#来开头 要从哪一行那一列开始替换需要定义一个标识为datas
 * 如果要设定相应的样式，可以在该行使用styles完成设定，此时所有此行都使用该样式
 * 如果使用defaultStyls作为表示，表示默认样式，如果没有defaultStyles使用datas行作为默认样式
 * </p>
 * 
 * @ClassName: ExcelTemplate
 * @version V1.0
 * @date 2017年7月12日
 * @author Zerlinda Copyright 2015 tsou.com, Inc. All rights reserved
 */
public class ExcelTemplate {

	/**
	 * 数据行标识
	 */
	public final static String DATA_LINE = "datas";
	/**
	 * 默认样式标识
	 */
	public final static String DEFAULT_STYLE = "defaultStyles";
	/**
	 * 行样式标识
	 */
	public final static String STYLE = "styles";
	/**
	 * 插入序号样式标识
	 */
	public final static String SER_NUM = "sernums";
	private static ExcelTemplate et = new ExcelTemplate();

	private Workbook wb;

	private Sheet sheet;
	/**
	 * 数据的初始化列数
	 */
	private int initColIndex;
	/**
	 * 数据的初始化行数
	 */
	private int initRowIndex;
	/**
	 * 当前列数
	 */
	private int curColIndex;
	/**
	 * 当前行数
	 */
	private int curRowIndex;
	/**
	 * 当前行对象
	 */
	private Row curRow;
	/**
	 * 最后一行的数据
	 */
	private int lastRowIndex;
	/**
	 * 默认样式
	 */
	private CellStyle defaultStyle;
	/**
	 * 默认行高
	 */
	private float rowHeight;
	/**
	 * 存储某一方所对于的样式
	 */
	private Map<Integer, CellStyle> styles;
	/**
	 * 序号的列
	 */
	private int serColIndex;

	private ExcelTemplate() {
	}

	public static ExcelTemplate getInstance() {
		return et;
	}

	/**
	 * 1.读取相应的模板文档
	 */
	public ExcelTemplate readTemplateByClasspath(String path) {
		try {
			wb = WorkbookFactory.create(ExcelTemplate.class.getResourceAsStream(path));
			initTemplate();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("InvalidFormatException, please check.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("The template is not exist, please check.");
		}
		return this;
	}

	public ExcelTemplate readTemplateByPath(String path) {
		try {
			wb = WorkbookFactory.create(new File(path));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("InvalidFormatException, please check.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("The template is not exist, please check.");
		}
		return this;
	}

	private void initTemplate() {
		sheet = wb.getSheetAt(0);
		initConfigData();
		lastRowIndex = sheet.getLastRowNum();
		curRow = sheet.getRow(curColIndex);
	}

	/**
	 * 循环遍历，找到有datas字符的那个单元，记录initColIndex，initRowIndex，curColIndex，curRowIndex
	 * 调用initStyles()方法
	 * 在寻找datas字符的时候会顺便找一下sernums，如果有则记录其列号serColIndex；如果没有则调用initSer()方法，重新循环查找
	 */
	private void initConfigData() {
		boolean findData = false;
		boolean findSer = false;
		for (Row row : sheet) {
			if (findData)
				break;
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String str = c.getStringCellValue().trim();
				if (str.equals(SER_NUM)) {
					serColIndex = c.getColumnIndex();
					findSer = true;
				}
				if (str.equals(DATA_LINE)) {
					initColIndex = c.getColumnIndex();
					initRowIndex = row.getRowNum();
					curColIndex = initColIndex;
					curRowIndex = initRowIndex;
					findData = true;
					break;
				}
			}
		}
		if (!findSer) {
			initSer();
		}
		initStyles();
	}

	/**
	 * 初始化序号位置
	 */
	private void initSer() {
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String str = c.getStringCellValue().trim();
				if (str.equals(SER_NUM)) {
					serColIndex = c.getColumnIndex();
				}
			}
		}
	}

	/**
	 * 初始化样式信息
	 */
	private void initStyles() {
		styles = new HashMap<Integer, CellStyle>();
		for (Row row : sheet) {
			for (Cell c : row) {
				if (c.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String str = c.getStringCellValue().trim();
				if (str.equals(DEFAULT_STYLE)) {
					defaultStyle = c.getCellStyle();
					rowHeight = row.getHeightInPoints();
				}
				if (str.equals(STYLE) || str.equals(SER_NUM)) {
					styles.put(c.getColumnIndex(), c.getCellStyle());
				}
			}
		}
	}

	/**
	 * 新建excel并向其写数据
	 */
	public void writeToFile(String filepath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filepath);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("写入的文件不存在" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("写入数据失败" + e.getMessage());
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 实现Excel公共模板的第一步（实现了数据插入） 创建单元格，以及重载方法
	 */
	public void createCell(String value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(int value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue((int) value);
		curColIndex++;
	}

	public void createCell(Date value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(double value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(boolean value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}

	public void createCell(Calendar value) {
		Cell c = curRow.createCell(curColIndex);
		setCellStyle(c);
		c.setCellValue(value);
		curColIndex++;
	}
	
	/**
	 * 设置某个单元格的样式
	 */
	private void setCellStyle(Cell c){
		if(styles.containsKey(c.getColumnIndex())){
			c.setCellStyle(styles.get(c.getColumnIndex()));
		}else{
			c.setCellStyle(defaultStyle);
		}
	}
	
	public void createNewRow(){
		if(lastRowIndex > curRowIndex && curRowIndex != initRowIndex){
			sheet.shiftRows(curRowIndex, lastRowIndex, 1, true, true);
			lastRowIndex++;
		}
		curRow = sheet.createRow(curRowIndex);
		curRow.setHeightInPoints(rowHeight);
		curRowIndex++;
		curColIndex = initColIndex;
	}
	
	/**
	 * 插入序号，会自动找相应的序号标示的位置完成插入
	 */
	public void insertSer(){
		int index = 1;
		Row row = null;
		Cell c = null;
		for(int i = initRowIndex; i < curRowIndex; i++){
			row = sheet.getRow(i);
			c = row.createCell(serColIndex);
			setCellStyle(c);
			c.setCellValue(index++);
		}
	}
	
	/**
	 * 根据map替换相应的常量，通过Map中的值来替换#开头的值
	 */
	public void replaceFinalData(Map<String, String> datas){
		if(datas == null) return;
		for(Row row : sheet){
			for(Cell c : row){
				if(c.getCellType()!=Cell.CELL_TYPE_STRING) continue;
				String str = c.getStringCellValue().trim();
				if(str.startsWith("#")){
					if(datas.containsKey(str.substring(1))){
						c.setCellValue(datas.get(str.substring(1)));
					}
				}
			}
		}
	}
	
	@Test
	public void test01(){
		ExcelTemplate et = ExcelTemplate.getInstance()  
                .readTemplateByClasspath("/excel/default.xls");  
    et.createNewRow();  
    et.createCell("1111111");  
    et.createCell("aaaaaaaaaaaa");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("222222");  
    et.createCell("bbbbb");  
    et.createCell("b");  
    et.createCell("dbbb");  
    et.createNewRow();  
    et.createCell("3333333");  
    et.createCell("cccccc");  
    et.createCell("a1");  
    et.createCell(12333);  
    et.createNewRow();  
    et.createCell("4444444");  
    et.createCell("ddddd");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell(112);  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    et.createNewRow();  
    et.createCell("555555");  
    et.createCell("eeeeee");  
    et.createCell("a1");  
    et.createCell("a2a2");  
    Map<String,String> datas = new HashMap<String,String>();  
    datas.put("title","测试用户信息");  
    datas.put("date","2012-06-02 12:33");  
    datas.put("dep","昭通师专财务处");  
    et.replaceFinalData(datas);  
    et.insertSer();  
    et.writeToFile("d:/test/poi/test01.xls");  
	}
}
