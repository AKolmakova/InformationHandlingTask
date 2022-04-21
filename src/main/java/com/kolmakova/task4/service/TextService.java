package com.kolmakova.task4.service;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.exception.TextException;

import java.util.List;

public interface TextService {

    void sortParagraphsByNumberOfSentences(TextComponent text) throws TextException;

    List<TextComponent> getSentencesWithLongestWord(TextComponent text) throws TextException;

    void removeSentencesWhereNumberOfWordsLessThanSpecified(TextComponent text, int specifiedNumberOfWords) throws TextException;

    int getNumberOfIdenticalWords(TextComponent text) throws TextException;

    int getNumberOfVowelsInSentence(TextComponent text) throws TextException;

    int getNumberOfConsonantsInSentence(TextComponent text) throws TextException;

}
