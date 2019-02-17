package com.mycrud.controller;

import com.mycrud.model.Report;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by book on 13.02.2019.
 */

@RequestMapping("/excel")
@Controller
public class ExportController {

    private static final Logger logger = Logger.getLogger(ExportController.class);

    @Autowired
    JdbcService jdbcService;



    @RequestMapping(method = RequestMethod.POST)
    public void downloadExcelTestFile(@RequestParam("repName") String repname,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");
        Row rowHead = sheet.createRow(0);
        rowHead.createCell(0).setCellValue("Year");
        rowHead.createCell(1).setCellValue("Faculty");
        rowHead.createCell(2).setCellValue("Count of students");

        List<Report> report = jdbcService.getReport();
        int sum = 0;
        int k = 0;
        int n = 1;
        for(Report summary : report) {
            Row row = sheet.createRow(n);
            if (summary.getFac() == null && summary.getYear() == null)
                break;
            row.createCell(k++).setCellValue(summary.getYear());
            if (summary.getFac() == null)
                row.createCell(k++).setCellValue("result: ");
            else {
                row.createCell(k++).setCellValue(summary.getFac());
                sum += summary.getStud();
            }
            row.createCell(k++).setCellValue(summary.getStud());

            n++;
            k = 0;
        }
        Row rowFoot = sheet.createRow(n);
        rowFoot.createCell(0).setCellValue("Total: ");
        rowFoot.createCell(2).setCellValue(sum);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + new Date().toString() + ".xls");

        OutputStream out = response.getOutputStream();
        wb.write(out);

        out.flush();
        out.close();
        wb.close();
    }
}
