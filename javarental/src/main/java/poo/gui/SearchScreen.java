package poo.gui;

import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;
import poo.models.Motocicleta;

public class SearchScreen {
    private static final double WIDTH = 150;
    private GridPane grid;
    private Scene scene;
    private Button logoutButton;
    private Button searchButton;

    private ChoiceBox<String> marcaBox;
    private ChoiceBox<String> modeloBox;
    private ChoiceBox<String> anoBox;
    private ChoiceBox<String> corBox;
    private ChoiceBox<String> assentosBox;
    private ChoiceBox<String> categoriaBox;
    private ChoiceBox<String> tipoCarroBox; // Usado apenas quando a categoria é carro
    private ChoiceBox<String> tipoMotoBox;//Usado apenas quando é moto
    private ChoiceBox<String> portasBox; // Usado apenas quando a categoria é carro
    private ChoiceBox<String> cilindradasBox; //Usado apenas quando é moto

    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private String assentos;
    private String categoria;
    private boolean isCarro;

    public SearchScreen(boolean isCarro) {
        this.isCarro = isCarro;
        grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(8);
        grid.setPadding(new Insets(8, 8, 8, 8));
        grid.setAlignment(Pos.CENTER);

        setButtons();
        setChoiceBoxes();

        scene = new Scene(grid, 800, 600);
    }

    public Scene getScene() {
        return scene;
    }

    private void setChoiceBoxes() {
        tipoCarroBox = new ChoiceBox<String>(FXCollections.observableArrayList("Sedan", "Wagon", "Hatch"));
        tipoCarroBox.setValue("Tipo");
        tipoCarroBox.setPrefWidth(WIDTH);

        tipoMotoBox = new ChoiceBox<String>(FXCollections.observableArrayList("Sedan", "Wagon", "Hatch"));
        tipoMotoBox.setValue("Tipo");
        tipoMotoBox.setPrefWidth(WIDTH);
        grid.add(tipoMotoBox, 1, 1);

        grid.add(tipoCarroBox, 1, 1);
        tipoCarroBox = new ChoiceBox<String>(FXCollections.observableArrayList("Sedan", "Wagon", "Hatch"));
        tipoCarroBox.setValue("Tipo");
        tipoCarroBox.setPrefWidth(WIDTH);
        grid.add(tipoCarroBox, 1, 1);

        marcaBox = new ChoiceBox<String>(FXCollections.observableArrayList("Honda", "BMW", "Nissan"));
        marcaBox.setValue("Marca");
        marcaBox.setPrefWidth(WIDTH);
        grid.add(marcaBox, 2, 1);


        anoBox = new ChoiceBox<String>(FXCollections.observableArrayList("1999", "1998", "1995"));
        anoBox.setValue("Ano");
        anoBox.setPrefWidth(WIDTH);
        grid.add(anoBox, 4, 1);

        corBox = new ChoiceBox<String>(FXCollections.observableArrayList("Vermelho", "Prata", "Azul"));
        corBox.setValue("Cor");
        corBox.setPrefWidth(WIDTH);
        grid.add(corBox, 1, 2);

        assentosBox = new ChoiceBox<String>(FXCollections.observableArrayList("4", "5"));
        assentosBox.setValue("Assentos"); 
        assentosBox.setPrefWidth(WIDTH);
        grid.add(assentosBox, 2, 2);

        portasBox = new ChoiceBox<String>(FXCollections.observableArrayList("2", "4"));
        portasBox.setValue("Portas"); 
        portasBox.setPrefWidth(WIDTH);
        grid.add(portasBox, 3, 1);

        cilindradasBox = new ChoiceBox<String>(FXCollections.observableArrayList("100", "150", "200"));
        cilindradasBox.setValue("Cilindradas"); 
        cilindradasBox.setPrefWidth(WIDTH);
        grid.add(cilindradasBox, 3, 1);

        if (isCarro) {
            portasBox.setVisible(true);
            tipoCarroBox.setVisible(true);
            tipoMotoBox.setVisible(false);
            cilindradasBox.setVisible(false);
        } else {
            portasBox.setVisible(false);
            tipoCarroBox.setVisible(false);
            tipoMotoBox.setVisible(true);
            cilindradasBox.setVisible(true);
        }

    }

    private void setButtons() {
        logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #ff0000");
        logoutButton.setTranslateX(50);
        logoutButton.setTranslateY(-220);
        grid.add(logoutButton, 5, 0);

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

        searchButton = new Button("Buscar");
        searchButton.setTranslateX(50);
        searchButton.setTranslateY(220);
        grid.add(searchButton, 5, 3);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ResultsScreen results;
                if (isCarro) {
                    results = new ResultsScreen(new ArrayList<>(), new Carro()); // TODO executeQuery aqui
                } else {
                    results = new ResultsScreen(new ArrayList<>());
                }
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(results.getScene());
                }
            }

        });
    }

    public List executeQuery(String marca, String modelo, String ano, String cor, String assentos, String categoria) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        String queryText = "FROM carro WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND marca = :marca AND assentos = :assentos AND isdisponivel = true";
        try {
            Query query = em.createQuery(queryText);
            query.setParameter(modelo, modelo);
            query.setParameter(cor, cor);
            query.setParameter(ano, ano);
            query.setParameter(marca, marca);
            query.setParameter(assentos, assentos);

            List<Carro> carros = (List<Carro>) query.getResultList();

            return carros;
        } catch (NoResultException e) {
            return null;
        }

    }

    public List executeQuery(String marca, String modelo, String ano, String cor, String categoria) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        String queryText = "FROM motocicleta WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND marca = :marca AND isdisponivel = true";
        try {
            Query query = em.createQuery(queryText);
            query.setParameter(modelo, modelo);
            query.setParameter(cor, cor);
            query.setParameter(ano, ano);
            query.setParameter(marca, marca);

            List<Motocicleta> motos = (List<Motocicleta>) query.getResultList();

            return motos;
        } catch (NoResultException e) {
            return null;
        }
    }
}