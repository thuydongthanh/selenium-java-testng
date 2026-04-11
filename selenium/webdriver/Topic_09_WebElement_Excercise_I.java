package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_Excercise_I {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Displayed() {
        //IsDislayed: kiểm tra element có thể nhìn thấy
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.id("email"));
        if (emailTextbox.isDisplayed()) {
            System.out.println("Email Textbox is displayed");
            emailTextbox.sendKeys("Automation Testing");
        }  else {
            System.out.println("Email Textbox is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Age Under 18 Radio is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is not displayed");
        }

        WebElement eduTextarea =  driver.findElement(By.id("edu"));
        if (eduTextarea.isDisplayed()) {
            System.out.println("Edu Textarea is displayed");
            eduTextarea.sendKeys("Automation Testing");
        } else {
            System.out.println("Edu Textarea is not displayed");
        }

        WebElement userText = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (userText.isDisplayed()) {
            System.out.println("User Text is displayed");
        } else {
            System.out.println("User Text is not displayed");
        }
    }
    @Test
    public  void TC_02_Enabled() {
        //IsDislayed: kiểm tra element có thể tương tác được ngược lại với read-only (disabled)
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.id("email"));
        if (emailTextbox.isEnabled()) {
            System.out.println("Email Textbox is enabled");
        }  else {
            System.out.println("Email Textbox is displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
        if (ageUnder18Radio.isEnabled()) {
            System.out.println("Age Under 18 Radio is enabled");
        } else {
            System.out.println("Age Under 18 Radio is displayed");
        }

        WebElement eduTextarea =  driver.findElement(By.id("edu"));
        if (eduTextarea.isEnabled()) {
            System.out.println("Education is enabled");
        } else {
            System.out.println("Education is displayed");
        }
        WebElement passwordTextbox = driver.findElement(By.id("disable_password"));
        if (passwordTextbox.isEnabled()) {
            System.out.println("Password is enabled");
        } else {
            System.out.println("Password Radio is displayed");
        }

        WebElement radioButton = driver.findElement(By.id("radio-disabled"));
        if (radioButton.isEnabled()) {
            System.out.println("Radio Button is enabled");
        } else {
            System.out.println("Radio Button is displayed");
        }

        WebElement bioTextarea = driver.findElement(By.id("bio"));
        if (bioTextarea.isEnabled()) {
            System.out.println("Biography is enabled");
        } else {
            System.out.println("Biography is displayed");
        }

    }

    @Test
    public  void TC_03_Selelcted() {
        //Kiểm tra 1 element đã được chọn thành công: Radio/Checkbox/Dropdown
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
        ageUnder18Radio.click();
        WebElement javaCheckbox = driver.findElement(By.id("java"));
        javaCheckbox.click();
        if (ageUnder18Radio.isSelected()) {
            System.out.println("Age under 18 is selected");
        } else {
            System.out.println("Age under 18 is de-selected");
        }
        if (javaCheckbox.isSelected()) {
            System.out.println("Java Checkbox is selected");
        } else {
            System.out.println("Java Checkbox is de-selected");
        }
        javaCheckbox.click();
        if (javaCheckbox.isSelected()) {
            System.out.println("Java Checkbox is selected");
        } else {
            System.out.println("Java Checkbox is de-selected");
        }
    }

    @Test
    public  void TC_04_MailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("thanhthuydongtmu@gmail.com");
        driver.findElement(By.cssSelector("input#new_username")).sendKeys(Keys.TAB);

        //Only number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("1234");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue( driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Only lower text
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue( driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Only upper text
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue( driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Only special character
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@!@@");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue( driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());


        //Only > 8
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Auto12345@");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertFalse( driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse( driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse( driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse( driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse( driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse( driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Check contains username
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("thanhthuy");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);

        Assert.assertTrue( driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue( driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());


    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}