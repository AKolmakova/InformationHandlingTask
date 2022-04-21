package com.kolmakova.task4.service.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.*;
import com.kolmakova.task4.exception.TextException;
import com.kolmakova.task4.service.TextService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TextServiceImplTest {

    private TextService textService;

    @BeforeMethod
    public void setUp() {
        textService = new TextServiceImpl();
    }

    @Test
    public void testSortParagraphsByNumberOfSentences() throws TextException {
        TextComponent sentence1 = new Sentence(TextComponentType.SENTENCE);
        TextComponent sentence2 = new Sentence(TextComponentType.SENTENCE);
        TextComponent sentence3 = new Sentence(TextComponentType.SENTENCE);

        TextComponent paragraph1 = new Paragraph(TextComponentType.PARAGRAPH);
        paragraph1.add(sentence1);
        paragraph1.add(sentence2);

        TextComponent paragraph2 = new Paragraph(TextComponentType.PARAGRAPH);
        paragraph2.add(sentence1);
        paragraph2.add(sentence2);
        paragraph2.add(sentence3);

        TextComponent paragraph3 = new Paragraph(TextComponentType.PARAGRAPH);
        paragraph3.add(sentence1);

        TextComponent textComponent = new Text(TextComponentType.TEXT);
        textComponent.add(paragraph1);
        textComponent.add(paragraph2);
        textComponent.add(paragraph3);

        textService.sortParagraphsByNumberOfSentences(textComponent);
        List<TextComponent> actual = textComponent.getElementsByType(TextComponentType.PARAGRAPH);
        List<TextComponent> expected = new ArrayList<>();

        expected.add(paragraph3);
        expected.add(paragraph1);
        expected.add(paragraph2);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetSentencesWithLongestWord() throws TextException {
        TextComponent letter1 = new Letter(TextComponentType.LETTER, 'a');
        TextComponent letter2 = new Letter(TextComponentType.LETTER, 'b');
        TextComponent letter3 = new Letter(TextComponentType.LETTER, 'c');
        TextComponent letter4 = new Letter(TextComponentType.LETTER, 'd');
        TextComponent letter5 = new Letter(TextComponentType.LETTER, 'e');
        TextComponent letter6 = new Letter(TextComponentType.LETTER, 'f');
        TextComponent letter7 = new Letter(TextComponentType.LETTER, 'j');

        TextComponent word1 = new Word(TextComponentType.WORD);
        word1.add(letter1);
        word1.add(letter2);
        word1.add(letter3);
        word1.add(letter4);

        TextComponent word2 = new Word(TextComponentType.WORD);
        word2.add(letter2);
        word2.add(letter3);
        word2.add(letter5);

        TextComponent word3 = new Word(TextComponentType.WORD);
        word3.add(letter6);
        word3.add(letter7);

        TextComponent word4 = new Word(TextComponentType.WORD);
        word4.add(letter1);
        word4.add(letter2);
        word4.add(letter3);
        word4.add(letter4);
        word4.add(letter5);
        word4.add(letter6);
        word4.add(letter7);

        TextComponent word5 = new Word(TextComponentType.WORD);
        word5.add(letter1);
        word5.add(letter2);
        word5.add(letter3);

        TextComponent sentence1 = new Sentence(TextComponentType.SENTENCE);
        sentence1.add(word1);
        sentence1.add(word2);
        sentence1.add(word4);

        TextComponent sentence2 = new Sentence(TextComponentType.SENTENCE);
        sentence2.add(word3);
        sentence2.add(word5);
        sentence2.add(word4);

        TextComponent sentence3 = new Sentence(TextComponentType.SENTENCE);
        sentence3.add(word3);
        sentence3.add(word5);
        sentence3.add(word1);
        sentence3.add(word2);

        TextComponent paragraphComponent = new Paragraph(TextComponentType.PARAGRAPH);
        paragraphComponent.add(sentence1);
        paragraphComponent.add(sentence2);
        paragraphComponent.add(sentence3);

        List<TextComponent> actual = textService.getSentencesWithLongestWord(paragraphComponent);
        List<TextComponent> expected = new ArrayList<>();

        expected.add(paragraphComponent.getElementsByType(TextComponentType.SENTENCE).get(0));
        expected.add(paragraphComponent.getElementsByType(TextComponentType.SENTENCE).get(1));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemoveSentencesWhereNumberOfWordsLessThanSpecified() throws TextException {
        TextComponent word1 = new Word(TextComponentType.WORD);
        TextComponent word2 = new Word(TextComponentType.WORD);
        TextComponent word3 = new Word(TextComponentType.WORD);
        TextComponent word4 = new Word(TextComponentType.WORD);
        TextComponent word5 = new Word(TextComponentType.WORD);

        TextComponent sentence1 = new Sentence(TextComponentType.SENTENCE);
        sentence1.add(word1);
        sentence1.add(word2);
        sentence1.add(word4);

        TextComponent sentence2 = new Sentence(TextComponentType.SENTENCE);
        sentence2.add(word5);
        sentence2.add(word4);

        TextComponent sentence3 = new Sentence(TextComponentType.SENTENCE);
        sentence3.add(word3);
        sentence3.add(word5);
        sentence3.add(word1);
        sentence3.add(word2);

        TextComponent paragraphComponent = new Paragraph(TextComponentType.PARAGRAPH);
        paragraphComponent.add(sentence1);
        paragraphComponent.add(sentence2);
        paragraphComponent.add(sentence3);

        textService.removeSentencesWhereNumberOfWordsLessThanSpecified(paragraphComponent, 4);
        List<TextComponent> actual = paragraphComponent.getElementsByType(TextComponentType.SENTENCE);
        List<TextComponent> expected = new ArrayList<>();
        expected.add(sentence3);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetNumberOfIdenticalWords() throws TextException {
        TextComponent sentence = createSentence();
        int actual = textService.getNumberOfIdenticalWords(sentence);

        Assert.assertEquals(actual, 2);
    }

    @Test
    public void testGetNumberOfConsonantsInSentence() throws TextException {
        TextComponent sentence = createSentence();
        int actual = textService.getNumberOfConsonantsInSentence(sentence);

        Assert.assertEquals(actual, 8);
    }

    @Test
    public void testGetNumberOfVowelsInSentence() throws TextException {
        TextComponent sentence = createSentence();
        int actual = textService.getNumberOfVowelsInSentence(sentence);

        Assert.assertEquals(actual, 4);
    }

    private TextComponent createSentence() {
        TextComponent letter1 = new Letter(TextComponentType.LETTER, 'a');
        TextComponent letter2 = new Letter(TextComponentType.LETTER, 'b');
        TextComponent letter3 = new Letter(TextComponentType.LETTER, 'c');
        TextComponent letter4 = new Letter(TextComponentType.LETTER, 'd');
        TextComponent letter5 = new Letter(TextComponentType.LETTER, 'e');

        TextComponent word1 = new Word(TextComponentType.WORD);
        word1.add(letter1);
        word1.add(letter2);
        word1.add(letter3);

        TextComponent word2 = new Word(TextComponentType.WORD);
        word2.add(letter2);
        word2.add(letter3);
        word2.add(letter5);

        TextComponent word3 = new Word(TextComponentType.WORD);
        word3.add(letter3);
        word3.add(letter5);
        word3.add(letter4);

        TextComponent word4 = new Word(TextComponentType.WORD);
        word4.add(letter1);
        word4.add(letter2);
        word4.add(letter3);

        TextComponent sentenceComponent = new Sentence(TextComponentType.SENTENCE);
        sentenceComponent.add(word1);
        sentenceComponent.add(word2);
        sentenceComponent.add(word3);
        sentenceComponent.add(word4);

        return sentenceComponent;
    }

}
