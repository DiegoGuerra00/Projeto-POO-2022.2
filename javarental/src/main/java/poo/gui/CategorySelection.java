package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CategorySelection {
    private VBox root;
    private Scene scene;
    private Text title;
    private Button carButton;
    private Button bikeButton;
    private Button logoutButton;

    public CategorySelection() {
        title = new Text("Deseja alugar um carro ou uma motocicleta?");
        title.setFont(new Font("Times New Roman", 16));
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(8, 8, 8, 8));
        root.setSpacing(5);
        root.getChildren().add(title);

        setButtons();

        scene = new Scene(root, 1200, 900);
    }

    public Scene getScene() {
        return scene;
    }

    private void setButtons() {
        carButton = new Button("Alugar Carro");
        root.getChildren().add(carButton);

        bikeButton = new Button("Alugar Motocicleta");
        root.getChildren().add(bikeButton);

        logoutButton = new Button("Logout");
        logoutButton.setTranslateX(100);
        logoutButton.setTranslateY(100);
        root.getChildren().add(logoutButton);

        carButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchScreen search = new SearchScreen();
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(search.getScene());
                }
            }
        });

        bikeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SearchScreen search = new SearchScreen();
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(search.getScene());
                }
            }
        });

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
    }
}
