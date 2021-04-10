package com.techproed.smoketest;

import com.techproed.pages.GlbHomePage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GlbCategoryDropdownTest
{

    GlbHomePage glbHomePage =new GlbHomePage();

    @Test
    public void glbCatagoryDropdownTest()
    {
        Driver.getDriver().get(ConfigReader.getProperty("glb_url"));
        Select options=new Select(glbHomePage.allCategoriesDropdown);

        boolean flag=false;
        for (WebElement i: options.getOptions())
        {
            if(i.getText().equals("Furniture"))
            {
                System.out.println("At Dropdown, there is Furniture option");
                flag=true;
                break;
            }

        }
        Assert.assertTrue(flag);
        Driver.closeDriver();

    }


}
