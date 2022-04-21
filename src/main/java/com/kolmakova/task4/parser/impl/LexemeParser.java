package com.kolmakova.task4.parser.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Lexeme;
import com.kolmakova.task4.parser.AbstractTextParser;

public class LexemeParser extends AbstractTextParser {

    private static final String LEXEME_SPLITTER = "\\s";

    public LexemeParser() {
        this.nextParser = new WordAndPunctuationParser();
    }

    @Override
    public void parse(TextComponent parentComponent, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER);

        for (String lexemeStr : lexemes) {
            TextComponent lexeme = new Lexeme(TextComponentType.LEXEME);
            parentComponent.add(lexeme);
            nextParser.parse(lexeme, lexemeStr);
        }
    }

}
