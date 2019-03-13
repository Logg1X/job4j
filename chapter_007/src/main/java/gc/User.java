package gc;

import java.util.Date;
import java.util.stream.IntStream;

public class User {
    private String name;
    private Object[][] arr = new Object[5][2];


    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public static void info() {
        Runtime runtime = Runtime.getRuntime();
        int mb = 1024;
        System.out.println("Used memory = " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free memory = " + (runtime.freeMemory()) / mb);
        System.out.println("Total memory = " + (runtime.totalMemory()) / mb);
        System.out.println("Max memory = " + (runtime.maxMemory()) / mb);
    }

    public static void info(boolean a) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Free memory = " + (runtime.freeMemory()));
    }

    public static void main(String[] args) {
        info();
        System.out.println("Start");
        info();
        IntStream.range(0, 10000).forEach(value -> {
//            System.gc();
            User user0 = new User(String.valueOf(value));
            info(true);
        });
        User.info();
        System.out.println("Finish");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("##############################################");
    }
}