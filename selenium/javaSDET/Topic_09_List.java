package javaSDET;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;

public class Topic_09_List {
     public static void main(String[] args) {
        //java Collection
//         RemoteWebDriver driver;
//
//         driver = new ChromeDriver();
//         driver = new FirefoxDriver();
//         driver = new EdgeDriver();

//         FirefoxDriver ffDriver = new FirefoxDriver();

//       ArrayList<String> address = new ArrayList<>();
//
//       Vector<Float> studentPoint = new Vector<>();
//
//       LinkedList<Integer> studentAge = new LinkedList<>();
//
//       Stack<Boolean> status = new Stack<>();

//       List<String> studentname = new Stack<>();

         List<String> address = new ArrayList<>();
         address.add("Ha Noi");
         address.add("Ho Chi Minh");
         address.add("Hai Phong");
         address.add("Can Thơ");
         //Lấy ra element cụ thể
         System.out.println(address.get(0));
         System.out.println(address.get(3));
         System.out.println(address.get(2));

         //lấy ra toàn bộ
         for (int i = 0; i< address.size(); i++ ) {
             System.out.println(address.get(i));
         }

         for (String a: address) {
             System.out.println(a);
         }

    }
}
