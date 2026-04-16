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

public class Topic_12_Custom_Dropdown {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Jquery_Dropdown() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInDropdown("span#speed-button", "ul#speed-menu>li>div", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");

        selectItemInDropdown("span#number-button", "ul#number-menu>li>div", "10");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");

        selectItemInDropdown("span#salutation-button", "ul#salutation-menu>li>div", "Other");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Other");

    }

    @Test
    public void TC_02_React_Dropdown() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.dropdown", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Matt");
        selectItemInDropdown("div.dropdown", "div.item>span", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Justen Kitsune");
    }

    @Test
    public void TC_03_VueJs_Dropdown() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("div.btn-group", "ul.dropdown-menu>li>a", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group")).getText(), "First Option");
    }

    @Test
    public void TC_04_Editable() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        inputItemInDropdown("input.search", "div.item>span", "Afghanistan");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Afghanistan");
        inputItemInDropdown("input.search", "div.item>span", "Algeria");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Algeria");
    }

    private void selectItemInDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        //Hành vi để thao tác lên dropdown
        //1- Chờ cho dropdown có thể thao tác lên được (clickable)
        //Wait: Selenium có 3 cách: Implicit Wait, Webdriver wait, Fluent wait và 1 hàm Java Theard sleep
        //Implicit Wait: wait ngầm định cho việc tìm element: findelements và findelement
        //Webdriver wait: wai tường minh cho element với 1 điều kiện rõ ràng
        //Fluent wait: có thể sửa thời gian polling lại được (thời gian lặp lại điều kiện để tìm)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click(); // trả luôn ra element để click

        //2- Click vào element nào để nó xổ ra dropdown
        //driver.findElement(By.cssSelector(parentCss)).click();
        Thread.sleep(2000);

        //3- Chờ cho tất cả item được load ra
        //presence: không quan tâm có hiển thị giao diện hay không, kiểm tra có trong html
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss))); // trả luôn ra list element

        //4- Tìm cái item nào đúng với mong đợi
        //List<WebElement> allItems = driver.findElements(By.cssSelector(childCss));
        for (WebElement item: allItems) {
            //5- Click lên item đó
            if (item.getText().equals(textItem)) { //getText chỉ lấy text của item visible
                item.click();
                break;
            }
        }
    }

    private void inputItemInDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        WebElement dropdownTextbox =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parentCss)));
        dropdownTextbox.click();
        dropdownTextbox.sendKeys(textItem); //sendkey với những dropdown cho nhập value
        Thread.sleep(2000);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss))); // trả luôn ra list element
        for (WebElement item: allItems) {
            if (item.getText().equals(textItem)) { //getText chỉ lấy text của item visible
                item.click();
                break;
            }
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}