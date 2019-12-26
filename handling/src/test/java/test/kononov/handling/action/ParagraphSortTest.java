package test.kononov.handling.action;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.kononov.handling.action.CompositeAction;
import by.kononov.handling.composite.CompositeText;
import by.kononov.handling.exception.CustomException;
import by.kononov.handling.parser.HandlerParser;
import by.kononov.handling.parser.LexemParser;
import by.kononov.handling.parser.ParagraphParser;
import by.kononov.handling.parser.SentenceParser;
import by.kononov.handling.parser.SymbolParser;
import by.kononov.handling.type.TextType;

public class ParagraphSortTest{
	private CompositeText composite;
	private CompositeAction action;

	@BeforeClass
	public void setUp() throws CustomException {
		String text =
				"\tOne. Two. \r\n" + "	One. Two. Three. Four. " + "\r\nOne. Two. Three. Four. Five. Six. Seven.\r\n"
						+ "	One. Two. Three. Four. Five. Six. Seven. Eight. Nine Bye.\r";
		HandlerParser paragraphParser = ParagraphParser.getInstance();
		HandlerParser sentencePasrser = SentenceParser.getInstance();
		HandlerParser lexemParser = LexemParser.getInstance();
		HandlerParser symbolParser = SymbolParser.getInstance();
		paragraphParser.setNextParser(sentencePasrser);
		sentencePasrser.setNextParser(lexemParser);
		lexemParser.setNextParser(symbolParser);
		composite = new CompositeText(TextType.TEXT);
		paragraphParser.parseComponent(text, composite);
		action = new CompositeAction();
	}

	@AfterClass
	public void tierDown() {
		composite = null;
		action = null;
	}

	@Test(description = "sort paragraphs by sentence's amount")
	public void paragraphsSortTest() {
		String expected = "\tOne. Two. Three. Four. Five. Six. Seven. Eight. Nine Bye. "
				+ "\n\tOne. Two. Three. Four. Five. Six. Seven. " + "\n\tOne. Two. Three. Four. " + "\n\tOne. Two. ";
		action.paragraphSort(composite);
		String actual = composite.toString();
		assertEquals(actual, expected);
	}

	@Test(description = "sort paragraphs by sentence's amount")
	public void paragraphsSortTestNegative() {
		String expected = "\tOne. Two. Three. Four. Five. Six. Seven. Eight. Nine Bye. "
				+ "\n\tOne. Two. Three. Four. Five. Six. Seven. " + "\n\tOne. Two. " + "\n\tOne. Two. Three. Four. ";
		action.paragraphSort(composite);
		String actual = composite.toString();
		assertNotEquals(actual, expected);
	}
}