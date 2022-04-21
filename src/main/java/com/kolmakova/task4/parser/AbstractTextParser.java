package com.kolmakova.task4.parser;

import com.kolmakova.task4.entity.TextComponent;

public abstract class AbstractTextParser implements TextParser {

    protected AbstractTextParser nextParser;

    protected AbstractTextParser() {

    }

    @Override
    public TextComponent parse(String textFromFile) {
        return nextParser.parse(textFromFile);
    }

    public abstract void parse(TextComponent parentComponent, String data);
}
