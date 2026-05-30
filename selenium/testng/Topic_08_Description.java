package testng;

import org.testng.annotations.Test;

public class Topic_08_Description {
    //Tên testcase = hàm/function/method của java
    //Đặt tên theo chuẩn convention của từng ngôn ngữ

    //Description =>Chú thích/diễn giải /note testcase dùng để làm gì khi quá dài không thể đặt thành tên testcase được
    //Dễ đọc code cho người Non-tech biết được chức năng làm gì

    @Test (description = "JIRA#1345-User can create new product and verify created success")
    public void TC_01() {
        System.out.println("Run TC 01");
    }
}
