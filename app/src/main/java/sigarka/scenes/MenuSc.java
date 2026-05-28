package sigarka.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuSc {

    public static Scene createScene(Stage stage) {

        // ===== BUTTON LOGOUT =====
        Button btnLogout = LogoutSc.createLogoutButton(stage);

        // ===== CARD =====
        VBox card = new VBox(18);


        card.getChildren().addAll(
                
                btnLogout
        );

        
        // ===== ROOT =====
        StackPane root = new StackPane();


        root.getChildren().addAll(
                card
        );

        return new Scene(root, 900, 600);
    }
}