package com.kolmakova.task4.service.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.exception.TextException;
import com.kolmakova.task4.service.TextService;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TextServiceImpl implements TextService {

    private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";
    private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";

    @Override
    public void sortParagraphsByNumberOfSentences(TextComponent text) {
        List<TextComponent> paragraphs = text.getElementsByType(TextComponentType.PARAGRAPH);

        paragraphs.sort((firstParagraph, nextParagraphs) -> {
            Integer firstParagraphSize = firstParagraph.getElementsByType(TextComponentType.SENTENCE).size();
            Integer nextParagraphsSize = nextParagraphs.getElementsByType(TextComponentType.SENTENCE).size();

            return firstParagraphSize.compareTo(nextParagraphsSize);
        });

        text.setChildren(paragraphs);
    }

    @Override
    public List<TextComponent> getSentencesWithLongestWord(TextComponent text) throws TextException {

        List<TextComponent> allSentences = text.getElementsByType(TextComponentType.SENTENCE);
        List<TextComponent> words = text.getElementsByType(TextComponentType.WORD);

        int maxLength = words.stream()
                .mapToInt(word -> word.getElementsByType(TextComponentType.LETTER).size())
                .max()
                .orElseThrow(() -> new TextException("No letters found!"));

        return allSentences.stream()
                .filter(sentence -> sentenceWithMaxWordLetters(sentence, maxLength))
                .collect(Collectors.toList());
    }

    private boolean sentenceWithMaxWordLetters(TextComponent sentence, int maxLength) {
        return sentence.getElementsByType(TextComponentType.WORD)
                .stream()
                .anyMatch(word -> word.getElementsByType(TextComponentType.LETTER).size() == maxLength);
    }

    @Override
    public void removeSentencesWhereNumberOfWordsLessThanSpecified(TextComponent text, int specifiedNumberOfWords) {
        for (TextComponent sentence : text.getElementsByType(TextComponentType.SENTENCE)) {
            int wordNumber = sentence.getElementsByType(TextComponentType.WORD).size();
            if (wordNumber < specifiedNumberOfWords) {
                text.remove(sentence);
            }
        }
    }

    @Override
    public int getNumberOfIdenticalWords(TextComponent text) {
        List<TextComponent> words = text.getElementsByType(TextComponentType.WORD);

        Map<String, Long> identicalWords = words
                .stream()
                .collect(groupingBy(word -> word.toString().toLowerCase(), counting()));

        identicalWords.values().removeIf(i -> i <= 1);

        return identicalWords.values().stream()
                .mapToInt(Long::intValue)
                .sum();
    }

    @Override
    public int getNumberOfVowelsInSentence(TextComponent text) {
        Pattern pattern = Pattern.compile(VOWEL_REGEX);
        return getElementsMatchesPattern(text, pattern);
    }

    @Override
    public int getNumberOfConsonantsInSentence(TextComponent text) {
        Pattern pattern = Pattern.compile(CONSONANT_REGEX);
        return getElementsMatchesPattern(text, pattern);
    }

    private int getElementsMatchesPattern(TextComponent text, Pattern pattern) {
        return (int) text.getElementsByType(TextComponentType.WORD).stream()
                .flatMap(word -> word.getElementsByType(TextComponentType.LETTER).stream())
                .map(TextComponent::toString)
                .filter(consonants -> pattern.matcher(consonants).matches())
                .count();
    }
}

