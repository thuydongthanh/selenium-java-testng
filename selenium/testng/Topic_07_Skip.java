package testng;

//Skip không chạy 1 testcase bất kì

import org.testng.annotations.Test;

public class Topic_07_Skip {

    @Test
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test(enabled = false)
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    //@Test => comment lại ko chạy testcase
    public void TC_03() {
        System.out.println("Run TC 03");
    }
}
