package com.eugnikolaev.coapexplorer;

import java.io.UnsupportedEncodingException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import javax.xml.bind.DatatypeConverter;

import javafx.application.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class CoapExplorer extends Application {
    private CoapPacket packet = new CoapPacket();
    private ObjectProperty<CoapPacket> packetProp = new SimpleObjectProperty<>();
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CoAP Tester");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        
        TextArea hexArea = new TextArea();
        TextArea binArea = new TextArea();
        
       
        packet.setPayload("PAYLOAD");
        packetProp.setValue(packet);
        
        hexArea.textProperty().bindBidirectional(packetProp, new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                System.out.println(obj + " " + pos);
                if (obj == null) {
                    System.err.println("Obj is null");
                    return toAppendTo;
                }
               
                CoapPacket p = (CoapPacket) obj;
                String payload = p.getPayload();
                try {
                    String hex = DatatypeConverter.printHexBinary(payload.getBytes("utf-8"));
                    return new StringBuffer(hex);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public CoapPacket parseObject(String source, ParsePosition pos) {
                CoapPacket packet = new CoapPacket();
                byte[] bytes = DatatypeConverter.parseHexBinary(source);
                try {
                    String payload = new String(bytes, "UTF-8");
                    packet.setPayload(payload);
                    int lastIndex = payload.length();
                    pos.setIndex(lastIndex);
                    return packet;
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                
            }
        });
        binArea.textProperty().bindBidirectional(packetProp, new Format() {

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                if (obj == null) {
                    return toAppendTo;
                }
                CoapPacket p = (CoapPacket) obj;
                String payload = p.getPayload();
                return new StringBuffer(payload);
            }

            @Override
            public CoapPacket parseObject(String source, ParsePosition pos) {
                CoapPacket packet = new CoapPacket();
                packet.setPayload(source);
                int lastIndex = source.length();
                pos.setIndex(lastIndex);
                return packet;
            }
            
        });
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        root.getChildren().add(hexArea);
        root.getChildren().add(binArea);
        
        VBox.setMargin(hexArea, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(hexArea);
        
        VBox.setMargin(binArea, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(binArea);
        
        root.getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}