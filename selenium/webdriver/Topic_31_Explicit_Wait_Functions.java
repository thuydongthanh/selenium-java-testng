package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Topic_31_Explicit_Wait_Functions {

    //Cách khai báo
        //driver, Duration timeout => măc định 0,5s tìm lại
        //driver, Duration timeout, Duration sleep (polling time)

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300)); //custom polling time
    }

    @Test
    public void TC_01_() {
        //Wait cho element không hiển thị không còn trong HTML (trươc đó là có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Wait cho element ko hiển thị (còn/ko còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //Wait cho element được hiển thị (có trên UI hoặc có trong HTML)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        //Wait cho element được phép click(button/link/radi/checkbox...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe("https://automationfc.github.io/dynamic-loading/"));

        //Wait cho URL của page tương  đối
        explicitWait.until(ExpectedConditions.urlContains("dynamic-loading"));

        //Wait cho URL page thỏa mãn biểu thức Regex
        explicitWait.until(ExpectedConditions.urlMatches("*$^...."));

        //Wait cho hàm js trả về kiểu dữ liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return argument[0].validationMessage"));

        //Wait cho Alert có xuất hiện trong HTML
        explicitWait.until(ExpectedConditions.alertIsPresent());

        //Wait cho title cảu page tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs("Title"));

        //Wait thỏa mãn cả hai điều kiện (AND)
        explicitWait.until(ExpectedConditions.and(ExpectedConditions.urlContains(""), ExpectedConditions.titleIs("")));

        //Wait thỏa mãn một trong hai điều kiện (OR)
        explicitWait.until(ExpectedConditions.or(ExpectedConditions.urlContains(""), ExpectedConditions.titleIs("")));

        //Wait cho elemet có xuât hiện trong HTML
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Wait cho một element có thuộc tính chứa một giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector(""), "class", "email"));

        //Wait cho một element có thuộc tính bằng  một giá trị nào đó
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "class", "email"));

        //Wait cho một element có thuộc tính không được rỗng/ null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("")), "class"));

        //Wait  cho một element có thuộc tính trong DOM bằng giá trị nào đó
        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("")),
                "baseURI", "https://automationfc.github.io/dynamic-loading/"));

        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("")), "innerHTML", ""));

        //Wait cho một ELement đã được chọn thành công (Checkbox/Radio/Dropdown Item)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //Wait cho elemnet dã được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), true));

        //Wait cho element chưa được chọn thành công
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""), false));

        //Wait cho frame/iframe xuất hiện và switch vào
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

        //Wait cho một đoạn lệnh JS được thực thi không trả về bất kì exception nào
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].click()"));

        //Phủ định lại điều kiện wait
        explicitWait.until(ExpectedConditions.not(ExpectedConditions.javaScriptThrowsNoExceptions("return arguments[0].click()")));

        //Wait cho 1 list element bằng bao nhiêu item
        List<WebElement> listElement = explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10)); // vừa wait vừa tìm và trả về element
        Assert.assertEquals(listElement.size(), 10);

        explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 10)); //ít item hơn
        explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 10)); //nhiều item hơn

        //Wait số lượng window tab bằng bao nhiêu
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        //Wait cho 1 đoạn text bằng tuyệt đối => dùng trước hàm gettext
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), ""));
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector(""), Pattern.compile("a*b")));

        //Wait cho 1 element hay bị change/refresh lại/update lại
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))));

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}