package by.kononov.handling.parser;

import by.kononov.handling.composite.ComponentText;

public abstract class HandlerParser{
	private HandlerParser nextParser;

	public abstract void parseComponent(String text, ComponentText component);

	public void setNextParser(HandlerParser next) {
		nextParser = next;
	}

	public HandlerParser getNextParser() {
		return nextParser;
	}
}
