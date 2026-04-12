package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Default_Dropdown {

    WebDriver driver;
    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Facebook_SignUp() {
        driver.get("https://www.facebook.com/reg/");
    }
    @Test
    public  void TC_02_SignUp() {
        //Dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));

        //Index thay đổi vị trí => Đọc code không biết đang chọn option nào
        select.selectByIndex(10);

        //Value => Value != text đọc code cũng không biết đang chọn option nào
        //TH option không bắt buộc phải có value
        select.selectByValue("1");

        //Text => Nhìn thấy option giống như user chọn và option lúc nào cũng phải có text, ko có thì k chọn được, text cũng k thể trùng nhau
        // Không thay đổi element nếu bị đổi vị trí
        // Chạy fail -> reproduce lại -> Thấy luôn giá trị test bị fail để manual test lại
        select.selectByVisibleText("25");

        //Chọn xong verify option đã chọn thành công hay chưa
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "25");

        //Verify cái dropdown có phải là multiple select hay không
        //Nếu là multiple trả về true, ko phải là fail
        Assert.assertFalse(select.isMultiple());

        //Verify tổng số lượng item trong dropdown này
        Assert.assertEquals(select.getOptions().size(), 31);

        //Month
        select = new Select(driver.findElement(By.cssSelector("select#month")));
        select.selectByVisibleText("Jun");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Jun");

        //Year
        select = new Select(driver.findElement(By.cssSelector("select#year")));
        select.selectByVisibleText("2000");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "2000");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}