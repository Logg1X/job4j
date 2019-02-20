package ood.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuTest {


    @Test
    public void whenCreateStructureMenuThanShowAllStructure() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Component c0 = new ComponentImpl("Задача 1");
        Component e1 = new ComponentImpl("Задача 1.1");
        Component e2 = new ComponentImpl("Задача 1.1.1");
        Component e3 = new ComponentImpl("Задача 1.1.2");
        Component e4 = new ComponentImpl("Задача 1.2");
        Component e5 = new ComponentImpl("Задача 1.2.1");
        Component e6 = new ComponentImpl("Задача 1.2.2");
        Component e7 = new ComponentImpl("Задача 1.1.1.1");
        Component e8 = new ComponentImpl("Задача 1.1.1.1.1");
        Component e9 = new ComponentImpl("Задача 1.2.1.1");
        Menu menu = new Menu();
        menu.getList().add(c0);
        c0.getList().add(e1);
        e1.getList().add(e2);
        e1.getList().add(e3);
        menu.getList().add(e4);
        e4.getList().add(e5);
        e4.getList().add(e6);
        e2.getList().add(e7);
        e7.getList().add(e8);
        e5.getList().add(e9);
        menu.showMenu(menu.getList(), 1);

        String expected = "--Задача 1\r\n----Задача 1.1\r\n------Задача 1.1.1\r\n--------Задача 1.1.1.1\r\n----------Задача 1.1.1.1.1\r\n"
                + "------Задача 1.1.2\r\n--Задача 1.2\r\n----Задача 1.2.1\r\n------Задача 1.2.1.1\r\n----Задача 1.2.2\r\n";
        assertThat(out.toString(), is(expected));
    }
}