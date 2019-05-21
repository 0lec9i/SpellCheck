package spellcheck;

import java.io.IOException;

public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to check
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {
		
		String dictionaryFileName = args[0];
		String inputWord = args[1];

		ISpellChecker checker = new SpellChecker();
		
		checker.useDictionary(dictionaryFileName);
		String suggestion = checker.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
