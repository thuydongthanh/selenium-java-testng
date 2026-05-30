package testng;

import org.testng.annotations.Test;

public class Topic_06_Priority {
    //Chạy TC theo thứ tự:
        //Set proirity theo số
        //Sắp xếp tên Testcase theo alphabet từ  0->9, a->z
    //=> Dễ dàng quản lý, tìm kiếm, count ra số lượng TC trong 1 class là bao nhiêu

    @Test(priority = 1)
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test(priority = 2)
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    @Test(priority = 3)
    public void TC_03() {
        System.out.println("Run TC 03");
    }

}

