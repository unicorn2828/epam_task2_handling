package by.kononov.handling.parser;

import static by.kononov.handling.type.TextType.SENTENCE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.handling.composite.ComponentText;
import by.kononov.handling.composite.CompositeText;

public class SentenceParser extends HandlerParser{
	private final static SentenceParser INSTANCE = new SentenceParser();

	private SentenceParser() {
	}

	public static SentenceParser getInstance() {
		return INSTANCE;
	}

	@Override
	public void parseComponent(String text, ComponentText component) {
		Pattern pattern = Pattern.compile(SENTENCE.getRegex());
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			ComponentText sentence = new CompositeText(SENTENCE);
			text = matcher.group();
			getNextParser().parseComponent(text, sentence);
			component.addComponent(sentence);
		}
	}
}