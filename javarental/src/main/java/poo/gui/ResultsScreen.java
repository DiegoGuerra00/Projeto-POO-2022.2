package poo.gui;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class ResultsScreen {
    private HBox root;
    private Scene scene;
    private ListView<String> listView;
    private DatePicker inicioDatePicker;
    private DatePicker fimDatePicker;
    private Button confirmButton;
    private Button cancelButton;
    private Carro carroSelecionado = null;
    private Motocicleta motoSelecionada = null;
    private List<Carro> carros;
    private List<Motocicleta> motos;
    private ObservableList<String> listViewItems;
    private Usuario user;
    private Separator sep;
    private EntityManager em;

    // FIXME parametro carro é gambiarra para diferenciar os construtores(devido ao
    // type erasure do Java)
    public ResultsScreen(List<Carro> carros, Carro carro, Usuario user, EntityManager em) {
        this.carros = carros;
        this.user = user;
        this.em = em;
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);

        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        getListViewItems(true);
        setDatePickers();

        sep = new Separator(Orientation.HORIZONTAL);
        sep.setPrefWidth(100);
        sep.setVisible(false);
        root.getChildren().add(sep);

        setListView(true);
        setButtons(true);

        scene = new Scene(root, 800, 600);
    }

    public ResultsScreen(List<Motocicleta> motos, Usuario user, EntityManager em) {
        this.user = user;
        this.motos = motos;
        this.em = em;
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8);
        root.setPadding(new Insets(8, 8, 8, 8));

        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        getListViewItems(false);
        setDatePickers();

        sep = new Separator(Orientation.HORIZONTAL);
        sep.setPrefWidth(100);
        sep.setVisible(false);
        root.getChildren().add(sep);

        setListView(false);
        setButtons(false);

        scene = new Scene(root, 800, 600);
    }

    private void getListViewItems(boolean isCarro) {
        if (isCarro) {
            System.out.println(carros.size());
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
        listView.setMaxHeight(300);

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

    private void setButtons(boolean isCarro) {
        confirmButton = new Button("Confirmar");
        cancelButton = new Button("Voltar");

        confirmButton.setPrefWidth(200);
        cancelButton.setPrefWidth(200);

        confirmButton.setTranslateX(-130);
        confirmButton.setTranslateY(230);

        cancelButton.setTranslateX(-390);
        cancelButton.setTranslateY(230);

        root.getChildren().add(confirmButton);
        root.getChildren().add(cancelButton);

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (checkSelection(isCarro)) {
                    ConfirmScreen confirmScreen;
                    if (isCarro) {
                        confirmScreen = new ConfirmScreen(carroSelecionado, inicioDatePicker.getValue(),
                                fimDatePicker.getValue(), user, em);
                    } else {
                        confirmScreen = new ConfirmScreen(motoSelecionada, inicioDatePicker.getValue(),
                                fimDatePicker.getValue(), user);
                    }
                    Window w = scene.getWindow();
                    if (w instanceof Stage) {
                        Stage s = (Stage) w;
                        s.setScene(confirmScreen.getScene());
                    }
                }
            }

        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                CategorySelection categorySelection = new CategorySelection(user, em);
                Window w = scene.getWindow();
                if(w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(categorySelection.getScene());
                }
            }

        });
    }

    private boolean checkSelection(boolean isCarro) {
        if (fimDatePicker.getValue() == null || fimDatePicker.getValue().isBefore(inicioDatePicker.getValue())) {
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
