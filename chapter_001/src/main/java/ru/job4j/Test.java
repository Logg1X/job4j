package ru.job4j;

public class Test {

    String str = "";

    public static void main(String[] args) {
        Test test = new Test();
        String a = "ШААЛААШ";
        System.out.println(test.testStr(a));
        System.out.println(test.testAAAA("MMJAADDDDAAAKKKKKKK"));

    }

    public String testAAAA(String a) {
        String result = "";
        int counter = 1;
        for (int j = 0; j < a.length() - 1; j++) {
            char[] c = a.toCharArray();
            if (j + 1 < a.length() - 1 && c[j + 1] == c[j]) {
                counter++;
            } else {
                result = counter > 1 ? result + c[j] + counter : result + c[j];
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







