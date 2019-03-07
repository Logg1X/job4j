package ru.job4j;

public class Test {

    String str = "";

    public static void main(String[] args) {
        Test test = new Test();
        String a = "ШААЛААШ";
        System.out.println(test.testStr(a));
        System.out.println(test.testAAAA("MMJ AADDDDAAAKKKKKKK"));

    }

    public String testAAAA(String a) {
        String result = "";
        int counter = 1;
        int i = 0;
        while (i < a.length() - 1) { // AADDDDAAA
            result = result + String.valueOf(a.charAt(i));
            while (i != a.length() - 1 && a.charAt(i) == a.charAt(++i)) {
                counter++;
            }
            if (counter > 1) {
                result = result + String.valueOf(counter);
                counter = 1;
            }
        }
        return result;
    }

    public boolean testStr(String a) {
        boolean result = false;
        int c = a.length() - 1;
        String b = a.toLowerCase();
        for (int i = 0; i < a.length(); i++) {
            if (b.charAt(i) == b.charAt(c--)) {
                result = true;
            } else {
                result = false;
                break;
            }

        }
        return result;
    }


}







