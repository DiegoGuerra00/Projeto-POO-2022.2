package poo;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poo.gui.Login;
import poo.localdao.LocalDAO;
import poo.models.Auth;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class Main extends Application {
    public static void main(String[] args) throws ParseException {
        // Criação dos mocks de usuario (sem persistência)

        Usuario mock2 = new Usuario();
        mock2.setEmail("mock2@teste.com");
        mock2.setNomeUsuario("mock_2");
        mock2.setNome("mock2");
        mock2.setSenha("teste");
        mock2.setCpf("11111111111");

        Usuario user = new Usuario();
        user.setEmail("teste@teste.com");
        user.setNomeUsuario("user_3");
        user.setNome("User");
        user.setSenha("senha");
        user.setCpf("123");

        Usuario user1 = new Usuario();
        user1.setEmail("email@email.com");
        user1.setNomeUsuario("user_2");
        user1.setNome("name");
        user1.setSenha("pwd");
        user1.setCpf("321");

        // Criação do EMF passado como param
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        // EntityManager em = emf.createEntityManager();

        // Criação dos mocks de carro (sem persistência)

        Carro car2 = new Carro();
        car2.setAno(1111);
        car2.setCor("amarelo");
        car2.setPrecoDiario(450.30);

        Carro car3 = new Carro();
        car3.setAno(2222);
        car3.setCor("verde");
        car3.setPrecoDiario(222.34);

        // Criação dos mocks de moto (sem persistência)
        Motocicleta moto = new Motocicleta();
        moto.setAno(2005);
        moto.setCor("vermelha");
        moto.setPrecoDiario(130.40);

        Motocicleta moto1 = new Motocicleta();
        moto1.setAno(9999);
        moto1.setCor("amarela");
        moto1.setPrecoDiario(250.34);

        Motocicleta moto2 = new Motocicleta();
        moto2.setAno(7777);
        moto2.setCor("roxa");
        moto2.setPrecoDiario(390.82);

        Motocicleta moto3 = new Motocicleta();
        moto3.setAno(0000);
        moto3.setCor("rosa");
        moto3.setPrecoDiario(184.32);

        // em.getTransaction().begin();
        // em.persist(car1);
        // em.persist(car);
        // em.persist(mock1);
        // em.getTransaction().commit();

        // Auth auth = new Auth();
        // auth.login("user_1", "senha");
        // auth.login("user_2", "outra_senha");

        launch(args);

        // em.close();
        // emf.close();
    }

    private void populateDB(EntityManager em) {
        Carro car = new Carro();
        car.setModelo("Honda Civic");
        car.setDisponivel(true);
        car.setAno(2010);
        car.setCor("Azul");
        car.setPrecoDiario(300.45);
        car.setDuasPortas(true);
        car.setTipoCarro("Hatch");

        Carro car1 = new Carro();
        car1.setModelo("Honda Civic");
        car1.setDisponivel(true);
        car1.setAno(2005);
        car1.setCor("Vermelha");
        car1.setPrecoDiario(150.45);
        car1.setDuasPortas(false);
        car1.setTipoCarro("Sedan");

        Carro car2 = new Carro();
        car2.setModelo("BMW 328i");
        car2.setDisponivel(true);
        car2.setAno(2005);
        car2.setCor("Preta");
        car2.setPrecoDiario(490.45);
        car2.setDuasPortas(false);
        car2.setTipoCarro("Wagon");

        Motocicleta moto = new Motocicleta();
        moto.setModelo("Honda CG 160");
        moto.setAno(2019);
        moto.setCor("vermelha");
        moto.setPrecoDiario(130.40);
        moto.setCilindradas(160);
        moto.setTipoMoto("Road");

        Motocicleta moto1 = new Motocicleta();
        moto1.setModelo("Honda PCX");
        moto1.setAno(2020);
        moto1.setCor("amarela");
        moto1.setPrecoDiario(250.34);
        moto1.setCilindradas(150);
        moto1.setTipoMoto("Scooter");

        Usuario mock1 = new Usuario();
        mock1.setEmail("mock1@teste.com");
        mock1.setNomeUsuario("mock_1");
        mock1.setNome("mock1");
        mock1.setSenha("teste");
        mock1.setCpf("00000000000");

        LocalDAO dao = new LocalDAO();
        dao.salvarCarro(car, em);
        dao.salvarCarro(car1, em);
        dao.salvarMoto(moto, em);
        dao.salvarMoto(moto1, em);
        dao.salvarUsuario(mock1, em);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Login login = new Login(em);
        Scene scene = login.getScene();

        // populateDB(em);

        primaryStage.setTitle("JavaRental");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}