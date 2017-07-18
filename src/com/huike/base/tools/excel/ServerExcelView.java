package com.huike.base.tools.excel;

import java.util.Map;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerExcelView extends ExcelView {

	Logger log = LoggerFactory.getLogger(ServerExcelView.class);

	@Override
	public void setRow(Sheet sheet, Map<String, Object> map) {

		// create title row
		Row title = sheet.createRow(0);
		title.createCell(0).setCellValue(map.get("title") == null ? "" : map.get("title").toString());
		title.setHeightInPoints(40);

		/*
		 * 设定合并单元格区域范围 firstRow 0-based lastRow 0-based firstCol 0-based lastCol
		 * 0-based
		 */
		CellRangeAddress craTitle = new CellRangeAddress(0, 0, 0, 8);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craTitle);

		Row row_1 = sheet.createRow(1);
		createRow(row_1, new String[] { "服务类型", "咨询（件）", "", "代办（件）", "", "其他（件）", "", "服务人次", "备注" });

		Row row_2 = sheet.createRow(2);
		createRow(row_2, new String[] { "", "受理", "办结", "受理", "办结", "受理", "办结", "", "" });

		Row row_3 = sheet.createRow(3);
		createRow(row_3,
				new String[] { "就业服务", getValue(map, "employment_consult_accpet"),
						getValue(map, "employment_consult_deal"), getValue(map, "employment_agent_accpet"),
						getValue(map, "employment_agent_deal"), getValue(map, "employment_others_accpet"),
						getValue(map, "employment_others_deal"), getValue(map, "employment_numbers"),
						getValue(map, "employment_remark") });

		Row row_4 = sheet.createRow(4);
		createRow(row_4, new String[] { "创业融资", getValue(map, "business_consult_accpet"),
				getValue(map, "business_consult_deal"), getValue(map, "business_agent_accpet"),
				getValue(map, "business_agent_deal"), getValue(map, "business_others_accpet"),
				getValue(map, "business_others_deal"), getValue(map, "business_numbers"),
				getValue(map, "business_remark") });

		Row row_5 = sheet.createRow(5);
		createRow(row_5, new String[] { "法律服务", getValue(map, "law_consult_accpet"),
				getValue(map, "law_agent_deal"), getValue(map, "law_agent_accpet"),
				getValue(map, "law_agent_deal"), getValue(map, "law_others_accpet"),
				getValue(map, "law_others_deal"), getValue(map, "law_numbers"),
				getValue(map, "law_remark")});

		Row row_6 = sheet.createRow(6);
		createRow(row_6, new String[] { "政策服务", getValue(map, "policy_consult_accpet"),
				getValue(map, "policy_agent_deal"), getValue(map, "policy_agent_accpet"),
				getValue(map, "policy_agent_deal"), getValue(map, "policy_others_accpet"),
				getValue(map, "policy_others_deal"), getValue(map, "policy_numbers"),
				getValue(map, "policy_remark") });

		Row row_7 = sheet.createRow(7);
		createRow(row_7, new String[] { "项目申办", getValue(map, "project_consult_accpet"),
				getValue(map, "project_agent_deal"), getValue(map, "project_agent_accpet"),
				getValue(map, "project_agent_deal"), getValue(map, "project_others_accpet"),
				getValue(map, "project_others_deal"), getValue(map, "project_numbers"),
				getValue(map, "project_remark") });

		Row row_8 = sheet.createRow(8);
		createRow(row_8, new String[] { "专家指导", getValue(map, "professor_consult_accpet"),
				getValue(map, "professor_agent_deal"), getValue(map, "professor_agent_accpet"),
				getValue(map, "professor_agent_deal"), getValue(map, "professor_others_accpet"),
				getValue(map, "professor_others_deal"), getValue(map, "professor_numbers"),
				getValue(map, "professor_remark")});

		Row row_9 = sheet.createRow(9);
		createRow(row_9, new String[] { "技能培训", getValue(map, "train_consult_accpet"),
				getValue(map, "train_agent_deal"), getValue(map, "train_agent_accpet"),
				getValue(map, "train_agent_deal"), getValue(map, "train_others_accpet"),
				getValue(map, "train_others_deal"), getValue(map, "train_numbers"),
				getValue(map, "train_remark") });

		Row row_10 = sheet.createRow(10);
		createRow(row_10, new String[] { "保险业务", getValue(map, "insurance_consult_accpet"),
				getValue(map, "insurance_agent_deal"), getValue(map, "insurance_agent_accpet"),
				getValue(map, "insurance_agent_deal"), getValue(map, "insurance_others_accpet"),
				getValue(map, "insurance_others_deal"), getValue(map, "insurance_numbers"),
				getValue(map, "insurance_remark") });

		Row row_11 = sheet.createRow(11);
		createRow(row_11, new String[] { "气象信息", getValue(map, "weather_consult_accpet"),
				getValue(map, "weather_agent_deal"), getValue(map, "weather_agent_accpet"),
				getValue(map, "weather_agent_deal"), getValue(map, "weather_others_accpet"),
				getValue(map, "weather_others_deal"), getValue(map, "weather_numbers"),
				getValue(map, "weather_remark") });

		Row row_12 = sheet.createRow(12);
		createRow(row_12, new String[] { "供需信息",getValue(map, "supply_consult_accpet"),
				getValue(map, "supply_agent_deal"), getValue(map, "supply_agent_accpet"),
				getValue(map, "supply_agent_deal"), getValue(map, "supply_others_accpet"),
				getValue(map, "supply_others_deal"), getValue(map, "supply_numbers"),
				getValue(map, "supply_remark") });

		Row row_13 = sheet.createRow(13);
		createRow(row_13, new String[] { "累计",getValue(map, "acount_consult_accpet"),
				getValue(map, "acount_agent_deal"), getValue(map, "acount_agent_accpet"),
				getValue(map, "acount_agent_deal"), getValue(map, "acount_others_accpet"),
				getValue(map, "acount_others_deal"), getValue(map, "acount_numbers"),
				getValue(map, "acount_remark")  });

		/*
		 * 设定合并单元格区域范围 firstRow 0-based lastRow 0-based firstCol 0-based lastCol
		 * 0-based
		 */
		CellRangeAddress craUnit_1 = new CellRangeAddress(1, 2, 0, 0);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_1);

		CellRangeAddress craUnit_2 = new CellRangeAddress(1, 1, 1, 2);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_2);

		CellRangeAddress craUnit_3 = new CellRangeAddress(1, 1, 3, 4);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_3);

		CellRangeAddress craUnit_4 = new CellRangeAddress(1, 1, 5, 6);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_4);

		CellRangeAddress craUnit_5 = new CellRangeAddress(1, 2, 7, 7);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_5);

		CellRangeAddress craUnit_6 = new CellRangeAddress(1, 2, 8, 8);
		// 在sheet里增加合并单元格
		sheet.addMergedRegion(craUnit_6);
	}

	private void createRow(Row row, String... vals) {

		row.setHeightInPoints(30);

		for (int i = 0; i < vals.length; i++) {
			row.createCell(i).setCellValue(vals[i]);
		}
	}

	@Override
	protected void setStyle(Workbook workbook) {
		DefaultCellStyle defaultCellStyle = new DefaultCellStyleImpl();
		// super.cellStyle = defaultCellStyle.setCellStyle(workbook, font);
	}

	private String getValue(Map map, String _param) {
		return map.get(_param) == null ? "" : map.get(_param).toString();
	}
}
