package by.kononov.handling.composite;

import static by.kononov.handling.type.TextType.SYMBOL;

import java.util.List;

public class SymbolLeaf extends ComponentText{
	private char symbol;

	public SymbolLeaf(char symbol) {
		this.symbol = symbol;
		setTextType(SYMBOL);
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}

	public char getSymbol() {
		return symbol;
	}

	@Override
	public int hashCode() {
		return Character.hashCode(symbol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		SymbolLeaf leaf = (SymbolLeaf) obj;
		if (symbol != leaf.symbol) {
			return false;
		}
		return true;
	}

	@Override
	public List<ComponentText> receiveComponents() {
		throw new UnsupportedOperationException("receiveComponents method shouldn't do anything");
	}

	@Override
	public void removeComponent(ComponentText component) {
		throw new UnsupportedOperationException("removeComponent method shouldn't do anything");
	}

	@Override
	public void addComponent(ComponentText component) {
		throw new UnsupportedOperationException("addComponent method shouldn't do anything");
	}

	@Override
	public ComponentText receiveChild(int index) {
		throw new UnsupportedOperationException("receiveChild method shouldn't do anything");
	}
}
