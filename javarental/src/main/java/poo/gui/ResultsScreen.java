package poo.gui;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;
import poo.models.Motocicleta;

public class ResultsScreen {
    private HBox root;
    private Scene scene;
    private ListView<String> listView;
    private DatePicker inicioDatePicker;
    private DatePicker fimDatePicker;
    private Button confirmButton;
    private Carro carroSelecionado = null;
    private Motocicleta motoSelecionada = null;
    private List<Carro> carros;
    private List<Motocicleta> motos;
    private ObservableList<String> listViewItems;

    // FIXME parametro carro é gambiarra para diferenciar os construtores(devido ao
    // type erasure do Java)
    public ResultsScreen(List<Carro> carros, Carro carro) {
        this.carros = carros;
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        getListViewItems(true);
        setListView(true);
        setDatePickers();
        setConfirmButton(true);

        scene = new Scene(root, 800, 600);
    }

    public ResultsScreen(List<Motocicleta> motos) {
        this.motos = motos;
        root = new HBox();

        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        getListViewItems(false);
        setListView(false);
        setDatePickers();
        setConfirmButton(false);

        scene = new Scene(root, 800, 600);
    }

    private void getListViewItems(boolean isCarro) {
        if (isCarro) {
            for (Carro carro : carros) {
                StringBuilder str = new StringBuilder();
                str.append(carro.getModelo() + " | " + carro.getPrecoDiario());
                listViewItems.add(str.toString());
            }
        } else {
            for (Motocicleta moto : motos) {
                StringBuilder str = new StringBuilder();
                str.append(moto.getModelo() + " | " + moto.getPrecoDiario());
                listViewItems.add(str.toString());
            }
        }

    }

    private void setListView(boolean isCarro) {
        listView.setItems(listViewItems);
        listView.setPrefHeight(300);

        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(carros.get(newValue.intValue()));
                if (isCarro) {
                    carroSelecionado = carros.get(newValue.intValue());
                } else {
                    motoSelecionada = motos.get(newValue.intValue());
                }
            }

        });

        root.getChildren().add(listView);
    }

    private void setConfirmButton(boolean isCarro) {
        confirmButton = new Button("Confirmar");

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (checkSelection(isCarro)) {
                    ConfirmScreen confirmScreen = new ConfirmScreen();
                    Window w = scene.getWindow();
                    if (w instanceof Stage) {
                        Stage s = (Stage) w;
                        s.setScene(confirmScreen.getScene());
                    }
                }
            }

        });
    }

    private boolean checkSelection(boolean isCarro) {
        if (fimDatePicker.getValue().isBefore(inicioDatePicker.getValue()) || fimDatePicker.getValue() == null) {
            return false;
        }

        if (isCarro) {
            if (carroSelecionado == null) {
                return false;
            }
        } else {
            if (motoSelecionada == null) {
                return false;
            }
        }

        return true;
    }

    private void setDatePickers() {
        inicioDatePicker = new DatePicker(LocalDate.now());
        fimDatePicker = new DatePicker();

        root.getChildren().addAll(inicioDatePicker, fimDatePicker);
    }

    public Scene getScene() {
        return scene;
    }
}
