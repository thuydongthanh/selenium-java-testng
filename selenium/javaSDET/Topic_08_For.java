package javaSDET;

import java.util.ArrayList;
import java.util.List;

public class Topic_08_For {

    public static void main(String[] args) {
        // Biểu thức vòng lặp (loop)
        int number = 100;

        //for classic - iterator
        for(int i = 0; i < number ; i++) {
            System.out.println(i);
        }

        //Collection: List/Set/Queue/Map

        List<String> name = new ArrayList<String>();
        name.add("Manual Testing");
        name.add("Automaton Testing");
        name.add("UI Testing");
        name.add("API Testing");

        //For-each
        for (String a: name) {
           if (a.equals("Manual Testing")) {
               System.out.println(a);
           }
        }

        int i = 1000;

        //do-while: ít nhất chạy 1 lần
        do {//Action trước
            System.out.println(i);
            i++;
        } while (i < number);// điều kiện sau

        //while
        while (i < number) {
            System.out.println(i);
            i++;
        }
    }
    //break thoát khỏi điều kiện, tiêt kiệm thời gian hơn

}
