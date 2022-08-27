package poo.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;

public class MainMenu {
    private AnchorPane anchor;
    private Scene scene;
    private Button logoutButton;
    private Text title;
    private List<Carro> carros = new ArrayList<Carro>();

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
        getCarList();
        buildScreen();

        scene = new Scene(anchor, 1200, 900);
    }

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

    // TODO find a way to set a card for each car on the list
    private void buildScreen() {
        Rectangle box = new Rectangle();
        box.setWidth(100);
        box.setHeight(100);
        box.setFill(Color.DARKGRAY);
        StackPane stack = new StackPane();
        Text model = new Text();
        model.setText(carros.get(0).getModelo());
        Text marca = new Text(carros.get(0).getMarca());


        stack.getChildren().addAll(box, model, marca);
        anchor.getChildren().add(stack);
        // for (Carro carro : carros) {
        //     model.setText(carro.getModelo());
        //     stack.getChildren().addAll(box, model);
        //     anchor.getChildren().add(stack);
        // }
    }

    private void getCarList() {
        Carro car = new Carro();
        car.setAno(1995);
        car.setAssentos(5);
        car.setCor("Prata");
        car.setMarca("Honda");
        car.setModelo("Civic VTI");
        car.setPrecoDiario(200);

        Carro car1 = new Carro();
        car1.setAno(1998);
        car1.setAssentos(5);
        car1.setCor("Preto");
        car1.setMarca("BMW");
        car1.setModelo("328I Touring");
        car1.setPrecoDiario(500);

        Carro car2 = new Carro();
        car2.setAno(2006);
        car2.setAssentos(5);
        car2.setCor("Preto");
        car2.setMarca("Honda");
        car2.setModelo("Civic SI");
        car2.setPrecoDiario(300);

        carros.add(car);
        carros.add(car1);
        carros.add(car2);
    }
}
