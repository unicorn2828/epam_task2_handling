package by.kononov.handling.interpreter;

import static by.kononov.handling.interpreter.BitwiseType.AND;
import static by.kononov.handling.interpreter.BitwiseType.COMPLEMENT;
import static by.kononov.handling.interpreter.BitwiseType.LEFT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.NUMBER_REGEX;
import static by.kononov.handling.interpreter.BitwiseType.OR;
import static by.kononov.handling.interpreter.BitwiseType.RIGHT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.XOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitwiseInterpreter{
	private static final String SPLIT_REGEX = "\\p{Blank}+";

	public List<MathExpression> parse(String bitExpression) {
		PolishNotation notation = new PolishNotation();
		bitExpression = notation.convertToPolishNotation(bitExpression);
		String[] operators =
				Arrays.stream(bitExpression.split(SPLIT_REGEX)).filter(o -> !o.isEmpty()).toArray(String[]::new);
		List<MathExpression> expression = new ArrayList<>();
		for (String element : operators) {
			switch (element) {
				case COMPLEMENT:
					expression.add(o -> o.push(~o.pop()));
					break;
				case AND:
					expression.add(o -> o.push(o.pop() & o.pop()));
					break;
				case XOR:
					expression.add(o -> o.push(o.pop() ^ o.pop()));
					break;
				case OR:
					expression.add(o -> o.push(o.pop() | o.pop()));
					break;
				case RIGHT_SHIFT:
					expression.add(o -> o.push(o.pop() >> o.pop()));
					break;
				case LEFT_SHIFT:
					expression.add(o -> o.push(o.pollLast() << o.pollLast()));
					break;
				default:
					if (isNumber(element)) {
						expression.add(o -> o.push(Integer.parseInt(element)));
					}
			}
		}
		return expression;
	}

	private boolean isNumber(String element) {
		if (element == null) {
			return false;
		}
		return element.matches(NUMBER_REGEX);
	}
}