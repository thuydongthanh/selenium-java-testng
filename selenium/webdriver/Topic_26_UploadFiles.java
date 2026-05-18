package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_26_UploadFiles {

    WebDriver driver;
    //Lấy đường dẫn tương đối
    String uploadFilesPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    //Định nghĩa tên file
    String firstImage = "a.jpg";
    String secondImage = "b.jpg";
    String thirdImage = "c.jpg";
    String forthImage = "c.jpg";

    //Định nghĩa đường dẫn của file
    String firstImagePath = uploadFilesPath + firstImage;
    String secondImagePath = uploadFilesPath + secondImage;
    String thirdImagePath = uploadFilesPath + thirdImage;
    String forthImagePath = uploadFilesPath + forthImage;

    @BeforeClass //chạy 1 lần trước tất cả test case
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Upload_Files_Single() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //Chuển qua máy window khác chạy đc
        //Chuyển qua 1 máy macOS chạy được
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(secondImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thirdImagePath);

        //verify hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ firstImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ secondImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thirdImage +"']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement button: startButton) {
            button.click();
            Thread.sleep(1000);
        }

        //Upload xong text chuyen qua link => verify link
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ firstImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ secondImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ thirdImage +"']")).isDisplayed());
    }

    @Test
    public void TC_02_Upload_Files_Multiple() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        //Chuển qua máy window khác chạy đc
        //Chuyển qua 1 máy macOS chạy được
        driver.findElement(By.cssSelector("input[type='file']"))
                .sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath + "\n" + forthImagePath);

        //verify hien thi
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ firstImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ secondImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thirdImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ forthImage +"']")).isDisplayed());

        List<WebElement> startButton = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement button: startButton) {
            button.click();
            Thread.sleep(1000);
        }

        //Upload xong text chuyen qua link => verify link
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ firstImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ secondImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ thirdImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+ forthImage +"']")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}