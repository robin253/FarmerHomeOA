package com.huike.app.report.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huike.base.tools.excel.ExcelView;
import com.huike.base.tools.excel.ExportMemberVo;
import com.huike.base.tools.excel.FinanceExcelView;


/**
 * @Author Kent.Wang
 * @Date 2017/6/27
 */
@RestController
@RequestMapping(value = "/api/excel")
public class ExportController {

    @RequestMapping(value = "/down", method = RequestMethod.GET)
    public ModelAndView download() {

        List<ExportMemberVo> list = new ArrayList<ExportMemberVo>();
        for (int i = 0; i < 5; i++) {
            ExportMemberVo exportMemberVo = new ExportMemberVo();
            exportMemberVo.setName("Kent" + i);
            @SuppressWarnings("unchecked")
            int gender = ThreadLocalRandom.current().nextInt(0, 2);
            exportMemberVo.setGender(gender);
            exportMemberVo.setPhone("182xxxxxxxx");
            exportMemberVo.setBankName("建设银行");
            list.add(exportMemberVo);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("members", list);
        map.put("name", "魅力城市");
        ExcelView excelView = new FinanceExcelView();
        return new ModelAndView(excelView, map);
    }

}
