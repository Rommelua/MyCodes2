package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static class CustomizedComparator<T> implements Comparator<T> {
        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        private Comparator<T>[] comparators;

        @Override
        public int compare(T o1, T o2) {
            for (int i = 0; i < comparators.length; i++) {
                int result = comparators[i].compare(o1, o2);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        }
    }
}
