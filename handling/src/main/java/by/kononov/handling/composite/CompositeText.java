package by.kononov.handling.composite;

import static by.kononov.handling.type.TextType.LEXEM;
import static by.kononov.handling.type.TextType.PARAGRAPH;

import java.util.LinkedList;
import java.util.List;

import by.kononov.handling.type.TextType;

public class CompositeText extends ComponentText{
	private final static String PARAGRAPH_MARK = "\n\t";
	private final static String TABULATION_MARK = "\t";
	private final static String WHITE_SPACE = " ";
	private List<ComponentText> components = new LinkedList<>();

	public CompositeText(TextType textType) {
		setTextType(textType);
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		for (ComponentText component : components) {
			if (PARAGRAPH.equals(component.getEntityType())) {
				if (component != components.get(0) && text.length() != 0) {
					text.append(PARAGRAPH_MARK);
				} else {
					text.append(TABULATION_MARK);
				}
			}
			text.append(component.toString());
			if (LEXEM.equals(component.getEntityType())) {
				text.append(WHITE_SPACE);
			}
		}
		return text.toString();
	}

	@Override
	public void removeComponent(ComponentText component) {
		components.remove(component);
	}

	@Override
	public void addComponent(ComponentText component) {
		components.add(component);
	}

	@Override
	public List<ComponentText> receiveComponents() {
		return components;
	}

	@Override
	public ComponentText receiveChild(int index) {
		return components.get(index);
	}
}