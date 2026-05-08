package webdriver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {

    WebDriver driver;
    WebElement element;

    @Test
    public void TC_01_WebElement() {

        //Dùng 1 lần
        driver.findElement(By.xpath("")).click();

        element = driver.findElement((By.xpath("")));

        //Click vào element dạng: button/checkbox/radio/link/image/icon
        element.click();

        //Nhập liệu vào các element dạng textbox/textarea/dropdown(edit)
        element.clear(); //xóa dữ liệu trước khi sendkey => clear và sendkey luôn đi kèm với nhau
        element.sendKeys("Truyền vào string");
        element.sendKeys(Keys.ENTER);

        driver.findElement(By.id("")).findElement(By.cssSelector(""));

        //Có tác dụng với form
        element.submit();

        //Áp dụng cho tất cả các loại element => Kiểm tra 1 loại element có hiển thị hay không => Size > 0: chiều rộng và chiều cao > 0
        element.isDisplayed();
        Assert.assertTrue(element.isDisplayed());

        //Áp dụng cho 3 loại: checkbox/radio/dropdown => Kiểm tra 1 element đã được chọn rồi hay chưa chọn
        element.isSelected();

        //Áp dụng cho tất cả các loại => Kiểm tra 1 element có bị disabled hay không
        element.isEnabled();

        element.getCssValue("Truyền vào thuộc tính css");
        element.getText(); //Lấy element chứa text: Link, button, header,..
        element.getAttribute("placeholder");//lấy ra giá trị thuộc tính
        element.getSize(); //Lấy kiểm tra chiều rộng/cao của element
        element.getLocation(); //Lấy ra vị trí của element so với viewport trên page
        Rectangle rectangle = element.getRect(); //Tổng hợp của cả size và location
        //Size
        rectangle.getWidth();
        rectangle.getHeight();
        rectangle.getDimension();
        //Location
        rectangle.getX();
        rectangle.getY();
        rectangle.getPoint();

        //Lấy ra thẻ html của element đó
        element.getTagName();
        //Element A
        String tagname = driver.findElement(By.cssSelector("#FirstName")).getTagName();
        //Element B
        driver.findElement(By.xpath("//" +tagname+ "input[@id='LastName']"));

        element.getAccessibleName();

        element.getAriaRole();

        element.getDomAttribute("");

        element.getDomProperty("");

        element.getShadowRoot();

    }


}