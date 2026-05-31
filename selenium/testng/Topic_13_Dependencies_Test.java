package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_13_Dependencies_Test {
    //Được ứng dụng khi device các testcase phụ thuộc nhau - những test chạy theo luồng - test sau phụ thuộc vào test trước
    //Tùy theo nhu cầu sử dụng

    @Test
    public void TC_01_Create_New_Product() {
        System.out.println("TC_01_Create_New_Product");

        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_02_View_product() {
        System.out.println("TC_02_View_product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_03_Edit_Product() {
        System.out.println("C_03_Edit_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Move_Product() {
        System.out.println("TC_04_Move_Product");
    }

    @Test(dependsOnMethods = "TC_01_Create_New_Product")
    public void TC_04_Delete_Product() {
        System.out.println("TC_04_Delete_Product");
    }
}
