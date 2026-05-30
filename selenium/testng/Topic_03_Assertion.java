package testng;

//TestNG Assertions: kiểm tra testcse PASS hay FAIL
//Nếu như trong 1 TC chỉ thực hiện action thì ko biết được TC đó chạy đúng hay sai

//AAA Patternt : Arrange - Action - Assert
//Arrange: khởi tạo object, class, data...
//Action: Click, gettext, sendkey, query database
//Assert: kiểm tra action đúng mong đợi hay không

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assertion {
    WebDriver driver;

    @Test
    public void assertion() {
        //assertTrue: Kiểm tra 1 điều kiện mong đợi trả về là đúng => áp dụng các hàm trả về kiểu boolean: isDisplayed, isEnabled,isSelected, isMultiple, User Defined
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        //assertFalse: Kiểm tra 1 điều kiện mong đợi trả về là sai
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isEnabled());

        //assertEquals: Kiểm ytat 1 điều kiện mong đợi bằng điều kiện thực tế => getText, getAttribute, getCss, getTitle, getUrl
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(), "");

    }

}
