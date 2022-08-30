package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterScreen {
    private GridPane grid;
    private Scene scene;
    Label title;
    private TextField nomeTextField;
    private TextField sobrenomeTextField;
    private TextField usernameTextField;
    private TextField senhaTextField;
    private TextField cpfTextField;
    private TextField emailTextField;
    private Button registerButton;
    private Button cancelButton;
    private Image logo;
    private ImageView iv;

    public RegisterScreen() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(8);
        grid.setVgap(8);
        grid.setPadding(new Insets(8, 8,8, 8));

        logo = new Image("/javarental_logo.png", 300, 300, true, false);
        iv = new ImageView(logo);
        grid.add(iv, 0, 0);

        title = new Label("Criar Conta");
        title.setFont(new Font("TIme New Roman", 20));
        grid.add(title, 0, 1);

        setTextFields();
        setButtons();

        scene = new Scene(grid, 800, 600);
    }

    private void setTextFields() {
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(col);

        nomeTextField = new TextField();
        sobrenomeTextField = new TextField();
        usernameTextField = new TextField();
        senhaTextField = new TextField();
        cpfTextField = new TextField();
        emailTextField = new TextField();

        nomeTextField.setPromptText("Nome");
        sobrenomeTextField.setPromptText("Sbrenome");
        usernameTextField.setPromptText("Nome de Usuário");
        senhaTextField.setPromptText("Senha");
        cpfTextField.setPromptText("CPF");
        emailTextField.setPromptText("e-mail");

        grid.add(nomeTextField, 0, 2);
        grid.add(sobrenomeTextField, 0, 3);
        grid.add(cpfTextField, 0, 4);
        grid.add(emailTextField, 0, 5);
        grid.add(usernameTextField, 0, 6);
        grid.add(senhaTextField, 0, 7);
    }

    private void setButtons() {
        registerButton = new Button("Criar Conta");
        cancelButton = new Button("Cancelar");

        cancelButton.setTranslateX(330);
        cancelButton.setTranslateY(-130);

        grid.add(registerButton, 0, 8);
        grid.add(cancelButton, 0, 0);

        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO criar conta
                Login login = new Login();
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(login.getScene());
                }
            }

        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Login login = new Login();
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(login.getScene());
                }
            }

        });
    }

    public Scene getScene() {
        return scene;
    }
}
