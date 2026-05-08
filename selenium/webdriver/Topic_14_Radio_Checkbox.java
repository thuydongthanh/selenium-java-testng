package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Radio_Checkbox {

    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        jsExecutor = (JavascriptExecutor) driver;
        //driver.manage().window().setSize(new Dimension(1366, 768));
    }
    //Default: input, select, option
    //Custome: thẻ khác div, li

    @Test
    public void TC_01_Telerik () {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //verify checkbox hoặc radio disabed hay enabled thì lấy element đúng thẻ => nên dùng text để lấy elememt vì text đại diện cho chính element đó
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        //Verify check box/radio selected và deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        //select
        By dualZoneAirby = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        //Scroll 1 đoạn tầm 300px
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        //nếu như chọn thì click
        if (!driver.findElement(dualZoneAirby).isSelected()) {
            driver.findElement(dualZoneAirby).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneAirby).isSelected());

        //de-select
        if (driver.findElement(dualZoneAirby).isSelected()) {
            driver.findElement(dualZoneAirby).click();
        }
        Assert.assertFalse(driver.findElement(dualZoneAirby).isSelected());

        //Radio
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        By twoPetrolBy = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        if (!driver.findElement(twoPetrolBy).isSelected()) {
            driver.findElement(twoPetrolBy).click();
        }
        Assert.assertTrue(driver.findElement(twoPetrolBy).isSelected());

    }

    @Test
    public  void TC_02_Angular() {
        driver.get("https://material.angular.dev/components/radio/examples");
        By summerBy = By.cssSelector("input[value='Summer']");

        if (!driver.findElement(summerBy).isSelected()) {
            driver.findElement(summerBy).click();
        }
        Assert.assertTrue(driver.findElement(summerBy).isSelected());

        driver.get("https://material.angular.dev/components/checkbox/examples");
        By checkedCheckboxBy = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        if (!driver.findElement(checkedCheckboxBy).isSelected()) {
            driver.findElement(checkedCheckboxBy).click();
        }
        Assert.assertTrue(driver.findElement(checkedCheckboxBy).isSelected());

        By imdeterCheckboxBy = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
        if (!driver.findElement(imdeterCheckboxBy).isSelected()) {
            driver.findElement(imdeterCheckboxBy).click();
        }

        Assert.assertTrue(driver.findElement(imdeterCheckboxBy).isSelected());

        if (driver.findElement(checkedCheckboxBy).isSelected()) {
            driver.findElement(checkedCheckboxBy).click();
        }
        Assert.assertFalse(driver.findElement(checkedCheckboxBy).isSelected());

        if (driver.findElement(imdeterCheckboxBy).isSelected()) {
            driver.findElement(imdeterCheckboxBy).click();
        }
        Assert.assertFalse(driver.findElement(imdeterCheckboxBy).isSelected());

    }

    @Test
    public void TC_03_Multiple () {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allItems = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));
        for (WebElement item : allItems ) {
            if (!item.isSelected()) {
                item.click();
                Assert.assertTrue(item.isSelected());
            }
        }

        for (WebElement item : allItems ) {
                Assert.assertTrue(item.isSelected());
        }

        for (WebElement item : allItems ) {
            if (item.isSelected()) {
                item.click();
            }
        }

        for (WebElement item : allItems ) {
            Assert.assertFalse(item.isSelected());
        }

       By heartAtkBy = By.cssSelector("input[value='Heart Attack']");
       if (!driver.findElement(heartAtkBy).isSelected()) {
           driver.findElement(heartAtkBy).click();
       }
       Assert.assertTrue(driver.findElement(heartAtkBy).isSelected());
    }

    @Test
    public void TC_04_Ubuntu() {
        driver.get("https://login.ubuntu.com/");
        //isSelected() chỉ dùng cho input radio, check box, select, các thẻ  khác không áp dụng

        //Tạo 2 locator khó đọc code cho người mới
        By newUserRadioInput = By.cssSelector("input#id_new_user");
        //By newUSerLabel = By.cssSelector("label.new-user");

        //driver.findElement(newUSerLabel).click();

        // dùng duy nhất thẻ input để click và verify dùng js excutor
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(newUserRadioInput));
        Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");
        jsExecutor.executeScript("arguments[0].click()", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
    }

    @Test
    public void TC_05_Google_Docs() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(5000);

        By ctRadio = By.cssSelector("div[aria-label='Cần Thơ']");
        By noodleRadio = By.cssSelector("div[aria-label='Mì Quảng']");
        driver.findElement(ctRadio).click();

        //ko nên dùng kiểu này do phải define nhiều locator
        //Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
        Assert.assertEquals(driver.findElement(ctRadio).getAttribute("aria-checked"), "true");

        //Check
        if (driver.findElement(noodleRadio).getAttribute("aria-checked").equals("false")) {
            driver.findElement(noodleRadio).click();
        }
        Assert.assertEquals(driver.findElement(noodleRadio).getAttribute("aria-checked"), "true");

        //Uncheck
        if (driver.findElement(noodleRadio).getAttribute("aria-checked").equals("true")) {
            driver.findElement(noodleRadio).click();
        }
        Assert.assertEquals(driver.findElement(noodleRadio).getAttribute("aria-checked"), "false");

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}