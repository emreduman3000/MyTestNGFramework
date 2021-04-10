package com.techproed.excelAutomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadExcel
{
    @Test
    public void readExcelFile() throws IOException
    {
        //write the address of the file which datas will be used
        String path="C:\\Users\\Emre Duman\\IdeaProjects\\myTestNGFramework\\src\\test\\resources\\ExcelAutomation.xlsx";
        String path2=".\\src\\test\\resources\\ExcelAutomation.xlsx";// . means all consecutive directories before source

        //by using fileInputStream open workbook
        FileInputStream fileInputStream=new FileInputStream(path);

        //selenium excel dosyalarını okuyamıyor,oyyuzden maven repositoryden dependency'lerini alıp pom.xml dosyamıza koyucaz
        Workbook workbook= WorkbookFactory.create(fileInputStream);//create method icin exception throw yaptık

        //go to 1.sheet
        Sheet sheet=workbook.getSheetAt(0);

        //go to first row
        Row row=sheet.getRow(0);

        //go to first cell
        Cell cell =row.getCell(0);

        System.out.println("at first cell's data: "+ cell);
        System.out.println("2. celll'deki data:" + row.getCell(1));

        //row:2 cell:1
        Row row2=sheet.getRow(1);
        Cell cell1=row2.getCell(0);
        System.out.println(cell1);

        //chain methodu
        System.out.println(workbook.getSheetAt(0).getRow(1).getCell(0));
        String row2Cell=workbook.getSheetAt(0).getRow(1).getCell(0).toString();
        System.out.println(row2Cell);

        //son satir numarasını alalım:999
        int rowSayisi=sheet.getLastRowNum();
        System.out.println("dosyadaki toplam satir sayisi:"+ rowSayisi);

        //kullanılan satırların sayısını nasıl alabliriz
        //burada index 1den baslar
        int numberOfPhysicalRows=sheet.getPhysicalNumberOfRows();
        System.out.println(numberOfPhysicalRows);

        //hash map ile ülke-baskent ini depolucam
        Map<String ,String> countries=new HashMap<>();
        int countryColumn=0;
        int capitalColumn=1;
        for (int rowNum=1; rowNum<=rowSayisi;rowNum++)
        {
            String country=sheet.getRow(rowNum).getCell(countryColumn).toString();
            //System.out.println(country+" ");
            String capital=sheet.getRow(rowNum).getCell(capitalColumn).toString();
            //System.out.println(capital);

            countries.put(country,capital);
        }
        System.out.println(countries);
    }
}
