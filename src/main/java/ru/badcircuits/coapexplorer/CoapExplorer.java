package ru.badcircuits.coapexplorer;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class CoapExplorer extends Application {
    private CoapPacket packet = new CoapPacket();
    
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
        
        hexArea.textProperty().bindBidirectional(packet.payloadProperty());
        binArea.textProperty().bindBidirectional(packet.payloadProperty());
        
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