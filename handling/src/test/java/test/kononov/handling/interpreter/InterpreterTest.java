package test.kononov.handling.interpreter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.kononov.handling.interpreter.BitwiseInterpreter;
import by.kononov.handling.interpreter.Client;

public class InterpreterTest{
	private BitwiseInterpreter expressionInterpreter;
	private Client client;

	@BeforeClass
	public void setUp() {
		expressionInterpreter = new BitwiseInterpreter();
		client = new Client();
	}

	@AfterClass
	public void tierDown() {
		expressionInterpreter = null;
		client = null;
	}

	@DataProvider
	public Object[][] bitwiseExpressions() {
		return new Object[][] {
				{ "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78", "78" },
				{ "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)", "5" },
				{ "~6&9|(3&4)", "9" },
				{ "(7^5|1&2<<(2|5>>2&71))|1200", "1202" },
				{ "3>>5", "0" },
				{ "13<<2", "52" }, };
	}

	@Test(dataProvider = "bitwiseExpressions", description = "bitwise expression interpreter test")
	public void interpreterTest(String in, String expected) {
		String actual = (client.handleExpression(expressionInterpreter.parse(in)));
		assertEquals(actual, expected);
	}

	@Test(description = "bitwise expression interpreter test")
	public void interpreterTest() {
		String expression = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
		String actual = (client.handleExpression(expressionInterpreter.parse(expression)));
		Integer bitwise = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
		String expected = bitwise.toString();
		assertEquals(actual, expected);
	}

	@Test(timeOut = 10, description = "check for exception if a expression can't be parse longer then specified time")
	public void parseExpressionTestTime() {
		String expression = "5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1)";
		client.handleExpression(expressionInterpreter.parse(expression));
	}

	@Test(description = "bitwise expression interpreter negative test")
	public void interpreterTestNegative() {
		String expression = "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78";
		String actual = (client.handleExpression(expressionInterpreter.parse(expression)));
		Integer bitwise = 5 | (1 & 2 & (3 | (4 & (1 ^ 5 | 6 & 47) | 3) | (~89 & 4 | (42 & 7))) | 1);
		String expected = bitwise.toString();
		assertNotEquals(actual, expected);
	}
}
