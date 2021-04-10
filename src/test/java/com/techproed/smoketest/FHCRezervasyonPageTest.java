package com.techproed.smoketest;
import com.techproed.pages.FHCLoginPage;
import com.techproed.pages.FHCRezervasyonPage;
import com.techproed.utilities.TestBase;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FHCRezervasyonPageTest extends TestBase
{
    @Test
    public void fhcRezervasyonTest() throws InterruptedException {
        driver.get("http://www.fhctrip.com/admin/RoomReservationAdmin/Create");
        FHCLoginPage fhcLoginPage = new FHCLoginPage(driver);
        fhcLoginPage.login2();
        //sayfaya girdik

        FHCRezervasyonPage fhcRezervasyonPage=new FHCRezervasyonPage(driver);

        Select idUserSelect=new Select(fhcRezervasyonPage.idUser);
        idUserSelect.selectByIndex(1);

        Select idHotelRoomSelect=new Select(fhcRezervasyonPage.idHotelRoom);
        idHotelRoomSelect.selectByIndex(5);

        fhcRezervasyonPage.price.sendKeys("500");
        fhcRezervasyonPage.dateStart.sendKeys("01/01/2022");
        fhcRezervasyonPage.dateEnd.sendKeys("02/01/2022");
        fhcRezervasyonPage.adultAmount.sendKeys("2");
        fhcRezervasyonPage.childrenAmount.sendKeys("3");
        fhcRezervasyonPage.surname.sendKeys("DUMAN");
        fhcRezervasyonPage.phone.sendKeys("1234567890");
        fhcRezervasyonPage.email.sendKeys("email@gmail.com");
        fhcRezervasyonPage.notes.sendKeys("sea view");
        fhcRezervasyonPage.isApproved.click();
        fhcRezervasyonPage.isPaid.click();
        fhcRezervasyonPage.saveButton.click();

        Thread.sleep(3000);

        //Assert.assertTrue(fhcRezervasyonPage.successMessage.isDisplayed());  //1.
        String mesaj = fhcRezervasyonPage.successMessage.getText();
        Assert.assertTrue(mesaj.equals("RoomReservation was inserted successfully"));  //2.

        //ok butonuna tiklayin.
        //bunu yapmadık sen yap
    }

}