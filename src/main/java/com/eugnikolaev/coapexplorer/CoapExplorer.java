package com.eugnikolaev.coapexplorer;

import java.awt.Font;

import com.eugnikolaev.coapexplorer.converters.AsciiConverter;
import com.eugnikolaev.coapexplorer.converters.HexConverter;

import javafx.application.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class CoapExplorer extends Application {
    private ObjectProperty<CoapPacket> packetProp = new SimpleObjectProperty<>();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CoAP Explorer");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        

        TextArea hexArea = new TextArea();
        TextArea binArea = new TextArea();

        CoapPacket packet = new CoapPacket();
        packet.setPayload("PAYLOAD");
        packetProp.setValue(packet);

        binArea.textProperty().bindBidirectional(packetProp, new AsciiConverter());
        
        hexArea.textProperty().bindBidirectional(packetProp, new HexConverter());
        hexArea.setEditable(false);
        hexArea.setFont(javafx.scene.text.Font.font(Font.MONOSPACED));

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        VBox.setMargin(hexArea, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(hexArea);
        VBox.setMargin(binArea, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(binArea);

        root.getChildren().add(vbox);
       
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}