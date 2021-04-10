package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTables extends TestBase {

    //Bir class oluşturun : WebTables
    //login() metodun oluşturun ve oturum açın.
    //http://www.fhctrip.com/admin/HotelRoomAdmin
    //Username : manager2
    //Password : Man1ager2!

    //printRows() metodu oluşturun
    //table body’sinde bulunan toplam satir(row) sayısını bulun.
    //Table body’sinde bulunan satirlari(rows)  konsolda yazdırın.
    //4.satirdaki(row) elementleri konsolda yazdırın.
    //printCells() metodu oluşturun
    //table body’sinde bulunan toplam hücre(cell) sayısını bulun.
    //Table body’sinde bulunan hücreleri(cells)  konsolda yazdırın.
    //printColumns() metodu oluşturun
    //table body’sinde bulunan toplam sutun(column) sayısını bulun.
    //Table body’sinde bulunan sutunlari(column)   konsolda yazdırın.
    //5.column daki elementleri  konsolda yazdırın.


    public void login() {
        driver.get("http://fhctrip-qa.com/admin/HotelRoomAdmin");
        driver.findElement(By.id("UserName")).sendKeys("manager2");
        driver.findElement(By.id("Password")).sendKeys("Man1ager2!");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    //table() metodu oluşturun
    //Tüm table body’sinin boyutunu(size) bulun.
    //Table’daki başlıkları(headers) konsolda yazdırın.
    @Test
    public void table() throws InterruptedException {

        //<table> -tablo
        //<thead> -header-section
        //<tr> -row
        //<th> table header data
        //</th>
        //</tr>
        //</thead>
        //<tbody> table body
            //<tr> -row
                //<th> -table data
                //</th>
            //</tr>
        //</tbody>
        //</table>

        login();
        // tbody'deki datalari konsolda yazdirin
        Thread.sleep(3000);
        WebElement tbody = driver.findElement(By.xpath("//tbody"));

        System.out.println("tbody.getText()\n" + tbody.getText());

        //Tüm table body'sinin boyutunu(size) bulun.
        List<WebElement> table = driver.findElements(By.xpath("//tbody//td"));
        System.out.println("TABLE BODY SIZE : " + table.size());
//        Table'daki başlıkları(headers) konsolda yazdırın.

        /*
        13 SEA HOTEL BUTIK MARIA SEA 500.00 Double DETAILS
        14 SEA HOTEL BUTIK MARIA SEA 500.00 Double DETAILS
        15 HILTON HOTEL Royal 111 123 700.00 King DETAILS
        16 HILTON HOTEL KING 112 ABC 500.00 Double DETAILS
        17 kalem 44 zaman istanbul 700.00 Double DETAILS
        18 kalem 44 zaman istanbul 700.00 Double DETAILS
        19 kalem 44 zaman istanbul 700.00 Double DETAILS
        20 kalem 1234 Plage 2'eme étage 500.00 Quad DETAILS
        21 HILTON HOTEL Seven Star Furkan London 500.00 Queen DETAILS
        22 kalem 1234 Plage 2'eme étage 500.00 Quad DETAILS
        TABLE BODY SIZE : 90   10lines 9coloumns
         */

        //        Table’daki başlıkları(headers) konsolda yazdırın.
        List<WebElement> allHeaders = driver.findElements(By.tagName("th"));
        int i = 0;
        for (WebElement header : allHeaders) {
            System.out.println(++i + ". Header - " + header.getText());
        }
    }


    @Test
    public void printRows() {
        login();
        driver.manage().window().maximize();//ya bu

        //table body’sinde bulunan toplam satir(row) sayısını bulun.
        System.out.println("*******ROWS********");
        System.out.println("Table body'de toplam " + driver.findElements(By.xpath("//tbody//tr")).size() + " tane satir(row) vardir.");
        //Table body’sinde bulunan satirlari(rows)  konsolda yazdırın.
        List<WebElement> allRows = driver.findElements(By.xpath("//tbody//tr"));
        for (WebElement row : allRows) {
            System.out.println(row.getText());
        }
        //4.satirdaki(row) elementleri konsolda yazdırın.
        System.out.println("*******ROW4********");
        List<WebElement> elementsRow4 = driver.findElements(By.xpath("//tr[4]//td"));
        for (WebElement element : elementsRow4) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void printCells() {
        login();

        driver.manage().window().maximize();//ya bu

        //table body'sinde bulunan toplam hücre(cell) sayısını bulun.
        System.out.println("Tabloda toplam " + driver.findElements(By.xpath("//tbody//td")).size() + " cell vardir.");

        System.out.println("At Table " + driver.findElements(By.xpath("//tbody//td")));
        List<WebElement> elementCells = driver.findElements(By.xpath("//tbody//td"));
        for (WebElement allCells : elementCells) {
            System.out.println("TABLE BODY DE BULUNAN HUCRELER :" + allCells.getText());
        }
    }

    @Test
    public void printColumns() {
        login();
        //table body’sinde bulunan toplam sutun(column) sayısını bulun.      //tr[3]//td
        System.out.println("Tabloda toplam " + driver.findElements(By.xpath("//th")).size() + " tane sutun(column) vardir.");
        //5.column daki elementleri  konsolda yazdırın.
        List<WebElement> column5 = driver.findElements(By.xpath("//tbody//td[5]"));
        for (WebElement element : column5) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void printDataTest() {
        login();
        printData(8, 3);     // 1234
        printData(5, 2);    //kalem
        printData(9, 5);    //London
    }

    public void printData(int row, int column) {
        //kodlari bu metodun icine yazalim
        //   //tbody//tr[8]//td[3]
        //dinamik bir xpath yazalim
        String xpath = "//tbody//tr[" + row + "]//td[" + column + "]";
        //elementleri xpath'i kullanarak bulalim
        WebElement data = driver.findElement(By.xpath(xpath));
        //data lari yazdiralim
        System.out.println(data.getText());
    }


    @Test
    public void fetch_TheNumberOf_RowAndColumn() {

        driver.get("http://demo.guru99.com/test/web-table-element.php");
        //No.of Columns
        List col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
        System.out.println("No of cols are : " + col.size());
        //No.of rows
        List rows = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
        System.out.println("No of rows are : " + rows.size());
    }

    @Test
    public void fetch_Cell() {
        driver.get("http://demo.guru99.com/test/web-table-element.php");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement baseTable = driver.findElement(By.tagName("table"));

        //To find third row of table
        WebElement tableRow = baseTable.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr[3]"));
        String rowtext = tableRow.getText();
        System.out.println("Third row of table : " + rowtext);

        //to get 3rd row's 2nd column data
        WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id='leftcontainer']/table/tbody/tr[3]/td[2]"));
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is : " + valueIneed);

    }


    @Test
    public void getMaxValueOfTable() throws ParseException {
        driver.get("http://demo.guru99.com/test/web-table-element.php");
        String max;
        double m=0,r=0;

        //No. of Columns
        List  col = driver.findElements(By.xpath(".//*[@id='leftcontainer']/table/thead/tr/th"));
        System.out.println("Total No of columns are : " +col.size());
        //No.of rows
        List  rows = driver.findElements(By.xpath (".//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
        System.out.println("Total No of rows are : " + rows.size());
        for (int i =1;i<rows.size();i++)
        {
            max= driver.findElement(By.xpath("html/body/div[1]/div[5]/table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
            NumberFormat f =NumberFormat.getNumberInstance();
            Number num = f.parse(max);
            max = num.toString();
            m = Double.parseDouble(max);
            if(m>r)
            {
                r=m;
            }
        }
        System.out.println("Maximum current price is : "+ r);
    }

    @Test
    public void getAllValues()
    {
        driver.get("http://demo.guru99.com/test/table.html");
        //To locate table.
        WebElement mytable = driver.findElement(By.xpath("/html/body/table/tbody"));
        //To locate rows of table.
        List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
        //To calculate no of rows In table.
        int rows_count = rows_table.size();
        //Loop will execute till the last row of table.
        for (int row = 0; row < rows_count; row++) {
            //To locate columns(cells) of that specific row.
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            //To calculate no of columns (cells). In that specific row.
            int columns_count = Columns_row.size();
            System.out.println("Number of cells In Row " + row + " are " + columns_count);
            //Loop will execute till the last cell of that specific row.
            for (int column = 0; column < columns_count; column++) {
                // To retrieve text from that specific cell.
                String celtext = Columns_row.get(column).getText();
                System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
            }
            System.out.println("-------------------------------------------------- ");
        }
    }
}

