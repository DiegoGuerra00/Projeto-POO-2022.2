package poo.gui;

import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
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
    private Button registerButton;
    private TextField usernameTextField;
    private TextField passwdTextfield;
    private Image logo;
    private ImageView iv;
    private EntityManager em;

    public Login(EntityManager em) {
        this.em = em;
        title = new Text("Bem Vindo");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(title, 0, 1);

        logo = new Image("/javarental_logo.png", 300, 300, true, false);
        iv = new ImageView(logo);
        grid.add(iv, 0, 0);

        setTextfields();
        setButtons();

        scene = new Scene(grid, 800, 600);
    }

    private void setButtons() {
        loginButton = new Button("Login");
        loginButton.setPrefWidth(100);

        registerButton = new Button("Criar Conta");
        registerButton.setPrefWidth(100);

        grid.add(loginButton, 0, 8);
        grid.add(registerButton, 0, 9);

        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(col);

        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                RegisterScreen register = new RegisterScreen(em);
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(register.getScene());
                }
            }

        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Auth auth = new Auth();
                Usuario tmp = new Usuario();
                try {
                    System.out.println("Login Iniciado!!");
                    tmp = auth.login(usernameTextField.getText(),
                            passwdTextfield.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (tmp != null) {
                    CategorySelection category = new CategorySelection(tmp, em);
                    Window w = scene.getWindow();
                    if (w instanceof Stage) {
                        Stage s = (Stage) w;
                        s.setScene(category.getScene());
                    }
                } else {
                }

            }
        });
    }

    private void setTextfields() {
        usernameTextField = new TextField();
        passwdTextfield = new PasswordField();

        // usernameTextField.setPrefWidth(205);
        // passwdTextfield.setPrefWidth(205);

        usernameTextField.setPromptText("Nome de Usuário");
        passwdTextfield.setPromptText("Senha");

        grid.add(usernameTextField, 0, 4);
        grid.add(passwdTextfield, 0, 6);
    }

    public Scene getScene() {
        return scene;
    }

}
