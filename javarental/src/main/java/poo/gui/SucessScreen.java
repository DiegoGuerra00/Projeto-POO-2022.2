package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SucessScreen {
    private GridPane grid;
    private Scene scene;
    private Button returnButton;
    private Button newRentalButton;
    private Label msgLabel;

    public SucessScreen() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        msgLabel = new Label("Aluguel Realizado com Sucesso!!!");
        grid.add(msgLabel, 0, 0);

        setButtons();
        
        scene = new Scene(grid, 800, 600);
    }

    private void setButtons() {
        returnButton = new Button("Sair");
        newRentalButton = new Button("Novo Aluguel");

        returnButton.setPrefWidth(70);
        newRentalButton.setPrefWidth(70);

        grid.add(returnButton, 0, 0);
        grid.add(newRentalButton, 0, 0);

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
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

        newRentalButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CategorySelection categorySel = new CategorySelection();
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
