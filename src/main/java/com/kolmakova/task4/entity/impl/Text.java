package com.kolmakova.task4.entity.impl;

import com.kolmakova.task4.entity.AbstractTextComponent;
import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Text extends AbstractTextComponent {

    public Text(TextComponentType type) {
        super(type);
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public void setChildren(List<TextComponent> children) {
        this.components = children;
    }

    @Override
    public List<TextComponent> getElementsByType(TextComponentType textComponentType) {
        if (textComponentType.equals(type)) {
            return List.of(this);
        }

        return components.stream()
                .map(child -> child.getElementsByType(textComponentType))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        components.forEach(builder::append);

        return builder.toString();
    }

}
