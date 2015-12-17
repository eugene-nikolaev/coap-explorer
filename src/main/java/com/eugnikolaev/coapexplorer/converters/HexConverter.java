package com.eugnikolaev.coapexplorer.converters;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import com.eugnikolaev.coapexplorer.CoapPacket;
import com.google.common.base.Splitter;

public class HexConverter extends OneSideConverter<CoapPacket> {
    
    private static final int BYTES_IN_LINE = 16;
    private static final int BYTES_IN_CHUNK = 2;
    private static final int CHARS_FOR_BYTE = 2;
    private static final int CHARS_FOR_LINE = BYTES_IN_LINE * CHARS_FOR_BYTE;
    private static final int CHARS_FOR_CHUNK = BYTES_IN_CHUNK * CHARS_FOR_BYTE;

    @Override
    protected String doConvert(CoapPacket packet) {
        byte[] payloadBytes = binarize(packet.getPayload());
        String hex = DatatypeConverter.printHexBinary(payloadBytes);        
        Splitter lineSplitter = Splitter.fixedLength(CHARS_FOR_LINE);
        List<String> lines = lineSplitter.splitToList(hex);
        Splitter chunkSplitter = Splitter.fixedLength(CHARS_FOR_CHUNK);
        List<String> chunkedLines = lines.stream().map((line) -> {
           List<String> chunks = chunkSplitter.splitToList(line);
           return String.join(" ", chunks);
        }).collect(Collectors.toList());
        return String.join("\n", chunkedLines);
    }

    
    private byte[] binarize(String source) {
        return (source == null) ? new byte[0] : source.getBytes(StandardCharsets.ISO_8859_1);
    }
}
