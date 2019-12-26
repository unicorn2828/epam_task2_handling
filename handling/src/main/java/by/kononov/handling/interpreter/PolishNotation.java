package by.kononov.handling.interpreter;

import static by.kononov.handling.interpreter.BitwiseType.AND;
import static by.kononov.handling.interpreter.BitwiseType.COMPLEMENT;
import static by.kononov.handling.interpreter.BitwiseType.LEFT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.NUMBER_REGEX;
import static by.kononov.handling.interpreter.BitwiseType.OR;
import static by.kononov.handling.interpreter.BitwiseType.RIGHT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.SINGLE_LEFT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.SINGLE_RIGHT_SHIFT;
import static by.kononov.handling.interpreter.BitwiseType.XOR;

import java.util.ArrayDeque;
import java.util.Deque;

public class PolishNotation{
	private static final String LEFT_BRACKET = "(";
	private static final String RIGHT_BRACKET = ")";
	private static final String WHITE_SPACE = " ";

	public String convertToPolishNotation(String exp) {
		StringBuilder polishNotation = new StringBuilder();
		Deque<String> elementsList = new ArrayDeque<>();
		char[] charExpression = exp.toCharArray();
		String listElement;
		String element;
		int i = 0;
		for (; i < charExpression.length; i++) {
			element = String.valueOf(charExpression[i]);
			switch (element) {
				case COMPLEMENT:
					elementsList.push(element);
					break;
				case LEFT_BRACKET:
					elementsList.push(element);
					break;
				case RIGHT_BRACKET:
					while (!LEFT_BRACKET.equals(listElement = elementsList.pop())) {
						polishNotation.append(listElement).append(WHITE_SPACE);
					}
					break;
				case SINGLE_RIGHT_SHIFT:
					while (!elementsList.isEmpty()
							&& (RIGHT_SHIFT.equals(elementsList.peek()) || COMPLEMENT.equals(elementsList.peek()))) {
						polishNotation.append(elementsList.pop()).append(WHITE_SPACE);
					}
					elementsList.push(element + charExpression[++i]);
					break;
				case SINGLE_LEFT_SHIFT:
					while (!elementsList.isEmpty()
							&& (LEFT_SHIFT.equals(elementsList.peek()) || COMPLEMENT.equals(elementsList.peek()))) {
						polishNotation.append(elementsList.pop()).append(WHITE_SPACE);
					}
					elementsList.push(element + charExpression[++i]);
					break;
				case AND:
					while (!elementsList.isEmpty() && (LEFT_SHIFT.equals(elementsList.peek())
							|| RIGHT_SHIFT.equals(elementsList.peek()) || COMPLEMENT.equals(elementsList.peek()))) {
						polishNotation.append(elementsList.pop()).append(WHITE_SPACE);
					}
					elementsList.push(element);
					break;
				case XOR:
					while (!elementsList.isEmpty()
							&& (LEFT_SHIFT.equals(elementsList.peek()) || RIGHT_SHIFT.equals(elementsList.peek())
									|| COMPLEMENT.equals(elementsList.peek()) || AND.equals(elementsList.peek()))) {
						polishNotation.append(elementsList.pop()).append(WHITE_SPACE);
					}
					elementsList.push(element);
					break;
				case OR:
					while (!elementsList.isEmpty() && (LEFT_SHIFT.equals(elementsList.peek())
							|| RIGHT_SHIFT.equals(elementsList.peek()) || COMPLEMENT.equals(elementsList.peek())
							|| AND.equals(elementsList.peek()) || XOR.equals(elementsList.peek()))) {
						polishNotation.append(elementsList.pop()).append(WHITE_SPACE);
					}
					elementsList.push(element);
					break;
				default:
					if (element.matches(NUMBER_REGEX)) {
						polishNotation.append(element);
						if (i + 1 < charExpression.length) {
							while ((i + 1) < charExpression.length && isDigit(charExpression[++i])) {
								polishNotation.append(charExpression[i]);
							}
							if (!isDigit(charExpression[i])) {
								i--;
							}
						}
						polishNotation.append(WHITE_SPACE);
					}
			}
		}
		elementsList.forEach(item -> polishNotation.append(item).append(WHITE_SPACE));
		return polishNotation.toString();
	}

	private boolean isDigit(char character) {
		String element = Character.toString(character);
		if (element == null) {
			return false;
		}
		return element.matches(NUMBER_REGEX);
	}
}