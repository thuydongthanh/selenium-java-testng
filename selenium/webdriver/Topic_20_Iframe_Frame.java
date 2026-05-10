package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Iframe_Frame {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Iframe_Form_Site() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("img[alt='Campus Safety Survey']")).click();
        Thread.sleep(4000);


        //Switch qua iframe
        //Indes: page hiện tại có nhiều iframe/frame
        //Frame/iframe đầu tiên sẽ có index = 0
        //Khi thêm mới/update lại/ xóa bớt đi thì đổi index của các iframe
        //driver.switchTo().frame(0);

        //Id hoặc name: page có iframe /frame có id hoặc name
        //driver.switchTo().frame("frame-one85593366");

        //WebElment có thể cover cả hai cách trên
        driver.switchTo().frame(driver.findElement(By.cssSelector("#formTemplateContainer>iframe")));

        //Element thuộc trang HTML B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Freshman");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("North Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(3000);

        //Iframe A => Iframe B => Iframe C
        //Muốn từ Iframe C quay lại Iframe B => dùng hàm parentFrame
        //Muốn từ Iframe B quay lại Iframe A => dùng hàm defaultContent
        driver.switchTo().defaultContent();

        // Driver đã quay lại A
        driver.findElement(By.cssSelector("a.fs-btn--transparent-kashmir")).click();

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");

        // A qua B
        driver.switchTo().frame(0);

        //B qua C
        driver.switchTo().frame(0);

        //C quay lại B
        driver.switchTo().parentFrame();

        //B quay lại A
        driver.switchTo().defaultContent();
    }

    @Test
    public  void TC_02_Iframe_ToiDiCodeDao() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        By iframeElement = By.cssSelector("iframe[title='fb:page Facebook Social Plugin']");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(iframeElement).isDisplayed());
        driver.switchTo().frame(driver.findElement(iframeElement));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']//parent::div//following-sibling::div")).getText(), "393,860 followers");
    }

    @Test
    public void TC_03_Frame() {
        driver.get("https://now.hdfc.bank.in/retail-app/");

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}