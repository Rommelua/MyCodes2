package com.javarush.task.task04.task0432;

/* 
Хорошего много не бывает
Ввести с клавиатуры строку и число N больше 0.
Вывести на экран строку N раз используя цикл while. Каждое значение с новой строки.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int a = Integer.parseInt(reader.readLine());
        while (a-- > 0)
            System.out.println(s);
    }
}
