package com.eugnikolaev.coapexplorer.converters;

import javafx.util.StringConverter;

public abstract class OneSideConverter<T> extends StringConverter<T> {

    @Override
    public final String toString(T object) {
        try {
            return doConvert(object);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    protected abstract String doConvert(T object);

    @Override
    public T fromString(String string) {
        throw new RuntimeException("Should not be called");
    }

}
