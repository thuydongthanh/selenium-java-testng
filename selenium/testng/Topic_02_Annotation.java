package testng;

import org.testng.annotations.*;

//TestNG Anotation: chỉ dẫn của TestNG để hướng dẫn cho hàm có nhiệm vụ làm gì, sẽ biết hàm đó là test case để run
    //Chạy theo thứ tự trong File RunTest: Suite (Chỉ chạy duy nhất 1 lần cho tất các các test) -> Test(chạy 1 lần cho mỗi test)
    // -> Class -> Method(chạy mỗi testcase một lần cho từng testcase) -> TC
    //Run Before Suite
        //Run Before Test
            //Run Before Class
                //Run Before Method
                    //Run TC 01
                //Run After Method
                //Run Before Method
                    //Run TC 02
                //Run After Method
                //Run Before Method
                    //Run TC 03
                //Run After Method
            //Run After Class
        //Run After Test
    //Run After Suite

public class Topic_02_Annotation {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Run Before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Run After Suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Run Before Class");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Run After Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Run Before Test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Run After Test");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Run Before Method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Run After Method");
    }

    @Test
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    @Test
    public void TC_03() {
        System.out.println("Run TC 03");
    }
}
