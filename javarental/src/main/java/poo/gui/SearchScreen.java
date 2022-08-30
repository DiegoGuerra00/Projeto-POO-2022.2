package poo.gui;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class SearchScreen {
    private static final double WIDTH = 150;
    private GridPane grid;
    private Scene scene;
    private Button logoutButton;
    private Button searchButton;
    private Usuario user;

    private ChoiceBox<String> modeloCarroBox;
    private ChoiceBox<String> modeloMotoBox;
    private ChoiceBox<String> anoBox;
    private ChoiceBox<String> corBox;
    private ChoiceBox<String> tipoCarroBox; // Usado apenas quando a categoria é carro
    private ChoiceBox<String> tipoMotoBox;// Usado apenas quando é moto
    private ChoiceBox<String> portasBox; // Usado apenas quando a categoria é carro
    private ChoiceBox<String> cilindradasBox; // Usado apenas quando é moto

    private boolean isCarro;
    private EntityManager em;

    public SearchScreen(boolean isCarro, Usuario user, EntityManager em) {
        this.user = user;
        this.isCarro = isCarro;
        this.em = em;
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
        tipoMotoBox = new ChoiceBox<String>(FXCollections.observableArrayList("Scooter", "Mobilete", "Road"));
        tipoMotoBox.setValue("Tipo");
        tipoMotoBox.setPrefWidth(WIDTH);
        grid.add(tipoMotoBox, 1, 1);

        tipoCarroBox = new ChoiceBox<String>(FXCollections.observableArrayList("Sedan", "Wagon", "Hatch"));
        tipoCarroBox.setValue("Tipo");
        tipoCarroBox.setPrefWidth(WIDTH);
        grid.add(tipoCarroBox, 1, 1);

        modeloCarroBox = new ChoiceBox<String>(
                FXCollections.observableArrayList("Honda Civic", "BMW 238i", "Nissan 180SX"));
        modeloCarroBox.setValue("Modelo");
        modeloCarroBox.setPrefWidth(WIDTH);
        grid.add(modeloCarroBox, 2, 1);

        modeloMotoBox = new ChoiceBox<String>(
                FXCollections.observableArrayList("Honda CG 160", "Honda PCX"));
        modeloMotoBox.setValue("Modelo");
        modeloMotoBox.setPrefWidth(WIDTH);
        grid.add(modeloMotoBox, 2, 1);

        anoBox = new ChoiceBox<String>(FXCollections.observableArrayList("1999", "1998", "1995", "2010", "2008"));
        anoBox.setValue("Ano");
        anoBox.setPrefWidth(WIDTH);
        grid.add(anoBox, 4, 1);

        corBox = new ChoiceBox<String>(FXCollections.observableArrayList("Vermelho", "Prata", "Azul"));
        corBox.setValue("Cor");
        corBox.setPrefWidth(WIDTH);
        grid.add(corBox, 1, 2);

        portasBox = new ChoiceBox<String>(FXCollections.observableArrayList("2", "4"));
        portasBox.setValue("Portas");
        portasBox.setPrefWidth(WIDTH);
        grid.add(portasBox, 2, 2);

        cilindradasBox = new ChoiceBox<String>(FXCollections.observableArrayList("100", "150", "160", "200"));
        cilindradasBox.setValue("Cilindradas");
        cilindradasBox.setPrefWidth(WIDTH);
        grid.add(cilindradasBox, 2, 2);

        if (isCarro) {
            portasBox.setVisible(true);
            tipoCarroBox.setVisible(true);
            tipoMotoBox.setVisible(false);
            cilindradasBox.setVisible(false);
            modeloCarroBox.setVisible(true);
            modeloMotoBox.setVisible(false);
        } else {
            portasBox.setVisible(false);
            tipoCarroBox.setVisible(false);
            tipoMotoBox.setVisible(true);
            cilindradasBox.setVisible(true);
            modeloCarroBox.setVisible(false);
            modeloMotoBox.setVisible(true);
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
                Login login = new Login(em);
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
                    boolean portas;
                    if (portasBox.getValue().matches("2")) {
                        portas = true;
                    } else {
                        portas = false;
                    }
                    List<Carro> carros = executeQuery(modeloCarroBox.getValue(), tipoCarroBox.getValue(),
                            Integer.parseInt(anoBox.getValue()), corBox.getValue(), portas);

                    results = new ResultsScreen(carros, new Carro(), user, em);

                } else {
                    List<Motocicleta> motos = executeQuery(modeloCarroBox.getValue(), tipoMotoBox.getValue(),
                            Integer.parseInt(anoBox.getValue()), corBox.getValue(),
                            Integer.parseInt(cilindradasBox.getValue()));
                    results = new ResultsScreen(motos, user, em);
                }
                Window w = scene.getWindow();
                if (w instanceof Stage) {
                    Stage s = (Stage) w;
                    s.setScene(results.getScene());
                }
            }

        });
    }

    public List executeQuery(String modelo, String tipo, int ano, String cor, boolean portas) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        String queryText = "FROM Carro WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND isDisponivel = true AND isDuasPortas = :portas";
        try {
            Query query = em.createQuery(queryText);
            query.setParameter("modelo", modelo);
            query.setParameter("cor", cor);
            query.setParameter("ano", ano);
            query.setParameter("portas", portas);

            return (List<Carro>) query.getResultList();
        } catch (NoResultException e) {
            System.out.println("No Result Found!");
            return null;
        }

    }

    public List executeQuery(String modelo, String tipo, int ano, String cor, int cilindradas) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        String queryText = "FROM motocicleta WHERE modelo = :modelo AND cor = :cor AND ano = :ano AND cilindradas = :cilindradas AND isdisponivel = true";
        try {
            Query query = em.createQuery(queryText);
            query.setParameter("modelo", modelo);
            query.setParameter("cor", cor);
            query.setParameter("ano", ano);
            query.setParameter("cilindradas", cilindradas);

            return (List<Motocicleta>) query.getResultList();

        } catch (NoResultException e) {
            System.out.println("No Result Found!");
            return null;
        }
    }
}
