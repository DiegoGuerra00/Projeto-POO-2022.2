package poo.gui;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class ConfirmScreen {
    private HBox root;
    private Scene scene;
    private Button confirmButton;
    private Button logoutButton;
    private Button cancelButton;
    private Label priceLabel;
    private Image logo;
    private ImageView iv;
    private Usuario user;
    private Carro carro;
    private Motocicleta moto;
    private LocalDate inicio;
    private LocalDate fim;

    public ConfirmScreen(Carro carro, LocalDate inicio, LocalDate fim, Usuario user) {
        this.user = user;
        this.carro = carro;
        this.inicio = inicio;
        this.fim = fim;
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        logo = new Image("/javarental_logo.png", 300, 300, true, false);
        iv = new ImageView(logo);
        root.getChildren().add(iv);

        // TODO set price
        root.getChildren().add(priceLabel);
        setButtons();

        scene = new Scene(root, 80, 600);
    }

    public ConfirmScreen(Motocicleta moto, LocalDate inicio, LocalDate fim, Usuario user) {
        this.user = user;
        this.moto = moto;
        this.inicio = inicio;
        this.fim = fim;
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        logo = new Image("/javarental_logo.png", 300, 300, true, false);
        iv = new ImageView(logo);
        root.getChildren().add(iv);

        // TODO set price
        root.getChildren().add(priceLabel);
        setButtons();

        scene = new Scene(root, 80, 600);
    }

    public Scene getScene() {
        return scene;
    }

    private void setButtons() {
        logoutButton = new Button("Logout");
        cancelButton = new Button("Cancelar");
        confirmButton = new Button("Confirmar Aluguel");

        cancelButton.setPrefWidth(100);
        confirmButton.setPrefWidth(100);

        root.getChildren().add(logoutButton);
        root.getChildren().add(confirmButton);
        root.getChildren().add(cancelButton);

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
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

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CategorySelection category = new CategorySelection(user);
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(category.getScene());
                }
            }
        });

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                SucessScreen sucess = new SucessScreen();
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(sucess.getScene());
                }
            }

        });
    }
}
