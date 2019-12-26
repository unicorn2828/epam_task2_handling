package by.kononov.handling.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context{
	private Deque<Integer> contextValue = new ArrayDeque<>();

	public void push(Integer number) {
		contextValue.push(number);
	}

	public Integer pop() {
		return contextValue.pop();
	}

	public Integer pollLast() {
		return contextValue.pollLast();
	}
}
