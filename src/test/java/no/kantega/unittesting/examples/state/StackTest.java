package no.kantega.unittesting.examples.state;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void pushShallIncreaseSizeByOne() {

        //given
        Stack<String> stack = new Stack<>();
        int oldSize = stack.size();

        //when
        stack.push(something());

        //then
        assertThat(stack.size(), equalTo(oldSize + 1));

    }

    @Test
    public void pushShallPutElementOnTopOfStack() {

        //given
        Stack<String> stack = new Stack<>();

        //when
        stack.push(something());

        //then
        assertThat(stack.top(), equalTo(something()));

    }

    @Test
    public void topShallNotChangeSize() {

        //given
        Stack<String> stack = new Stack<>();
        stack.push(something());
        int oldSize = stack.size();

        //when
        stack.top();

        //then
        assertThat(stack.size(), equalTo(oldSize));

    }

    @Test
    public void popShallReturnElementOnTop() {

        //given
        Stack<String> stack = new Stack<>();
        stack.push(something());
        String oldTop = stack.top();

        //when
        String popped = stack.pop();

        //then
        assertThat(popped, equalTo(oldTop));

    }

    @Test
    public void popShallDecreaseSizeByOne() {

        //given
        Stack<String> stack = new Stack<>();
        stack.push(something());
        int oldSize = stack.size();

        //when
        stack.pop();

        //then
        assertThat(stack.size(), equalTo(oldSize - 1));

    }

    @Test
    public void defaultStackIsEmpty() {

        //given
        Stack<String> stack = new Stack<>();

        //then
        assertThat(stack.empty(), is(true));
    }

    private String something() {
        return "something";
    }

}
