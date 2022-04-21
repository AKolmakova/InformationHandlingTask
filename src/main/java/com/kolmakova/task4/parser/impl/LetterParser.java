package com.kolmakova.task4.parser.impl;

import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;
import com.kolmakova.task4.entity.impl.Letter;
import com.kolmakova.task4.parser.AbstractTextParser;

public class LetterParser extends AbstractTextParser {

    @Override
    public void parse(TextComponent parentComponent, String data) {
        char[] letters = data.toCharArray();

        for (char letter : letters) {
            parentComponent.add(new Letter(TextComponentType.LETTER, letter));
        }
    }

}
