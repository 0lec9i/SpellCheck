package spellcheck;

import java.io.IOException;

public interface ISpellChecker {

	public void useDictionary(String dictionaryFileName) throws IOException;

	public String suggestSimilarWord(String inputWord);

}
