package com.techproed.excelAutomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel
{
    @Test
    public void writeExcelFile() throws IOException
    {
        String path=".\\src\\test\\resources\\ExcelAutomation.xlsx";// . means all consecutive directories before source
        FileInputStream fileInputStream=new FileInputStream(path);//dosya acma- input alma

        Workbook workbook= WorkbookFactory.create(fileInputStream);//workbook!ı ac - create method icin exception throw yaptık
        Sheet sheet=workbook.getSheetAt(0);//sheet 1 i ac
        Row row=sheet.getRow(0);//1. row'a git
        Cell cell=row.getCell(3);//4.cell
        //Cell cell=row.createCell(3);//4.cell
        cell.setCellValue("population");//4. cell'e ata

        System.out.println(sheet.getRow(0).getCell(3).toString());//population


        workbook.getSheetAt(0).getRow(1).getCell(3).setCellValue(1234567890);
        workbook.getSheetAt(0).getRow(2).createCell(3).setCellValue(1234567890);
        workbook.getSheetAt(0).getRow(3).getCell(3).setCellValue(1234567890);

        Cell cell5 = row.createCell(4);
        cell5.setCellValue("YUZOLCUMU");
        row.removeCell(cell5);


        FileOutputStream fileOutputStream=new FileOutputStream(path);//dosya yazma -out koyma
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        fileInputStream.close();
        workbook.close();

        //exceli kaptcaz hem de pcde kaptcaz o zaman pass oluyor

        //Test package'ına right click add directory(resources) de
        //excel dosyasını bilgisayardan alalım
        //excel dosyasaını desktoptan pull and drop over resources directory
        //or right click resources -refactor -move file
    }
}
