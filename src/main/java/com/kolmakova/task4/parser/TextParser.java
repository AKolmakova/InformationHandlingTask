package com.kolmakova.task4.parser;

import com.kolmakova.task4.entity.TextComponent;

public interface TextParser {
    TextComponent parse(String textFromFile);
}
