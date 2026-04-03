package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Css {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.get("https://live.techpanda.org/");
    }

    @Test
    public void TC_01_Css() {
        //Cú pháp css thông thường: tag name[attribute='value']
        driver.findElement(By.cssSelector("input[id='search']"));

        //Đi xuống 1 node con: div>input[id='search']

        //Đi bất kì node con bên trong: ul[class='form-list'] input[id='email'] => dùng dấu ' '

        //ID: dùng dấu # => input#email , #email

        //Class: dùng dấu .  => input.classEmail, .classEmail
            // nếu giá trị của class phân tách bởi khoảng trắng thì có thể lấy 1 phần hoặc toàn bộ (lấy toàn bộ thì .class1.class2)
                // div.col2.registered-user

        //Attribute: input[name='email']

        //And: input[id='email'][title='Email Address']

        //Or: input[id='email'],[id='pass']

        //NOT: input:not(#email) , input:not(id='email'), ul:not(.form-list)

        //Contains: input[placeholder*='entire']

        //Starts-with : input[placeholder^='Search entire']

        //Ends-width: input[placeholder$='here...']

        //Following-sibling

        // preceding, parent, ancestor, text => css ko support

        //index: nth-of-type, nth-child, first-of-type, last-of-type, first-child, last-child,...

    }
    @Test
    public  void TC_02_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}