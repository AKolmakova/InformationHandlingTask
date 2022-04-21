package com.kolmakova.task4.entity.impl;

import com.kolmakova.task4.entity.AbstractTextComponent;
import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Punctuation extends AbstractTextComponent {

    private final char symbol;

    public Punctuation(TextComponentType type, char symbol) {
        super(type);
        this.symbol = symbol;
    }

    @Override
    public boolean add(TextComponent component) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_ERROR_MESSAGE);
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_ERROR_MESSAGE);
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

        return new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Punctuation)) return false;
        if (!super.equals(o)) return false;
        Punctuation that = (Punctuation) o;
        return symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbol);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
