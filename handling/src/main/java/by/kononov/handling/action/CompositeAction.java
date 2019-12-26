package by.kononov.handling.action;

import static by.kononov.handling.type.TextType.WORD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.kononov.handling.composite.ComponentText;
import by.kononov.handling.composite.CompositeText;

/**
 * This is the CompositeAction class; it contains methods for: sorting
 * paragraphs by sentence's amount, removing sentences by min amount
 * words, finding sentences by the longest word and receiving amount
 * equal words in a text.
 * 
 * @author Vitaly Kononov
 * @since 2019-12-04
 */
public class CompositeAction{

	/**
	 * This method sorts paragraphs by sentence's amount.
	 * 
	 * @param composite - the object of CompositeText class for sorting.
	 */
	public void paragraphSort(CompositeText composite) {
		composite.receiveComponents()
				.sort((o1, o2) -> Integer.compare(o2.receiveComponents().size(), o1.receiveComponents().size()));
	}

	/**
	 * This method removes sentences by min amount words.
	 * 
	 * @param composite - object of CompositeText class for removing.
	 */
	public void removeSentence(CompositeText composite, int minSentenceSize) {
		for (ComponentText paragraph : composite.receiveComponents()) {
			paragraph.receiveComponents().removeIf(o -> o.receiveComponents().size() <= minSentenceSize);
		}
	}

	/**
	 * This method receives sentences by the longest word.
	 * 
	 * @param composite - object of CompositeText class for receiving.
	 * @return sentenceList - the list of sentences with the longest word.
	 */
	public List<String> findSentencesWithLongestWord(CompositeText composite) {
		List<String> sentenceList = new ArrayList<>();
		Pattern wordPattern = Pattern.compile(WORD.getRegex());
		String theLongestWord;
		Matcher matcher;
		int size = 0;
		for (ComponentText paragraph : composite.receiveComponents()) {
			for (ComponentText sentence : paragraph.receiveComponents()) {
				matcher = wordPattern.matcher(sentence.toString());
				while (matcher.find()) {
					theLongestWord = matcher.group();
					if (theLongestWord.length() == size) {
						size = theLongestWord.length();
						sentenceList.add(sentence.toString());
					}
					if (theLongestWord.length() > size) {
						sentenceList.clear();
						sentenceList.add(sentence.toString());
						size = theLongestWord.length();
					}
				}
			}
		}
		return sentenceList;
	}

	/**
	 * This method receives amount equal words in a text.
	 * 
	 * @param composite - the object of CompositeText class for receiving.
	 * @return equalWordsList - the list of equal words.
	 */
	public Set<String> findEqualWords(CompositeText composite) {
		Set<String> notEqualWordsList = new HashSet<>();
		Set<String> equalWordsList = new HashSet<>();
		Pattern wordPattern = Pattern.compile(WORD.getRegex());
		Matcher matcher;
		String word;
		for (ComponentText paragraph : composite.receiveComponents()) {
			for (ComponentText sentence : paragraph.receiveComponents()) {
				matcher = wordPattern.matcher(sentence.toString());
				while (matcher.find()) {
					word = matcher.group().toLowerCase();
					if (!notEqualWordsList.add(word)) {
						equalWordsList.add(word);
					}
				}
			}
		}
		return equalWordsList;
	}
}