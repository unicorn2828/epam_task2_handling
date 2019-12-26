package by.kononov.handling.parser;

import static by.kononov.handling.type.TextType.SYMBOL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.handling.composite.ComponentText;
import by.kononov.handling.composite.SymbolLeaf;

public class SymbolParser extends HandlerParser{
	private final static SymbolParser INSTANCE = new SymbolParser();

	private SymbolParser() {
	}

	public static SymbolParser getInstance() {
		return INSTANCE;
	}

	@Override
	public void parseComponent(String text, ComponentText component) {
		Pattern pattern = Pattern.compile(SYMBOL.getRegex());
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			SymbolLeaf symbol = new SymbolLeaf(matcher.group().charAt(0));
			component.addComponent(symbol);
		}
	}
}