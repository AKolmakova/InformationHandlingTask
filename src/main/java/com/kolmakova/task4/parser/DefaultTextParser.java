package com.kolmakova.task4.parser;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Text;
import com.kolmakova.task4.parser.impl.ParagraphParser;

public class DefaultTextParser implements TextParser {

    private final AbstractTextParser startParser;

    public DefaultTextParser() {
        startParser = new ParagraphParser();
    }

    @Override
    public TextComponent parse(String textFromFile) {
        TextComponent rootElement = new Text(TextComponentType.TEXT);
        startParser.parse(rootElement, textFromFile);

        return rootElement;
    }

}
