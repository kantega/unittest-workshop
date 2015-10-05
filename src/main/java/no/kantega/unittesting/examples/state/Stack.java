package no.kantega.unittesting.examples.state;

import java.util.Deque;
import java.util.LinkedList;

public class Stack<T> {

    private Deque<T> list = new LinkedList<T>();

    public void push(T element) {
        list.addFirst(element);
    }

    public T top() {
        return list.peekFirst();
    }

    public T pop() {
        return list.removeFirst();
    }

    public boolean empty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

}
