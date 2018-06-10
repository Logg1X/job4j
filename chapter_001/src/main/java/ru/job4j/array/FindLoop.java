package ru.job4j.array;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoop {
    /**
     * Возвращает индекс массива если массив содержит нужный элемент.
     *
     * @param data массив в котором будет производиться поиск.
     * @param el   элемент поиска (в данном случае число).
     * @return индекс элемента.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }
}
