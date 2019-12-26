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

public class SizeSentenceTest{
	private CompositeText composite;
	private CompositeAction action;

	@BeforeClass
	public void setUp() throws CustomException {
		String text = "\tOne, 2." + "	One, two2, 3.three, 4four4. " + "One, Two, Three, Four, Five, Six, Seven."
				+ "	One, two ,Three, 4, five, Six-66, 7even, Eight.8, Nine/9 Bye.\r";
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

	@Test(description = "remove sentences with min specified amount of words; 9 words")
	public void sizeSentenceTest() {
		System.out.println(composite.toString());
		int minSentenceSize = 9;
		action.removeSentence(composite, minSentenceSize);
		String expected = "\tOne, two ,Three, 4, five, Six-66, 7even, Eight.8, Nine/9 Bye. ";
		String actual = composite.toString();
		assertEquals(actual, expected);
	}

	@Test(description = "remove sentences with min specified amount of words; 10 words")
	public void sizeSentenceTestNegative() {
		System.out.println(composite.toString());
		int minSentenceSize = 10;
		action.removeSentence(composite, minSentenceSize);
		String expected = "\tOne, two ,Three, 4, five, Six-66, 7even, Eight.8, Nine/9 Bye. ";
		String actual = composite.toString();
		assertNotEquals(actual, expected);
	}
}