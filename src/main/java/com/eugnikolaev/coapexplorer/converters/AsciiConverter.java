package com.eugnikolaev.coapexplorer.converters;

import java.util.Optional;

import com.eugnikolaev.coapexplorer.CoapPacket;

import javafx.util.StringConverter;

public class AsciiConverter extends StringConverter<CoapPacket> {

    @Override
    public String toString(CoapPacket packet) {
        String payload = packet.getPayload();
        return Optional.of(payload).orElse("");
    }

    @Override
    public CoapPacket fromString(String string) {
        CoapPacket packet = new CoapPacket();
        packet.setPayload(Optional.of(string).orElse(""));
        return packet;
    }

}
