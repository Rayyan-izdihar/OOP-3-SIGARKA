package sigarka.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginSc {

    public static Scene createScene(Stage stage) {

        // ===== USERNAME =====
        TextField usernameField = new TextField();

        // ===== PASSWORD =====
        PasswordField passwordField = new PasswordField();

        // ===== BUTTON LOGIN =====
        Button loginBtn = new Button("LOGIN");

        // ===== INFO =====
        Label info = new Label();

        // ===== LOGIN ACTION =====
        loginBtn.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            String validUsername = "admin";
            String validPassword = "123";

            // Login berhasil
            if (username.equals(validUsername) && 
                password.equals(validPassword)) {

                
            }

            // Username atau Password salah
            else if (!username.equals(validUsername) ||
                     !password.equals(validPassword)) {

                
                info.setText("Username atau Password tidak valid!");
            }
        });

        // ===== CARD =====
        VBox card = new VBox(15);


        card.getChildren().addAll(

                usernameField,
                passwordField,
                loginBtn,
                info
        );

        // ===== ROOT =====
        StackPane root = new StackPane();
        

        root.getChildren().add(card);

        return new Scene(root, 900, 600);
    }
}