package pl.edu.uj.generics;

import org.springframework.util.Assert;

public interface PriorityQueueTest {

    @Test
    public void IntTest() {
        PriorityQueue<Integer, Integer> queueInt = new PriorityQueue<>();
        queueInt.add(8, 1);
        queueInt.add(9, 1);
        queueInt.add(11, 4);
        queueInt.add(523, 2);
        queueInt.add(0, 123);
        Assert.True(queueInt.get(), 8);
        Assert.False(queueInt.get(), 9);
    }

    @Test
    public void StringTest() {
        PriorityQueue<String, Integer> queueString = new PriorityQueue<>();
        queueString.add("a", 1);
        queueString.add("aa", 1);
        queueString.add("aaa", 4);
        queueString.add("aaaa", 2);
        queueString.add("aaaaa", 123);
        Assert.True(queueInt.get(), 8);
        Assert.False(queueInt.get(), 9);
    }
}
