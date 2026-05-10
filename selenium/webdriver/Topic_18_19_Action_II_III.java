package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class Topic_18_19_Action_II_III {

    WebDriver driver;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys keys;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
        keys = osName.startsWith("Windows") ?  Keys.CONTROL : Keys.COMMAND;
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Click_And_Hold() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        //driver.findElement(By.cssSelector("ol#selectable>li")); // Lấy Element duy nhất đầu tiên để thao tác
        //driver.findElements(By.cssSelector("ol#selectable>li")); //Lấy tất cả Element

        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 30);

        actions.clickAndHold(allNumber.get(0)) //CLick vào số 1 và gữi chuôt
                .moveToElement(allNumber.get(3)) // Di chuột đến số 4
                .release() // Nhả chuột trái ra  và kết thúc sự kiện clickandhold
                .perform(); // Thực thi tất cả các câu lệnh trên - Nếu ko có thì ko thực thi
        Thread.sleep(2000);

        List<WebElement> allItemSelected = driver.findElements(By.cssSelector(" li.ui-selected"));
        Assert.assertEquals(allItemSelected.size(), 4);

    }

    @Test
    public  void TC_02_Click_And_Hold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        Assert.assertEquals(allNumber.size(), 30);

        //Nhấn phím Ctrl xuống nhưng chưa nhả ra
        actions.keyDown(keys).perform();

        actions.click(allNumber.get(0))
            .click(allNumber.get(3))
            .click(allNumber.get(11))
            .click(allNumber.get(19))
            .pause(3000)
            .perform();

        //Nhả phím control
        actions.keyUp(keys);

        List<WebElement> allItemSelected = driver.findElements(By.cssSelector(" li.ui-selected"));
        Assert.assertEquals(allItemSelected.size(), 4);
    }

    @Test
    public void TC_03_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.xpath("//button[text()='Double click me']")));
        Thread.sleep(500);
        actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        Thread.sleep(2000);

        By quitElement = By.xpath("//span[text()='Quit']");
        Assert.assertTrue(driver.findElement(quitElement).isDisplayed());
        actions.moveToElement(driver.findElement(quitElement)).perform();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover ")).isDisplayed());
        actions.click(driver.findElement(quitElement)).perform();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        Assert.assertFalse(driver.findElement(quitElement).isDisplayed());
    }

    @Test
    public void TC_05_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        actions.dragAndDrop(driver.findElement(By.cssSelector("div#draggable")), driver.findElement(By.cssSelector("div#droptarget"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
        Assert.assertEquals(Color.fromString(driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color"))
                .asHex(), "#03a9f4");
    }

    @Test
    public void TC_06_Drag_And_Drop_HTML5() throws IOException, InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        //Read content file dragAndDrop.js
        String jqueryDragAndDrop = getContentFile(projectPath +  "\\dragDrop\\dragAndDrop.js");

        //Drag A to B
        jsExecutor.executeScript(jqueryDragAndDrop);
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "A");

        //Drag B to A
        jsExecutor.executeScript(jqueryDragAndDrop);
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b")).getText(), "B");
    }

    @Test
    public void TC_05_Scroll_To_Element() {
        //Lỗi trên firefox nhung trình duyệt Chrome và Edge chạy bình thường
        driver.get("https://live.techpanda.org/index.php/about-magento-demo-store/");
        actions.scrollToElement(driver.findElement(By.cssSelector("input#newsletter"))).perform();
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}