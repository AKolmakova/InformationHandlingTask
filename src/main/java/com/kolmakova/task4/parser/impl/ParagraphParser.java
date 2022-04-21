package com.kolmakova.task4.parser.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Paragraph;
import com.kolmakova.task4.parser.AbstractTextParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser extends AbstractTextParser {

    private static final String PARAGRAPH_SPLITTER = "\\n";

    public ParagraphParser() {
        this.nextParser = new SentenceParser();
    }

    @Override
    public void parse(TextComponent parentComponent, String data) {
        List<String> paragraphs = Stream.of(data.split(PARAGRAPH_SPLITTER))
                .filter(paragraph -> !paragraph.isEmpty())
                .collect(Collectors.toList());

        for (String paragraphStr : paragraphs) {
            TextComponent paragraph = new Paragraph(TextComponentType.PARAGRAPH);
            parentComponent.add(paragraph);
            nextParser.parse(paragraph, paragraphStr);
        }
    }

}
