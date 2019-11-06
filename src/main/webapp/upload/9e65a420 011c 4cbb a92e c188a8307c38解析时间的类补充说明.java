package com.offcn.poi;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @Author: liuhui
 * @Date: 2019-04-08
 */
public class TestApp {


    @Test
    public void tests02() throws Exception{
        Workbook wb = WorkbookFactory.create(new File("C:\\Users\\Administrator\\Desktop\\customer.xls"));

        Sheet sheet = wb.getSheetAt(0);

        for (int i=sheet.getFirstRowNum();i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                String cellValue = getCellValue(cell);
                System.out.print(cellValue+"--");

            }
            System.out.println();
        }



    }

    private String getCellValue(Cell c){
        String o = null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd");

        switch(c.getCellType()){
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:

                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(c)){
                    Date date = c.getDateCellValue();
                    o = sdf.format(date);
                }else {
                    o = c.getNumericCellValue()+"";
                }
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }


}
