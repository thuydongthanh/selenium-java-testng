package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;

public class Topic_10_Textbox_Textarea {

    WebDriver driver;
    String firstName, lastName, emailAddress, password, fulllName;
    Random rand = new Random();

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        firstName = "Thanh";
        lastName = "Thuy";
        fulllName = firstName + " " + lastName;
        emailAddress = "Thanhthuy" + rand.nextInt(99999) + "@gmail.com";
        password = "123456789";
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        //Register
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        //verify tương đối
        Assert.assertTrue(informationText.contains(fulllName) && informationText.contains(emailAddress));

        //Check thông tin trong edit
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);

        //Feedback sản phẩm
        driver.findElement(By.cssSelector("nav#nav .nav-1")).click();
        driver.findElement(By.cssSelector("img#product-collection-image-3")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Very Good\n"+"So Hot\n"+ "So Cheap");
        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best Phone");
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(fulllName);
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");

        //Logout
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("#header-account .last")).click();

        //Back home
        Thread.sleep(6000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");

        //Login account đã đăng ký
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/index/");
    }

    @Test
    public void TC_02_OrangeHrm () throws InterruptedException {
        String usernameOrangeHrm, passwordOrangeHrm,phoneOrangeHrm, commentOrangeHrm, newUsername, newPassword;

        usernameOrangeHrm = "Admin";
        passwordOrangeHrm = "admin123";
        phoneOrangeHrm = "0968765431";
        commentOrangeHrm =
                "very good\n" +
                "very good\n" +
                "very good";

        newUsername = "newadmin" + rand.nextInt(100);
        newPassword = "newadmin123";

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(usernameOrangeHrm);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(passwordOrangeHrm);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[text()='PIM']/parent::a/parent::li/a")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[text()='Add Employee']/parent::li")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);
        String employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
        driver.findElement(By.xpath("//input[@type='checkbox']/parent::label")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(newUsername);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(10000);

        //Verify Pesonal Detail
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(phoneOrangeHrm);
        driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys(commentOrangeHrm);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5000);

        driver.findElement(By.cssSelector(".oxd-icon.bi-pencil-fill")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), phoneOrangeHrm);
        Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), commentOrangeHrm);

        //Logout
        driver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(10000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        //Login lại acc đã edit
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys(newUsername);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[text()='My Info']/parent::a/parent::li/a")).click();
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeId);
        driver.findElement(By.xpath("//a[text()='Immigration'] ")).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(".oxd-icon.bi-pencil-fill")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), phoneOrangeHrm);
        Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), commentOrangeHrm);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}