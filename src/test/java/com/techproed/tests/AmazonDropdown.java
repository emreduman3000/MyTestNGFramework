package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AmazonDropdown extends TestBase
{
    //Tests packagenin altinda bir class olusturun: AmazonDropdown
    //NOT: TestBase classini kullanalim.
    //Dogrulama yaparken Assertion kullanalim.
    //Test case'de verilen secenekler sizde farkli olabilir. Sizde gorunen seceneklere gore
    //kodunuzu yazabilirsiniz.

    @Test
    public void amazonDropdownTest()
    {
        //2. https://www.amazon.com/ adresine gidin.
        driver.get("https://www.amazon.com/");

        //3. Dropdown elementini bulun.
        WebElement dropdown=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        //4. İlk seçilen seçeneği(firstSelectedOption) konsolda yazdırın ve ilk secenegin "All
        //Departments" ile esit oldugunu dogrulayin.
        Select options= new Select(dropdown);
        String firstSelectedOption= options.getFirstSelectedOption().getText();//by default, it is selected
        System.out.println(firstSelectedOption);
        Assert.assertEquals(firstSelectedOption,"All Departments");

        //5. 6.seçeneği(option) index kullanarak secin ve 6.seçeneğin "Books" oldugunu
        //dogrulayin.(Seçtikten sonra getFirstSelectedOption() metodunu kullanmanız gerekir)
        options.selectByIndex(5);
        String sixthSelectedOption=options.getFirstSelectedOption().getText();
        System.out.println(sixthSelectedOption);
        Assert.assertEquals(sixthSelectedOption,"Books");

        //6. Butun dropdown seçeneklerini konsolda yazdırın
        List<WebElement> allOptions=options.getOptions();//ı created an List which has all WebElement which are in the dropdown
        for(WebElement i: allOptions)
            System.out.println(i.getText());

        //7. Dropdowndaki eleman sayisini konsolda yazdırın
        System.out.println("Dropdown element number: "+allOptions.size());//28

        //8. "Electronics" ın dropdownda olup olmadığını kontrol edin. "Electronics" dropdownda
        //bulunuyorsa konsolda True yazdırın. Aksi takdirde False yazdırın.
        boolean flag=false;
        WebElement electronics = null;
        for (WebElement i:allOptions)
        {
            if(i.getText().equals("Electronics"))
            {
                flag=true;
                System.out.println(flag);//true

                electronics=i;
                break;
            }

        }
        electronics.click();

    }
}
