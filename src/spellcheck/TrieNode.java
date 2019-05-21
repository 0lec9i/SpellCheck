package spellcheck;


import java.util.List;

public class TrieNode implements spellcheck.ITrie.INode {

    private TrieNode[] nodeArray = new TrieNode[26];

    private int value;

    public int getValue() {
        return value;
    }

    public void add(List<String> letters, Trie motherTrie){

        //  If we only get no letter, the word ends here.
        if ( letters.size() == 0 ){

            if (this.value == 0){
                //  First instance of this word
                motherTrie.incWordCount();
            }


            this.value += 1;



            return;
        }

        // Get chopped list
        List<String> chopLetters = letters.subList( 1, letters.size() );

        // Find the next node
        TrieNode nextNode = this.getNodeL(letters.get(0).charAt(0), motherTrie);

        // Pass the chopped list to the next node
        nextNode.add(chopLetters, motherTrie);

        return;
    }

    public TrieNode getNodeL(char l, Trie motherTrie){
        //  This method is only meant to be used in conjunction with the add method.
        //  When searching the completed Trie, it is easier to index into nodeArray.

        int index = l - 'a';

        if( this.nodeArray[index] == null ) {
            this.nodeArray[index] = new TrieNode();
            motherTrie.incNodeCount();
        }

        return this.nodeArray[index];
    }

    public TrieNode find(List<String> letters){

        //  If we run out of letters, we have it or we don't
        if ( letters.isEmpty()){

            if (this.getValue() > 0){

                return this;

            } else {

                return null;

            }
        }

        //  If we still have more, find next and keep going.

        List<String> chopLetters = letters.subList(1,letters.size());

        TrieNode nextNode = this.nodeArray[(int)(letters.get(0).charAt(0) - 'a')];

        //  If there is no node, return null
        if (nextNode == null) return null;

        return nextNode.find(chopLetters);
    }


    public void buildToString(StringBuilder outString, StringBuilder current){
        if ( this.value > 0 ){
            outString.append(current + "\n");
        }

        for (int i = 0; i < 26; i++){
            if (this.nodeArray[i] != null){
                //  Here, we append the next letter to current
                current.append( (char) (i + 'a') );

                this.nodeArray[i].buildToString(outString,current);

                // Before moving on, we take off the letter we just put on
                current.delete(current.length() - 1, current.length());
            }
        }
    }

    public String toString(){
        StringBuilder outString = new StringBuilder();
        StringBuilder current = new StringBuilder();
        this.buildToString(outString, current);
        return outString.toString();
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null || getClass() != other.getClass()){
            return false;
        }

        TrieNode o = (TrieNode) other;

        //  Check that values are equal
        if ( this.getValue() != o.getValue()) {
            return false;
        }

        //  Call equal on each child
        for (int i = 0; i < 26; i++){
            TrieNode child = this.nodeArray[i];
            TrieNode oChild = o.nodeArray[i];

            //  Both null, we go on
            if (child == null && oChild == null ){
                continue;
            }

            //  One null, not a match
            if (child == null || oChild == null ){
                return false;
            }

            //  call equals recursively on children
            if (!child.equals(oChild)){
                return false;
            }

        }

        // If no false on any child, the nodes are equal
        return true;
    }

}
