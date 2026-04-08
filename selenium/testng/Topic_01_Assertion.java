package testng;

import org.testng.Assert;

public class Topic_01_Assertion {
    public static void main(String[] args) {
        //3 hàm chính để kiểm tra tính đúng đắn của dữ liệu
        boolean gender = true;
        //Kiểm tra dữ liệu nó phải ĐÚNG
        Assert.assertTrue(gender);
        //Kiểm tra dữ liệu nó phải SAI
        Assert.assertFalse(3>5);
        Assert.assertFalse(false);

        //Kiểm tra dữ liệu nó bằng với mong đợi (ACTUAL - EXPECTED)
        //Kiểm tra 2 thứ: kiểu dữ liệu giống nhau và Giá trị của dữ liệu bằng nhau
        Assert.assertEquals(5,"THUY");
    }
}
