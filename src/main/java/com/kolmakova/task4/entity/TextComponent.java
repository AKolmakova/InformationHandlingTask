package com.kolmakova.task4.entity;

import java.util.List;

public interface TextComponent {

    boolean add(TextComponent component);

    void remove(TextComponent component);

    void setChildren(List<TextComponent> children);

    List<TextComponent> getElementsByType(TextComponentType textComponentType);
}
