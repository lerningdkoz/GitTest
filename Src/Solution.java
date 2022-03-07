package PACKAGE_NAME;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static String number1 = "";
    public static String number2 = "";
    public static String result = "";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int i = 0;
        while (i != 1000) {
            int int1 = (int) (Math.random() * 10);
            int int2 = (int) (Math.random() * 10);
            if ((int1 == 0 || int2 == 0) && i == 0) i = 0;
            else {
                number1 += String.valueOf(int1);
                number2 += String.valueOf(int2);
                i++;
            }
        }

        System.out.println("Число 1: " + number1);
        System.out.println("Число 2: " + number2);
        System.out.println("Результат умножения: " + GetRes(getMultNumber(number1, number2)));
        System.out.println("Время работы программы: " + (System.currentTimeMillis() - start) + " милисекунд(a/ы)");
    }

    public static ArrayList<String> getMultNumber(String num1, String num2) {
        String result = "";
        int checkRes;
        int promRes;
        int nakonecto = 0;
        String zero = "";
        ArrayList<String> endRes = new ArrayList<>();
        for (int i = num2.length() - 1; i >= 0; i--) {
            promRes = 0;
            result = "";
            for (int j = num1.length() - 1; j >= 0; j--) {
                checkRes = Character.getNumericValue(num2.charAt(i)) * Character.getNumericValue(num1.charAt(j)) + promRes;
                if (j == 0) {
                    result = Integer.toString(checkRes) + result;
                } else {
                    result = Integer.toString(checkRes % 10) + result;
                    promRes = (int) checkRes / 10;
                }
            }
            endRes.add(result + zero);
            zero += "0";
        }

        return GetReadyList(endRes);
    }

    public static ArrayList<String> GetReadyList(ArrayList<String> endRes) {
        Collections.reverse(endRes);
        ArrayList<String> list = new ArrayList<>();
        for (String el : endRes) {
            String s = "";
            for (int j = 0; j < endRes.get(0).length() - el.length(); j++) {
                s += "0";
            }
            list.add(s + el);
        }
        endRes.clear();
        return list;
    }

    public static String GetRes(ArrayList<String> list) {
        list = getMultNumber(number1, number2);
        String res = "";
        int resInt;
        int ostatok = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            String el1 = list.get(i);
            String el2 = list.get(i + 1);
            for (int j = el1.length() - 1; j >= 0; j--) {
                if (el1.length() > el2.length()) list.set(i + 1, "0" + el2);
                resInt = Character.getNumericValue(el1.charAt(j)) + Character.getNumericValue(el2.charAt(j)) + ostatok;
                if (j == 0) {
                    res = Integer.toString(resInt) + res;
                } else {
                    res = Integer.toString(resInt % 10) + res;
                    ostatok = resInt / 10;
                }
            }
            list.set(i + 1, res);
            res = "";
        }
        return list.get(list.size() - 1);
    }
}
