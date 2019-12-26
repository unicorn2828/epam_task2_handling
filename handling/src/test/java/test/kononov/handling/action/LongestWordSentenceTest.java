package test.kononov.handling.action;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import by.kononov.handling.reader.DataReader;
import by.kononov.handling.type.TextType;

public class LongestWordSentenceTest{
	private static final String FILE_PATH = "data/mainText.txt";
	private CompositeText composite;
	private CompositeAction action;

	@BeforeClass
	public void setUp() throws CustomException {
		DataReader dataReader = new DataReader();
		String text = dataReader.readFile(FILE_PATH);
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

	@Test(description = "find sentences with the longest word")
	public void longestWordTest() {
		List<String> actual = action.findSentencesWithLongestWord(composite);
		List<String> expected = new ArrayList<>();
		expected.add("IamTheLongestWord. ");
		expected.add("Long IamTheLongestWord. ");
		assertEquals(actual, expected);
	}
}