//1. Kiểu dữ liệu: mô phỏng dữ liệu thực tế

package javaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic_01_DataType {
    // Cách khai báo
        //1. Access Modifier: phạm vi truy cập: public/private/protected/default - Kiểu dữ liệu - Tên biến - Giá trị của biến  (Trong / ngoài hàm đều được)
            public char cName = 'b';
        //1.1 - Access Modifier - Kiểu dữ liệu -Tên biến
            public char cAddress;
        //1.2 - Tên biến - Kiểu dữ liệu - Giá trị gán sau (hàm)
            public void clickToElement() {
                cAddress = 'c';
            }
    // Chia thành 2 nhóm kiểu dữ liệu trong
        // Nhóm 1: kiểu dữ liệu nguyên thủy - Primitive Type
            // char: kí tự (character): khởi tạo /gán giá trị nằm trong dấu nháy đơn
            // byte/short/int/long : số nguyên : khởi tạo/gán giá trị không nằm trong dấu gì
                byte bNumber = -120;
                short sNumber = 1200;
                int iNumber = 350000;
                long lNumber = 10000000;
            // float/double : số thực : khởi tạo/gán giá trị không nằm trong dấu gì
                float fNumber = 12.2f;
                double dNumber = 12.2;
            //boolean: logic : khởi tạo/gán giá trị không nằm trong dấu gì
                boolean gender = true;
                boolean gender1 = false;

        // Nhóm 2: Kiểu dữ liệu tham chiếu -  Reference Type - Non-primitive
            //String: chuỗi : khởi tạo/gán giá trị nằm trong dấu nháy kép
                String fullName = "Thanh Thuy";
            //Class
                FirefoxDriver fDrive = new FirefoxDriver();
                Actions actions = new Actions(fDrive);
                Topic_01_DataType topic01 = new Topic_01_DataType();
            //Interface
                WebDriver driver;
                JavascriptExecutor jsExecutor;
            //Array
                String[] studentName = {"Hiền", "Nam"};
                Integer[] studentPhone = {1, 2, 4};
            //List/Set
                List<String> studentAddress = new ArrayList<String>();
            //Map
                Map<String, Integer> zip = new HashMap<String, Integer>();
            //Object
                Object name = "";
                Object phone = 1;
                Object isDisplay = true;

}
