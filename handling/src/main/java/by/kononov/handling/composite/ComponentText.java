package by.kononov.handling.composite;

import java.util.List;

import by.kononov.handling.type.TextType;

public abstract class ComponentText{
	private TextType textType;

	public abstract String toString();

	public TextType getEntityType() {
		return textType;
	}

	public void setTextType(TextType textType) {
		this.textType = textType;
	}

	public abstract void removeComponent(ComponentText component);

	public abstract void addComponent(ComponentText component);

	public abstract List<ComponentText> receiveComponents();

	public abstract ComponentText receiveChild(int index);
}
