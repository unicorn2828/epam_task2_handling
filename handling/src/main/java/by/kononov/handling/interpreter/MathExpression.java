package by.kononov.handling.interpreter;

@FunctionalInterface
public interface MathExpression{
	void interpret(Context context);
}
