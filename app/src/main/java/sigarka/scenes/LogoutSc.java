package sigarka.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogoutSc {
    
    public static VBox createScene(Stage stage) {
        
        // ===== LABEL TANYA =====
        Label labelTanya = new Label("Yakin ingin logout?");


        // ===== BUTTON =====
        Button btnNo = new Button("No");
        Button btnYes = new Button("Yes");


        // ===== LAYOUT TOMBOL =====
        HBox layoutTombol = new HBox(10); 
        
        layoutTombol.getChildren().addAll(
                btnNo,
                btnYes
        );


        // ===== LAYOUT LOGOUT SCENE =====
        VBox layoutLogout = new VBox(15);

        layoutLogout.getChildren().addAll(
                labelTanya,
                layoutTombol
        );


        // ===== ACTION =====
        layoutLogout.setVisible(false);
        btnNo.setOnAction(e ->
                layoutLogout.setVisible(false)
        );

        btnYes.setOnAction(e -> {});


        return layoutLogout;
    }
}