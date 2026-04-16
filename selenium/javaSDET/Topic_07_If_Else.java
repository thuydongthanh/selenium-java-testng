package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_07_If_Else {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String browserName = "IE";
        WebDriver driver;
        //Biểu thức điều kiện
        //if: chỉ dùng đúng 1 điều kiện
        if(browserName.equals("IE")) {
            System.out.println("Click to submit button");
        }

        //if-else
        if (osName.contains("Windows")) {
            System.out.println("Window OS");
        } else  {
            System.out.println("Mac or Linux OS");
        }

        System.out.println(osName);

        //if-else-if-else: dùng cho nhiều hơn 2 điều kiện

        if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        //switch-case
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver = new ChromeDriver();
                break;
            default:
                driver = new EdgeDriver();
                break;
        }

    }


}
