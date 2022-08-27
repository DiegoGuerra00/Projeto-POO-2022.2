package poo.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainMenu {
    private AnchorPane anchor;
    private Scene scene;
    private Button logoutButton;
    private Text title;

    public Scene getScene() {
        return scene;
    }

    public AnchorPane getAnchor() {
        return anchor;
    }

    public MainMenu() {
        title = new Text("Main Menu");

        anchor = new AnchorPane();
        anchor.getChildren().add(title);

        setLogoutButton();

        scene = new Scene(anchor, 1200, 900);
    }

    // TODO criar classe para o botao
    private void setLogoutButton() {
        logoutButton = new Button("Teste");
        anchor.getChildren().add(logoutButton);

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
