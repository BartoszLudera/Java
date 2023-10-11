package pl.edu.uj.generics;

import java.util.LinkedList;
import java.util.EmptyStackException;
import java.util.Queue;

public class PriorityQueue<T, Integer> implements Queue<T, Integer> {
    LinkedList<T> listValue = new LinkedList<>();
    LinkedList<Integer> listPriority = new LinkedList<>();

    public void add(T t, Integer priority) {
        listValue.push(t);
        listPriority.push(priority);
    }

    public T get() throws EmptyStackException {
        if (listPriority.isEmpty() || listValue.isEmpty()) {
            throw new EmptyStackException();
        }

        int pos = 0;
        T search;
        for (int i = 0; i < listValue.size(); i++) {
            if ((int) listPriority.get(i) > (int) listPriority.get(pos)) {
                pos = i;
            }
        }
        search = listValue.get(pos);
        listPriority.remove(pos);
        listValue.remove(pos);
        return search;
    }

}
