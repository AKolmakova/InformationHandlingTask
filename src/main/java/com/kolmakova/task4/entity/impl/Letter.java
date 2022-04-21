package com.kolmakova.task4.entity.impl;

import com.kolmakova.task4.entity.AbstractTextComponent;
import com.kolmakova.task4.entity.TextComponent;
import com.kolmakova.task4.entity.TextComponentType;

import java.util.ArrayList;
import java.util.List;

public class Letter extends AbstractTextComponent {

    private final char symbol;

    public Letter(TextComponentType componentType, char symbol) {
        super(componentType);
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
        throw new UnsupportedOperationException(NOT_SUPPORTED_ERROR_MESSAGE);
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Letter letter1 = (Letter) o;

        return symbol == letter1.symbol;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + symbol;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
