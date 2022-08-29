package poo.gui;

import javafx.beans.value.ObservableValue;

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
    private ChoiceBox<String> portasBox; // Usado apenas quando a categoria é carro

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

    // TODO remover
    private void getValues() {
        System.out.println(categoriaBox.getValue());
        System.out.println(marcaBox.getValue());
    }

    private void setChoiceBoxes() {
        String[] categorias = new String[] { "Carro", "Motocicleta" };
        categoriaBox = new ChoiceBox<String>(FXCollections.observableArrayList("Carro", "Motocicleta"));
        categoriaBox.setValue("Categoria");
        categoriaBox.setPrefWidth(WIDTH);

        categoriaBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue) {
                categoria = categorias[newValue.intValue()];
                System.out.println(categoria);
            }
        });

        grid.add(categoriaBox, 1, 1);

        marcaBox = new ChoiceBox<String>(FXCollections.observableArrayList("Honda", "BMW", "Nissan"));
        marcaBox.setValue("Marca");
        marcaBox.setPrefWidth(WIDTH);
        grid.add(marcaBox, 2, 1);

        // FIXME vai dar problema com a selecao da marca, muito trabalho mapear marca e
        // modelo juntos
        modeloBox = new ChoiceBox<String>(FXCollections.observableArrayList("Civic", "328i Touring", "180SX"));
        modeloBox.setValue("Modelo");
        modeloBox.setPrefWidth(WIDTH);
        grid.add(modeloBox, 3, 1);

        anoBox = new ChoiceBox<String>(FXCollections.observableArrayList("1999", "1998", "1995"));
        anoBox.setValue("Ano");
        anoBox.setPrefWidth(WIDTH);
        grid.add(anoBox, 4, 1);

        corBox = new ChoiceBox<String>(FXCollections.observableArrayList("Vermelho", "Prata", "Azul"));
        corBox.setValue("Cor");
        corBox.setPrefWidth(WIDTH);
        grid.add(corBox, 1, 2);

        assentosBox = new ChoiceBox<String>(FXCollections.observableArrayList("4", "5"));
        assentosBox.setValue("Assentos"); // TODO caso categoria = moto deixar fixo em 2
        assentosBox.setPrefWidth(WIDTH);
        grid.add(assentosBox, 2, 2);

        portasBox = new ChoiceBox<String>(FXCollections.observableArrayList("2", "4"));
        portasBox.setValue("Portas"); // TODO Só deve ser visivel quando categoria foi carro
        portasBox.setPrefWidth(WIDTH);

        if (isCarro) {
            portasBox.setVisible(true);
        } else {
            portasBox.setVisible(false);
        }

        grid.add(portasBox, 3, 2);
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
                getValues();
            }

        });
    }

    public void executeQuery(String marca, String modelo, String ano, String cor, String assentos, String categoria) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        if (isCarro) {
            String queryText = "FROM carro WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND marca = :marca AND assentos = :assentos AND isdisponivel = true";
            try {
                Query query = em.createQuery(queryText);
                query.setParameter(modelo, modelo);
                query.setParameter(cor, cor);
                query.setParameter(ano, ano);
                query.setParameter(marca, marca);
                query.setParameter(assentos, assentos);

                List<Carro> carros = (List<Carro>) query.getResultList();
            } catch (NoResultException e) {

            }
        } else {
            String queryText = "FROM motocicleta WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND marca = :marca AND assentos = :assentos AND isdisponivel = true";
            try {
                Query query = em.createQuery(queryText);
                query.setParameter(modelo, modelo);
                query.setParameter(cor, cor);
                query.setParameter(ano, ano);
                query.setParameter(marca, marca);
                query.setParameter(assentos, assentos);
            } catch (NoResultException e) {

            }
        }

    }
}
