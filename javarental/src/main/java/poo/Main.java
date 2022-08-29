package poo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poo.gui.Login;
import poo.gui.Login;
import poo.models.Aluguel;
import poo.models.Auth;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Login login = new Login();
        Scene scene = login.getScene();


        primaryStage.setTitle("JavaRental");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
