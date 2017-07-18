package com.huike.base.tools.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author Kent.Wang
 * @Date 2017/6/28
 */
public interface DefaultCellStyle {
    CellStyle setCellStyle(Workbook workbook, Font font);
}
