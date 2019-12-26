package by.kononov.handling.interpreter;

import java.util.List;

public class Client{
	private Context context = new Context();

	public String handleExpression(List<MathExpression> exspression) {
		exspression.forEach(terminal -> terminal.interpret(context));
		return context.pop().toString();
	}
}