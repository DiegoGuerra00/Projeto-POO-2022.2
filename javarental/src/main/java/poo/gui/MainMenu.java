package poo.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.jaxb.internal.stax.HbmEventReader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Aluguel;
import poo.models.Carro;

public class MainMenu {
    private HBox root;
    private Scene scene;
    private Button logoutButton;
    private Text title;
    private List<Carro> carros = new ArrayList<Carro>();
    private ChoiceBox cb;
    Carro selecionado;

    public Scene getScene() {
        return scene;
    }

    public MainMenu() {
        title = new Text("Main Menu");

        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        // grid.getChildren().add(title);

        // setLogoutButton();
        getCarList();
        buildScreen();
        // selectionTest();

        scene = new Scene(root, 1200, 900);
    }

    private void setLogoutButton() {
        logoutButton = new Button("Teste");
        root.getChildren().add(logoutButton);

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
        ListView<String> lv = new ListView<String>();
        lv.setPrefHeight(10);
        ObservableList<String> values = FXCollections.observableArrayList();
        for (Carro carro : carros) {
            StringBuilder str = new StringBuilder();
            str.append(carro.getModelo() + " | " + carro.getPrecoDiario());
            values.add(str.toString());
        }

        lv.setItems(values);
        lv.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(carros.get(newValue.intValue()));
                selecionado = carros.get(newValue.intValue());
            }

        });

        DatePicker inicio = new DatePicker(LocalDate.now());
        DatePicker fim = new DatePicker();

        Button getDates = new Button("Preço");
        getDates.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // System.out.println("inicio: " + inicio.getValue().toString());
                // System.out.println("fim: " + fim.getValue().toString());

            }

        });

        root.getChildren().add(lv);
        root.getChildren().add(inicio);
        root.getChildren().add(fim);
        root.getChildren().add(getDates);
    }

    // private void selectionTest() {
    // cb = new ChoiceBox<>(FXCollections.observableArrayList("Opcao 1", "Opcao 2",
    // "Opcao 3"));
    // cb.setTooltip(new Tooltip("Opcoes"));
    // cb.setValue("Teste");
    // final String[] opcoes = new String[] { "Opcao 1", "Opcao 2", "Opcao 3" };
    // cb.getSelectionModel().selectedIndexProperty().addListener(new
    // ChangeListener<Number>() {
    // @Override
    // public void changed(ObservableValue observable, Number oldValue, Number
    // newValue) {

    // }
    // });
    // root.add(cb, 2, 1);
    // }

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
