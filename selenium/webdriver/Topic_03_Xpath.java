package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Xpath {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_Xpath_Technical_02() {
        driver.get("https://live.techpanda.org/");
        //lấy đời cha để tìm ra element duy nhất
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

    }
    @Test
    public  void TC_Xpath_Technical_03() {
        //lấy tuyệt đối (=) =>khi muốn lấy hết giá trị => tốc độ chạy rất nhanh
        // Sử dụng attribute dùng @ phía trước, Không dùng () sau tên thuộc tính => text(), string()
        // Sử dụng hàm thì không được dùng @ phía trước. Dùng () sau tên hàm => @class, @id, @title,..

        //lấy tương đối (,) => khi muốn lấy 1 phần giá trị => dùng hàm contains() => tốc độ quét chậm hơn
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//div[@class='topic-block-body']/p[contains(text(), 'Online shopping is')]")); // lấy 1 phần text
        driver.findElement(By.xpath("//input[contains(@class, 'search-box-text')]"));  // lấy 1 phần attrinute
    }

    @Test
    public  void TC_Xpath_Starts_With() {
        //dùng khi các attribute có 1 đoạn giá trị ở đầu luôn ko thay đổi sau khi reload lại trang
        //Cú pháp:
            //Với attribute => //input[starts-with(@data-spm-anchor-id, 'a2o4n.homepage')]
            //Với text() => //div[starts-with(text(), 'Login')]

        //ends-with() : Xpath không support
    }

    @Test
    public void TC_Xpath_Text() {
        driver.get("https://automationfc.github.io/basic-form/");
        // 1. text()='..' =>=> CÓ DẤU = KHÁC VỚI text()
            // Lấy toàn bộ text trên cùng hàng với thẻ, ko nằm trong child note, ko phải dạng nested text
            //Text có thể nằm index ở đầu/giữa/cuối đều có thể lấy được (so với thẻ con khác)
            //Lấy text tuyệt đối => ko có khoảng trắng/ xuống dòng/ tab ở đầu hoặc cuối chuỗi

                driver.findElement(By.xpath("//h5[text()='Michael Jackson']"));

        // 2. contains[text(), '..']
            //Lấy một phần text trên cùng node chứa
            //Dạng nested text nhưng text phải nằm index đầu, idex giữa và cuối thì ko lấy được
            //Text trong child note thì ko lấy được
            //Text có khoảng trắng/xuống dòng/tab đầu cuối text đều lấy được
            //Ko dùng contains(text()='') mà dùng  contains(text(), '')

            driver.findElement(By.xpath("//h5[contains(text(), 'Michael Jackson')]"));

        // 3. contains[., '..'] và contains[string(), '..']
            //Text ở dòng dưới (thẻ con) hoặc nằm cùng hàng với thẻ chứa nó
            //Hầu như lấy được hết tất cả các trường hợp

            driver.findElement(By.xpath("//h5[contains(., 'Michael Jackson')]"));
            driver.findElement(By.xpath("//h5[contains(string(), '- living in Viet Nam')]"));

        // 4. Text trong cùng dòng có cả nháy đôi và đơn => concat() để xử lý
            driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]"));

        //Text lồng nhau = Nested text
    }

    @Test
    public void TC_Xpath_AND_OR() {
        // AND tuyệt đối
            driver.findElement(By.xpath("//input[@type='email'and @id='email']"));
        // OR tương đối
            driver.findElement(By.xpath("//button[@title='Subscribe'or @id='Login']"));
    }

    @Test
    public void TC_Xpath_Not() {
        // Phủ định lại element có style rõ ràng hơn, chỉ dùng khi có 2 element
            driver.findElement(By.xpath("//div[not(@style='display: none')]/div[@class='raDiv']"));
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}