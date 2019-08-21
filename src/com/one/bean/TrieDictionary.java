package com.one.bean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.one.One;

public class TrieDictionary {
	 
    private static TrieDictionary trieDictionary = null;
 
    private static List<String> wordlist = null;
 
    private static TrieNode root = null;
 
    public TrieNode getRoot(){
        return root;
    }
 
    public static TrieDictionary getInstance(String dictionaryName){
        if (trieDictionary==null) {
            trieDictionary = new TrieDictionary(dictionaryName);
        }
        return trieDictionary;
    }
 
    public static TrieDictionary getInstance(){
        if (trieDictionary==null) {
            trieDictionary = new TrieDictionary(One.Config.WORDDICPATH);
        }
        return trieDictionary;
    }
 
    @SuppressWarnings("unchecked")
	private TrieDictionary(String dictionaryName){
        try {
            wordlist = FileUtils.readLines(new File(One.Config.WORDDICPATH));
            root = new TrieNode();
            for(String word: wordlist){
                addWord(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private void addWord(String word){
        TrieNode current = root;
        for (int i=0; i<word.length();++i) {
            char c = word.charAt(i);
            TrieNode node = new TrieNode(c);
            if (i == word.length() - 1) {
                node.bound=true;
            }
            HashMap<Character, TrieNode> childs = current.childs;
            if (childs.containsKey(c)) {
                current = childs.get(c);
            }else{
                childs.put(c, node);
                current = node;
            }
        }
    }
}
