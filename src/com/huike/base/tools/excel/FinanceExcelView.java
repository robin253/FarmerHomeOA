package com.huike.base.tools.excel;

import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author Kent.Wang
 * @Date 2017/6/28
 */
public class FinanceExcelView extends ExcelView {

	Logger log = LoggerFactory.getLogger(FinanceExcelView.class);
	
    @Override
    public void setRow(Sheet sheet, Map<String, Object> map) {
    	
    	// create title row
    	Row title = sheet.createRow(0);
    	title.createCell(0).setCellValue(map.get("title")==null?"":map.get("title").toString());
    	title.setHeightInPoints(40);
        /* 
         * 设定合并单元格区域范围 
         *  firstRow  0-based 
         *  lastRow   0-based 
         *  firstCol  0-based 
         *  lastCol   0-based 
         */  
        CellRangeAddress craTitle=new CellRangeAddress(0, 0, 0, 5);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(craTitle);  
        
    	// create unit row
    	Row unit = sheet.createRow(1);
    	unit.createCell(0).setCellValue("单位：元");
    	
        /* 
         * 设定合并单元格区域范围 
         *  firstRow  0-based 
         *  lastRow   0-based 
         *  firstCol  0-based 
         *  lastCol   0-based 
         */  
        CellRangeAddress craUnit=new CellRangeAddress(1, 1, 0, 5);        
        //在sheet里增加合并单元格  
        sheet.addMergedRegion(craUnit);
        
    	Row row_2 = sheet.createRow(2);
    	createRow(row_2, new String[]{"资产","年初数","期末数","负债和净资产","年初数","期末数"});
    	
    	Row row_3 = sheet.createRow(3);
    	createRow(row_3, new String[]{"流动资产：","","","流动负债：","",""});
    	
    	Row row_4 = sheet.createRow(4);
    	createRow(row_4, new String[]{"货币资金",map.get("hbzj_begin")==null?"":map.get("hbzj_begin").toString(),map.get("hbzj_end")==null?"":map.get("hbzj_end").toString(),"短期借款",map.get("dqjd_begin")==null?"":map.get("dqjd_begin").toString(),map.get("dqjd_end")==null?"":map.get("dqjd_end").toString()});
    	
    	Row row_5 = sheet.createRow(5);
    	createRow(row_5, new String[]{"短期投资",map.get("dqtz_begin")==null?"":map.get("dqtz_begin").toString(),map.get("dqtz_end")==null?"":map.get("dqtz_end").toString(),"应付款项",map.get("yfkx_begin")==null?"":map.get("yfkx_begin").toString(),map.get("yfkx_end")==null?"":map.get("yfkx_end").toString()});
    	
    	Row row_6 = sheet.createRow(6);
    	createRow(row_6, new String[]{"应收款项",map.get("yskx_begin")==null?"":map.get("yskx_begin").toString(),map.get("yskx_end")==null?"":map.get("yskx_end").toString(),"应付工资",map.get("yfgz_begin")==null?"":map.get("yfgz_begin").toString(),map.get("yfgz_end")==null?"":map.get("yfgz_end").toString()});
    	
    	Row row_7 = sheet.createRow(7);
    	createRow(row_7, new String[]{"预付账款",map.get("yfzk_begin")==null?"":map.get("yfzk_begin").toString(),map.get("yfzk_end")==null?"":map.get("yfzk_end").toString(),"应交税金",map.get("yjsj_begin")==null?"":map.get("yjsj_begin").toString(),map.get("yjsj_end")==null?"":map.get("yjsj_end").toString()});
    	
    	Row row_8 = sheet.createRow(8);
    	createRow(row_8, new String[]{"存货",map.get("ch_begin")==null?"":map.get("ch_begin").toString(),map.get("ch_end")==null?"":map.get("ch_end").toString(),"预收账款",map.get("yszk_begin")==null?"":map.get("yszk_begin").toString(),map.get("yszk_end")==null?"":map.get("yszk_end").toString()});
    	
    	Row row_9 = sheet.createRow(9);
    	createRow(row_9, new String[]{"待摊费用",map.get("dtfy_begin")==null?"":map.get("dtfy_begin").toString(),map.get("dtfy_end")==null?"":map.get("dtfy_end").toString(),"预提费用",map.get("ytfy_begin")==null?"":map.get("ytfy_begin").toString(),map.get("ytfy_end")==null?"":map.get("ytfy_end").toString()});
    	
    	Row row_10 = sheet.createRow(10);
    	createRow(row_10, new String[]{"一年内到期的长期债券投资",map.get("ynndqdcqzqtz_begin")==null?"":map.get("ynndqdcqzqtz_begin").toString(),map.get("ynndqdcqzqtz_end")==null?"":map.get("ynndqdcqzqtz_end").toString(),"预计负债",map.get("yjfz_begin")==null?"":map.get("yjfz_begin").toString(),map.get("yjfz_end")==null?"":map.get("yjfz_end").toString()});
    	
    	Row row_11 = sheet.createRow(11);
    	createRow(row_11, new String[]{"其他流动资产",map.get("qtldzc_begin")==null?"":map.get("qtldzc_begin").toString(),map.get("qtldzc_end")==null?"":map.get("qtldzc_end").toString(),"一年内到期的长期负债",map.get("ynndqdcqfz_begin")==null?"":map.get("ynndqdcqfz_begin").toString(),map.get("ynndqdcqfz_end")==null?"":map.get("ynndqdcqfz_end").toString()});
    	
    	Row row_12 = sheet.createRow(12);
    	createRow(row_12, new String[]{"流动资产合计",map.get("ldzchj_begin")==null?"":map.get("ldzchj_begin").toString(),map.get("ldzchj_end")==null?"":map.get("ldzchj_end").toString(),"其他流动负债",map.get("qtldfz_begin")==null?"":map.get("qtldfz_begin").toString(),map.get("qtldfz_end")==null?"":map.get("qtldfz_end").toString()});
    	
    	Row row_13 = sheet.createRow(13);
    	createRow(row_13, new String[]{"","","","流动负债合计",map.get("ldfzhj_begin")==null?"":map.get("ldfzhj_begin").toString(),map.get("ldfzhj_end")==null?"":map.get("ldfzhj_end").toString()});
    	
    	Row row_14 = sheet.createRow(14);
    	createRow(row_14, new String[]{"长期投资：","","","","",""});
    	
    	Row row_15 = sheet.createRow(15);
    	createRow(row_15, new String[]{"长期股权投资",map.get("cqgqtz_begin")==null?"":map.get("cqgqtz_begin").toString(),map.get("cqgqtz_end")==null?"":map.get("cqgqtz_end").toString(),"长期负债：","",""});
    	
    	Row row_16 = sheet.createRow(16);
    	createRow(row_16, new String[]{"长期债权投资",map.get("cqzqtz_begin")==null?"":map.get("cqzqtz_begin").toString(),map.get("cqzqtz_end")==null?"":map.get("cqzqtz_end").toString(),"长期借款",map.get("cqjd_begin")==null?"":map.get("cqjd_begin").toString(),map.get("cqjd_end")==null?"":map.get("cqjd_end").toString()});
    	
    	Row row_17 = sheet.createRow(17);
    	createRow(row_17, new String[]{"长期投资合计",map.get("cqtzhj_begin")==null?"":map.get("cqtzhj_begin").toString(),map.get("cqtzhj_end")==null?"":map.get("cqtzhj_end").toString(),"长期应付款",map.get("cqyfk_begin")==null?"":map.get("cqyfk_begin").toString(),map.get("cqyfk_end")==null?"":map.get("cqyfk_end").toString()});
    	
    	Row row_18 = sheet.createRow(18);
    	createRow(row_18, new String[]{"","","","其他长期负债",map.get("qtcqfz_begin")==null?"":map.get("qtcqfz_begin").toString(),map.get("qtcqfz_end")==null?"":map.get("qtcqfz_end").toString()});
    	
    	Row row_19 = sheet.createRow(19);
    	createRow(row_19, new String[]{"固定资产：","","","长期负债合计",map.get("cqfzhj_begin")==null?"":map.get("cqfzhj_begin").toString(),map.get("cqfzhj_end")==null?"":map.get("cqfzhj_end").toString()});
    	
    	Row row_20 = sheet.createRow(20);
    	createRow(row_20, new String[]{"固定资产原价",map.get("gdzcyj_begin")==null?"":map.get("gdzcyj_begin").toString(),map.get("gdzcyj_end")==null?"":map.get("gdzcyj_end").toString(),"","",""});
    	
    	Row row_21 = sheet.createRow(21);
    	createRow(row_21, new String[]{"减：累积折旧",map.get("ljzj_begin")==null?"":map.get("ljzj_begin").toString(),map.get("ljzj_end")==null?"":map.get("ljzj_end").toString(),"受托代理负债：","",""});
    	
    	Row row_22 = sheet.createRow(22);
    	createRow(row_22, new String[]{"固定资产净值",map.get("gdzcjz_begin")==null?"":map.get("gdzcjz_begin").toString(),map.get("gdzcjz_end")==null?"":map.get("gdzcjz_end").toString(),"受托代理负债：",map.get("gdzcjz_begin")==null?"":map.get("gdzcjz_begin").toString(),map.get("gdzcjz_end")==null?"":map.get("gdzcjz_end").toString()});
    	
    	Row row_23 = sheet.createRow(23);
    	createRow(row_23, new String[]{"在建工程",map.get("zjgc_begin")==null?"":map.get("zjgc_begin").toString(),map.get("zjgc_end")==null?"":map.get("zjgc_end").toString(),"","",""});
    	
    	Row row_24 = sheet.createRow(24);
    	createRow(row_24, new String[]{"文物文化资产",map.get("wwwhzc_begin")==null?"":map.get("wwwhzc_begin").toString(),map.get("wwwhzc_end")==null?"":map.get("wwwhzc_end").toString(),"负债合计",map.get("fzhj_begin")==null?"":map.get("fzhj_begin").toString(),map.get("fzhj_end")==null?"":map.get("fzhj_end").toString()});
    	
    	Row row_25 = sheet.createRow(25);
    	createRow(row_25, new String[]{"固定资产清理",map.get("gdzcql_begin")==null?"":map.get("gdzcql_begin").toString(),map.get("gdzcql_end")==null?"":map.get("gdzcql_end").toString(),"","",""});
    	
    	Row row_26 = sheet.createRow(26);
    	createRow(row_26, new String[]{"固定资产合计",map.get("gdzchhj_begin")==null?"":map.get("gdzchhj_begin").toString(),map.get("gdzchhj_end")==null?"":map.get("gdzchhj_end").toString(),"","",""});
    	
    	Row row_27 = sheet.createRow(27);
    	createRow(row_27, new String[]{"","","","","",""});
    	
    	Row row_28 = sheet.createRow(28);
    	createRow(row_28, new String[]{"无形资产：","","","","",""});
    	
    	Row row_29 = sheet.createRow(29);
    	createRow(row_29, new String[]{"无形资产",map.get("wxzc_begin")==null?"":map.get("wxzc_begin").toString(),map.get("wxzc_end")==null?"":map.get("wxzc_end").toString(),"净资产：","",""});
    	
    	Row row_30 = sheet.createRow(30);
    	createRow(row_30, new String[]{"","","","非限定性净资产",map.get("fxdxjzc_begin")==null?"":map.get("fxdxjzc_begin").toString(),map.get("fxdxjzc_end")==null?"":map.get("fxdxjzc_end").toString()});
    	
    	Row row_31 = sheet.createRow(31);
    	createRow(row_31, new String[]{"受托代理资产","","","限定性净资产",map.get("xdxjzc_begin")==null?"":map.get("xdxjzc_begin").toString(),map.get("xdxjzc_end")==null?"":map.get("xdxjzc_end").toString()});
    	
    	Row row_32 = sheet.createRow(32);
    	createRow(row_32, new String[]{"受托代理资产",map.get("stdlzc_begin")==null?"":map.get("stdlzc_begin").toString(),map.get("stdlzc_end")==null?"":map.get("stdlzc_end").toString(),"净资产合计",map.get("jzchj_begin")==null?"":map.get("jzchj_begin").toString(),map.get("jzchj_end")==null?"":map.get("jzchj_end").toString()});
    	
    	Row row_33 = sheet.createRow(33);
    	createRow(row_33, new String[]{"","","","","",""});
    	
    	Row row_34 = sheet.createRow(34);
    	createRow(row_34, new String[]{"资产总计",map.get("zczj_begin")==null?"":map.get("zczj_begin").toString(),map.get("zczj_end")==null?"":map.get("zczj_end").toString(),"负债和净资产合计",map.get("fzhjzchj_begin")==null?"":map.get("fzhjzchj_begin").toString(),map.get("fzhjzchj_end")==null?"":map.get("fzhjzchj_end").toString()});
        
    }
    
    private void createRow(Row row, String...vals){
    	for(int i = 0 ; i < vals.length; i++){
    		row.createCell(i).setCellValue(vals[i]);
    	}
    }

    @Override
    protected void setStyle(Workbook workbook) {
        DefaultCellStyle defaultCellStyle = new DefaultCellStyleImpl();
//        super.cellStyle = defaultCellStyle.setCellStyle(workbook, font);
    }

}
