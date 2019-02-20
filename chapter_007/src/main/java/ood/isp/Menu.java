package ood.isp;

import java.util.ArrayList;
import java.util.List;

public class Menu {
   private List<Component> elementList;


    public Menu() {
        this.elementList = new ArrayList<>();
    }

    public List<Component> getList() {
        return elementList;
    }

    public void showMenu(List<Component> el, int i) {
        for (Component element : el) {
            for (int x = 0; x < i; x++) {
                System.out.print("--");
            }
            System.out.println(element.getName());
            if (!element.getList().isEmpty()) {
                showMenu(element.getList(), i + 1);
            }
        }
    }
}
