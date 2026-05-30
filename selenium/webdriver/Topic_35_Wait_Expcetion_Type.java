package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_35_Wait_Expcetion_Type {

    //Đọc thông báo lỗi/loaid exception để biết nó là của loại wait nào -> đưa ra phương án xử lí nhanh hơn

        //1. StaleElementReferenceException : Do element không còn tồn tại trong HTML, trước đó thì có nhưng sau không còn do refresh trang chẳng hạn

        //2. NoSuchElement (Implicit Wait) : Ko tìm thấy element

        //3. TimeoutException (Explicit) : có log kèm theo hàm sử dụng

        //4. TimeoutException (Fluent) : Expected condition failed: waiting for (...) tried for (...) seconds with (...) miliseconds interval)

}