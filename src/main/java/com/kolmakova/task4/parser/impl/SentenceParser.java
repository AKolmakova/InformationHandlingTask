package com.kolmakova.task4.parser.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Sentence;
import com.kolmakova.task4.parser.AbstractTextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {

    private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).+?([.!?\\u2026])(\\s|$)";

    public SentenceParser() {
        this.nextParser = new LexemeParser();
    }

    @Override
    public void parse(TextComponent parentComponent, String data) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String sentenceStr = matcher.group();
            TextComponent sentence = new Sentence(TextComponentType.SENTENCE);
            parentComponent.add(sentence);
            nextParser.parse(sentence, sentenceStr);
        }
    }

}
