package com.yao.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class ExcelUtil {

    /**
     * 创建用户使用时间excel表
     * @return
     */
    public static Workbook createUserInlineExcel(List<String>times){
        //创建excel工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建第一个sheet
        Sheet sheet = workbook.createSheet("time");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        sheet.setColumnWidth(0, (short) (35.7 * 150));

        //创建第一行
        Row row = sheet.createRow(0);

        //创建两种单元格格式
        CellStyle cs1 = workbook.createCellStyle();
        CellStyle cs2 = workbook.createCellStyle();

        //创建两种字体
        Font f1 = workbook.createFont();
        Font f2 = workbook.createFont();

        //创建第一种字体样式(用于列名）
        f1.setFontHeightInPoints((short) 10);
        f1.setColor(IndexedColors.BLACK.getIndex());
        f1.setBold(true);

        //创建第二种字体样式，用于值
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        //设置第一种单元格的样式（用于列）
        cs1.setFont(f1);
        cs1.setBorderLeft(BorderStyle.THIN);
        cs1.setBorderRight(BorderStyle.THIN);
        cs1.setBorderTop(BorderStyle.THIN);
        cs1.setBorderBottom(BorderStyle.THIN);
        cs1.setAlignment(HorizontalAlignment.CENTER);

        //设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs2.setAlignment(HorizontalAlignment.CENTER);

        //设置列
        Cell cell = row.createCell(0);
        cell.setCellValue("time");
        cell.setCellStyle(cs1);

        //设置每列的值
        for (int i = 0;i<times.size();i++){
            Row row1 = sheet.createRow(i+1);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue(times.get(i));
            cell.setCellStyle(cs2);
        }
        return workbook;
    }
}
