package com.huike.base.tools.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

/**
 * @Author Kent.Wang
 * @Date 2017/6/28
 */
@Component
public class DefaultCellStyleImpl implements DefaultCellStyle {
	
    public CellStyle setCellStyle(Workbook workbook, Font font) {
        // create style for header cells
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        return cellStyle;	
    }
}
