package com.kolmakova.task4.parser.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Punctuation;
import com.kolmakova.task4.entity.impl.Word;
import com.kolmakova.task4.parser.AbstractTextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordAndPunctuationParser extends AbstractTextParser {

    private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ]+";
    private static final String WORD_PUNCTUATION_REGEX = "([\\wа-яА-ЯёЁ]+)|([\\p{Punct}\u2026])";

    public WordAndPunctuationParser() {
        this.nextParser = new LetterParser();
    }

    @Override
    public void parse(TextComponent parentComponent, String data) {
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Pattern pattern = Pattern.compile(WORD_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String group = matcher.group();
            Matcher wordMatcher = wordPattern.matcher(group);

            if (wordMatcher.matches()) {
                TextComponent word = new Word(TextComponentType.WORD);
                parentComponent.add(word);
                nextParser.parse(word, group);
            } else {
                TextComponent punctuationComponent = new Punctuation(TextComponentType.PUNCTUATION, group.charAt(0));
                parentComponent.add(punctuationComponent);
            }
        }
    }

}
