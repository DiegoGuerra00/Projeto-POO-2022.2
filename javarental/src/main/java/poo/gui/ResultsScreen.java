package poo.gui;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import poo.models.Carro;
import poo.models.Motocicleta;

public class ResultsScreen {
    private HBox root;
    private Scene scene;
    private ListView<String> listView;
    private DatePicker inicioDatePicker;
    private DatePicker fimDatePicker;
    private Carro carroSelecionado;
    private Motocicleta motoSelecionada;
    private List<Carro> carros;
    private List<Motocicleta> motos;
    private ObservableList<String> listViewItems;

    public ResultsScreen(Carro carro) {
        this.carroSelecionado = carro;
        root = new HBox();

        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        getListViewItems(true);
        setListView(true);

        scene = new Scene(root, 800, 600);
    }

    public ResultsScreen(Motocicleta moto) {
        this.motoSelecionada = moto;
        root = new HBox();

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
    }

    public Scene getScene() {
        return scene;
    }
}
