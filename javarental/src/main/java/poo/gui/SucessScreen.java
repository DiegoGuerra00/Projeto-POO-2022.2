package poo.gui;

import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Usuario;

public class SucessScreen {
    private VBox root;
    private Scene scene;
    private Button returnButton;
    private Button newRentalButton;
    private Label msgLabel;
    private Usuario user;
    private EntityManager em;
    private Image logo;
    private ImageView iv;

    public SucessScreen(Usuario user, EntityManager em) {
        this.user = user;
        this.em = em;
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        logo = new Image("/javarental_logo.png", 300, 300 , true, false);
        iv = new ImageView(logo);

        root.getChildren().add(iv);

        msgLabel = new Label("Aluguel Realizado com Sucesso!!!");
        root.getChildren().add(msgLabel);

        setButtons();
        
        scene = new Scene(root, 800, 600);
    }

    private void setButtons() {
        returnButton = new Button("Sair");
        newRentalButton = new Button("Novo Aluguel");

        returnButton.setPrefWidth(70);
        newRentalButton.setPrefWidth(70);

        root.getChildren().add(returnButton);
        root.getChildren().add(newRentalButton);

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login login = new Login(em);
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(login.getScene());
                }
            }
        });

        newRentalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CategorySelection categorySel = new CategorySelection(user, em);
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(categorySel.getScene());
                }
            }
        });

    }

    public Scene getScene() {
        return scene;
    }
}
