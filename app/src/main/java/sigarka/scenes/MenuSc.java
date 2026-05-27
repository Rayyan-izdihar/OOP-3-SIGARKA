package sigarka.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuSc {

    public static Scene createScene(Stage stage) {


        // ===== BUTTON =====
        
        Button btnLogout = new Button("Logout");


        // ===== ACTION =====

        VBox layoutLogout = LogoutSc.createScene(stage);
        btnLogout.setOnAction(e ->  {
                layoutLogout.setVisible(true); });


        // ===== CARD =====
        VBox card = new VBox(18);


        card.getChildren().addAll(
                
                btnLogout
        );

        
        // ===== ROOT =====
        StackPane root = new StackPane();


        root.getChildren().addAll(
                card,
                layoutLogout
        );

        return new Scene(root, 900, 600);
    }
}