package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_11_Default_Dropdown_Excersise {

    WebDriver driver;
    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Dropdown()  {
        driver.get("https://rode.com/en-au/support/where-to-buy");

        select = new Select(driver.findElement(By.cssSelector("select#country")));
        //Kiểm tra multiple select hay không
        Assert.assertFalse(select.isMultiple());

        select.selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> dealears = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));

        Assert.assertEquals(dealears.size(), 16);

      for (WebElement dealer : dealears) {
          System.out.println(dealer.getText());
      }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}