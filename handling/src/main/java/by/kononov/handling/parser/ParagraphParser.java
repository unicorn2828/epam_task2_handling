package by.kononov.handling.parser;

import static by.kononov.handling.type.TextType.PARAGRAPH;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.handling.composite.ComponentText;
import by.kononov.handling.composite.CompositeText;

public class ParagraphParser extends HandlerParser{
	private final static ParagraphParser INSTANCE = new ParagraphParser();

	private ParagraphParser() {
	}

	public static ParagraphParser getInstance() {
		return INSTANCE;
	}

	@Override
	public void parseComponent(String text, ComponentText component) {
		Pattern pattern = Pattern.compile(PARAGRAPH.getRegex());
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			ComponentText paragraph = new CompositeText(PARAGRAPH);
			text = matcher.group();
			getNextParser().parseComponent(text, paragraph);
			component.addComponent(paragraph);
		}
	}
}