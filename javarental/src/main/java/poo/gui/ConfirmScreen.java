package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ConfirmScreen {
    private GridPane grid;
    private Scene scene;
    private Button confirmButton;
    private Button logoutButton;
    private Button cancelButton;
    private Label priceLabel;
    private Image logo;

    public ConfirmScreen() {
        grid = new GridPane();

        logo = new Image("file:javarental_logo.png", true);

        // TODO set price
        grid.add(priceLabel, 0, 1);
        setButtons();

        scene = new Scene(grid, 80, 600);
    }

    public Scene getScene() {
        return scene;
    }

    private void setButtons() {
        logoutButton = new Button("Logout");
        cancelButton = new Button("Cancelar");
        confirmButton = new Button("Confirmar Aluguel");

        cancelButton.setPrefWidth(70);
        confirmButton.setPrefWidth(70);

        grid.add(logoutButton, 0, 0);
        grid.add(confirmButton, 0, 2);
        grid.add(cancelButton, 0, 3);

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
                CategorySelection category = new CategorySelection(); 
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
                if(w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(sucess.getScene());
                } 
            }
            
        });
    }
}
