package spell;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SpellCorrector implements ISpellCorrector{
    private Trie dictionary;

    public SpellCorrector(){
        dictionary = new Trie();
    }
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        File file = new File(dictionaryFileName);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String word = scanner.next();
            dictionary.add(word.toLowerCase());
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        inputWord = inputWord.toLowerCase();
        //test to see if word is already in dictionary. if so, return it.
        Node node = dictionary.find(inputWord);
        if(node != null){
            return dictionary.nodeToString(node);
        }
        //create a new word list with all of the words from distance 1
        Set<String> wordList = madeWordList(inputWord);
        //test words from distance 1.
        Set<Node> nodeSet = new HashSet<>();
        makeNodeSet(nodeSet, wordList);

        String champ = testWords(nodeSet);
        if(champ != null){
            return champ;
        }

        //if none work creat new list from dist 2
        Set<Node> newNodeSet = secondSet(wordList);


        //test words from dist 2
        champ = testWords(newNodeSet);
        //if none work return null
        return champ;
    }
    private void makeNodeSet(Set<Node> nodeSet, Set<String> wordList){
        for(String word: wordList){
            Node node1 = dictionary.find(word);
            if(node1 != null){
                nodeSet.add(node1);
            }
        }
    }

    private Set<Node> secondSet(Set<String> wordList){
        Set<Node> newNodeSet = new HashSet<>();
        Set<String> newWordList = new HashSet<>();
        for(String word: wordList){
            newWordList.addAll(madeWordList(word));
        }
        makeNodeSet(newNodeSet, newWordList);
        return newNodeSet;
    }

    private String testWords(Set<Node> nodeSet){
        String champ = null;
        String contendor = null;
        int champCount = 0;
        if(nodeSet.size() == 0){
            return champ;
        }
        else if(nodeSet.size() == 1){
            for(Node n: nodeSet){
                return dictionary.nodeToString(n);
            }
        }
        else if(nodeSet.size() > 1){
            for(Node node: nodeSet){
                contendor = dictionary.nodeToString(node);
                if(champ == null){
                    champ = contendor;
                    champCount = node.getValue();
                } else if(node.getValue() > champCount){
                    champ = contendor;
                    champCount = node.getValue();
                } else if(node.getValue() == champCount){
                    int len;
                    boolean same = true;
                    if(champ.length() < contendor.length()){
                        len = champ.length();
                    } else{
                        len = contendor.length();
                    }
                    for(int i = 0; i < len; i++){
                        if(champ.charAt(i) > contendor.charAt(i)){
                            champ = contendor;
                            same = false;
                            break;
                        }
                    }
                    if((len == contendor.length()) && same){
                        champ = contendor;
                    }
                }
            }
        }
        return champ;
    }
    private Set<String> madeWordList(String word){
        Set<String> list = new HashSet<>();
        alterationDist(list, word);
        deletionDist(list, word);
        transpositionDist(list,word);
        insertionDist(list,word);
        return list;
    }

    private void deletionDist(Set<String> list, String word){
        for(int i = 0; i < word.length(); i++){
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            list.add(sb.toString());
        }
    }

    private void alterationDist(Set<String> list, String word){
        for(int i = 0; i < word.length(); i++){
            for(int j = 0; j < 26; j++){
                StringBuilder sb = new StringBuilder(word);
                char c = (char)(j + 'a');
                sb.setCharAt(i,c);
                list.add(sb.toString());
            }
        }
    }

    private void transpositionDist(Set<String> list, String word){
        for(int i = 0; i < word.length() - 1; i++){
            StringBuilder sb = new StringBuilder(word);
            char c = sb.charAt(i);
            sb.setCharAt(i,word.charAt(i+1));
            sb.setCharAt(i+1, c);
            list.add(sb.toString());
        }
    }

    private void insertionDist(Set<String> list, String word){
        for(int i = 0; i < word.length()+1; i++){
            for(int j = 0; j < 26; j++){
                StringBuilder sb = new StringBuilder(word);
                char c = (char)(j + 'a');
                if(i == word.length()){
                    sb.append(c);
                }else{
                    sb.insert(i,c);
                }
                list.add(sb.toString());
            }
        }
    }

}
