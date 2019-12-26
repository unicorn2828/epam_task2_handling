package by.kononov.handling.parser;

import static by.kononov.handling.type.TextType.BIT_EXPRESSION;
import static by.kononov.handling.type.TextType.LEXEM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.handling.composite.ComponentText;
import by.kononov.handling.composite.CompositeText;
import by.kononov.handling.interpreter.BitwiseInterpreter;
import by.kononov.handling.interpreter.Client;

public class LexemParser extends HandlerParser{
	private final static LexemParser INSTANCE = new LexemParser();

	private LexemParser() {
	}

	public static LexemParser getInstance() {
		return INSTANCE;
	}

	@Override
	public void parseComponent(String text, ComponentText component) {
		Pattern pattern = Pattern.compile(LEXEM.getRegex());
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			ComponentText lexem = new CompositeText(LEXEM);
			text = matcher.group();
			if (text.matches(BIT_EXPRESSION.getRegex())) {
				BitwiseInterpreter interpreter = new BitwiseInterpreter();
				Client client = new Client();
				text = client.handleExpression(interpreter.parse(text));
			}
			getNextParser().parseComponent(text, lexem);
			component.addComponent(lexem);
		}
	}
}