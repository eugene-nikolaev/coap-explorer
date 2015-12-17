package com.eugnikolaev.coapexplorer.converters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.eugnikolaev.coapexplorer.CoapPacket;

public class HexConverterTest {
    
    private HexConverter converter;
    
    @Before
    public void setUp() {
       converter = new HexConverter();
    }
    
    @Test
    public void convertsNull() {
        assertEquals("", convert(null));
    }
    
    @Test
    public void convertsEmptyText() {
        assertEquals("", convert(""));
    }
    
    @Test
    public void convertsOneCharacter() {
        assertEquals("31", convert("1"));
    }
    
    @Test
    public void convertsTwoCharacterText() {
        assertEquals("3132", convert("12"));
    }
    
    @Test
    public void convertsThreeChars() {
        assertEquals("3132 33", convert("123"));
    }
    
    @Test
    public void convertsEvenCountOfChars() {
        assertEquals("3132 3334", convert("1234"));
    }  
    
    @Test
    public void convertsOddCountOfChars() {
        assertEquals("3132 3334 35", convert("12345"));
    }
    
    @Test
    public void convertsLines() {
        assertEquals("3132 3334 3132 3334 3132 3334 3132 3334\n3132 3334 3132 3334 3132 3334 3132 3334", convert("12341234123412341234123412341234"));
    }
    
    private String convert(String payload) {
        return converter.doConvert(packet(payload));
    }
    
    private CoapPacket packet(String payload) {
        CoapPacket packet = new CoapPacket();
        packet.setPayload(payload);
        return packet;
    }

}
