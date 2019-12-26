package by.kononov.handling.type;

public enum TextType {
	TEXT("(?sm)^.*"),
	PARAGRAPH(".+\\R"),
	SENTENCE("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)"),
	LEXEM("[^\\s]+"),
	WORD("\\p{L}+"),
	SYMBOL(".{1}"),
	BIT_EXPRESSION("[\"\\'\\^<>\\d()&|~]+");

	private String regex;

	private TextType(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}
}
