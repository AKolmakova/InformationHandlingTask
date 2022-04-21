package com.kolmakova.task4.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextComponent implements TextComponent {

    protected static final String NOT_SUPPORTED_ERROR_MESSAGE = "Not available operation with this component";

    protected final TextComponentType type;
    protected List<TextComponent> components;

    protected AbstractTextComponent(TextComponentType type) {
        this.type = type;
        components = new ArrayList<>();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTextComponent that = (AbstractTextComponent) o;

        if (type != that.type) return false;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + components.hashCode();
        return result;
    }

}
