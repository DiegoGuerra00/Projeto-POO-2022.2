package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Auth;
import poo.models.Usuario;

public class Login {
    private GridPane grid;
    private Scene scene;
    private Text title;
    private Button loginButton;
    private TextField usernameTextField;
    private TextField passwdTextfield;

    public Login() {
        title = new Text("Bem Vindo");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(title, 0, 0);

        setTextfields();
        setLoginButton();

        scene = new Scene(grid, 1200, 900);
    }

    private void setLoginButton() {
        loginButton = new Button("Login");
        grid.add(loginButton, 0, 7);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Auth auth = new Auth();
                // auth.login(userTextfield.getText(), passwdTextfield.getText());
                // Usuario tmp = auth.login(usernameTextField.getText(), passwdTextfield.getText());
                // if (tmp != null) {
                    // MainMenu menu = new MainMenu();
                    // SearchScreen search = new SearchScreen();
                    CategorySelection category = new CategorySelection();
                    Window w = scene.getWindow();
                    if (w instanceof Stage) {
                        Stage s = (Stage) w;
                        s.setScene(category.getScene());
                    }
                // } else {
                //     // TODO show error screen
                // }

            }
        });
    }

    private void setTextfields() {
        usernameTextField = new TextField();
        passwdTextfield = new TextField();

        usernameTextField.setPromptText("Nome de Usuário");
        passwdTextfield.setPromptText("Senha");

        grid.add(usernameTextField, 0, 3);
        grid.add(passwdTextfield, 0, 5);
    }

    public Scene getScene() {
        return scene;
    }

}
