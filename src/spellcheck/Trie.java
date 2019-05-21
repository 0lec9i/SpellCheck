package spellcheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie implements spellcheck.ITrie {

    //  VARIABLES

    // Begins with root node, always
    private int nodeCount = 1;

    private int wordCount = 0;

    private TrieNode root = new TrieNode();


    //  METHODS

    public void add(String word) {

        word = word.toLowerCase();

        ArrayList<String> arrayLetters = new ArrayList<String>(Arrays.asList(word.split("")));

        List<String> letters = (List<String>) arrayLetters;

        this.root.add(letters, this);

    }

    public INode find(String word) {
        // Don't try empty strings
        if (word == null || word.isEmpty()){
          System.out.println("Almost tried an empty string!");
          return null;
        }

        word = word.toLowerCase();

        ArrayList<String> letters = new ArrayList<String>(Arrays.asList(word.split("")));


        return root.find(letters);
    }

    public int getWordCount() {
        return this.wordCount;
    }

    public int getNodeCount() {
        return this.nodeCount;
    }


    @Override
    public String toString() {
        return this.root.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Trie trie = (Trie) o;
        return this.root.equals(trie.root);
    }

    @Override
    public int hashCode() {
        return this.getWordCount() * this.getNodeCount();
    }



    public void incWordCount(){
        this.wordCount += 1;
    }

    public void incNodeCount(){
        this.nodeCount += 1;
    }


}
