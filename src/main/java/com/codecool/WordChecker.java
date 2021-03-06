package com.codecool;

import java.util.ArrayList;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker {
    private WordList wordList;
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
	public WordChecker(WordList wordList) {
        this.wordList = wordList;
	}
	

	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise. 
   *
   * @param word Word to chack against the internal word list
   * @return bollean indicating if the word was found or not.
   */
	public boolean wordExists(String word) {
	    return wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList<String> getSuggestions(String word) {
	    ArrayList<String> suggestions = new ArrayList<>();
	    suggestions.addAll(getSuggestionsWithSwappingCharacter(word));
        suggestions.addAll(getSuggestionsWithAddedCharacter(word));
        suggestions.addAll(getSuggestionsWithDeletedCharacter(word));
        suggestions.addAll(getSuggestionsWithReplacedCharacter(word));
        suggestions.addAll(getSuggestionsWithSpace(word));
	    return suggestions;
	}

	private ArrayList<String> getSuggestionsWithSwappingCharacter(String word) {
	    ArrayList<String> words = new ArrayList<>();
	    char[] wordAsChars = word.toCharArray();
	    StringBuilder stringBuilder;
	    String newWord;
	    for (int i = 0; i < word.length() - 1; i++) {
	        stringBuilder = new StringBuilder();
	        for (int j = 0; j < word.length(); j++) {
	            if (j == i) {
	                stringBuilder.append(wordAsChars[j + 1]).append(wordAsChars[j]);
	                j++;
                } else {
	                stringBuilder.append(wordAsChars[j]);
                }
            }
            newWord = stringBuilder.toString();
	        if (wordExists(newWord)) {
	            words.add(newWord);
            }
        }
        return words;
    }

    private ArrayList<String> getSuggestionsWithDeletedCharacter(String word) {
	    ArrayList<String> words = new ArrayList<>();
	    char[] wordAsChars = word.toCharArray();
	    StringBuilder stringBuilder;
	    String newWord;
	    for (int i = 0; i < word.length(); i++) {
	        stringBuilder = new StringBuilder();
	        for (int j = 0; j < word.length(); j++) {
	            if (j != i) {
                    stringBuilder.append(wordAsChars[j]);
                }
            }
            newWord = stringBuilder.toString();
	        if (wordExists(newWord)) {
	            words.add(newWord);
            }
        }
        return words;
    }

    private ArrayList<String> getSuggestionsWithAddedCharacter(String word) {
	    ArrayList<String> words = new ArrayList<>();
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    char[] wordAsChars = word.toCharArray();
	    StringBuilder stringBuilder;
	    String newWord;
	    for (char ch : alphabet.toCharArray()) {
            for (int i = 0; i < word.length(); i++) {
                stringBuilder = new StringBuilder();
                for (int j = 0; j < word.length(); j++) {
                    if (j == i) {
                        stringBuilder.append(ch);
                    }
                    stringBuilder.append(wordAsChars[j]);
                }
                newWord = stringBuilder.toString();
                if (wordExists(newWord)) {
                    words.add(newWord);
                }
            }
	    }

	    for (char ch : alphabet.toCharArray()) {
	        newWord = word + ch;
	        if (wordExists(newWord)) {
	            words.add(newWord);
            }
        }

        return words;
    }

    private ArrayList<String> getSuggestionsWithReplacedCharacter(String word) {
	    ArrayList<String> words = new ArrayList<>();
	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    char[] wordAsChars = word.toCharArray();
	    StringBuilder stringBuilder;
	    String newWord;

	    for (char ch : alphabet.toCharArray()) {
            for (int i = 0; i < word.length(); i++) {
                stringBuilder = new StringBuilder();
                for (int j = 0; j < word.length(); j++) {
                    if (j == i) {
                        stringBuilder.append(ch);
                    } else  {
                        stringBuilder.append(wordAsChars[j]);
                    }
                }
                newWord = stringBuilder.toString();
                if (wordExists(newWord)) {
                    words.add(newWord);
                }
            }
	    }

        return words;
    }

    private ArrayList<String> getSuggestionsWithSpace(String word) {
	    ArrayList<String> words = new ArrayList<>();
	    char[] wordAsChars = word.toCharArray();
	    StringBuilder stringBuilder;
	    String newWord;

        for (int i = 1; i < word.length() ; i++) {
            stringBuilder = new StringBuilder();
            for (int j = 0; j < word.length(); j++) {
                if (j == i) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(wordAsChars[j]);
            }
            newWord = stringBuilder.toString();
            String[] wordParts = newWord.split(" ");
            if (wordExists(wordParts[0]) && wordExists(wordParts[1])) {
                words.add(newWord);
            }
	    }

        return words;
    }


}
