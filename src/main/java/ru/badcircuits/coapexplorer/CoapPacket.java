package ru.badcircuits.coapexplorer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CoapPacket {
    private SimpleStringProperty payload = new SimpleStringProperty();

    public String getPayload() {
        return payload.get();
    }

    public void setPayload(String value) {
        payload.set(value);
    }

    public StringProperty payloadProperty() {
        return payload;
    }
}
