package poo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import poo.models.Aluguel;
import poo.models.Auth;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setEmail("teste@teste.com");
        user.setNomeUsuario("user_3");
        user.setNome("User");
        user.setSenha("senha");
        user.setCpf(123);

        Usuario user1 = new Usuario();
        user1.setEmail("email@email.com");
        user1.setNomeUsuario("user_2");
        user1.setNome("name");
        user1.setSenha("pwd");
        user1.setCpf(123);
        
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        //EntityManager em = emf.createEntityManager();
        
        // Usuario test = em.find(Usuario.class, 1);
        // System.out.println(test.toString());

        List<Carro> carros = new ArrayList<Carro>();
        List<Carro> carros1 = new ArrayList<Carro>();
    
        Carro car = new Carro();
        car.setAno(1234);
        car.setCor("azul");

        Carro car1 = new Carro();
        car1.setAno(9999);
        car1.setCor("preto");

        Carro car2 = new Carro();
        car1.setAno(1111);
        car1.setCor("amarelo");

        Carro car3 = new Carro();
        car1.setAno(2222);
        car1.setCor("verde");

        carros.add(car);
        carros.add(car1);

        carros1.add(car2);
        carros1.add(car3);

        List<Motocicleta> motos = new ArrayList<Motocicleta>();
        List<Motocicleta> motos1 = new ArrayList<Motocicleta>();

        Motocicleta moto = new Motocicleta();
        moto.setAno(2005);
        moto.setCor("vermelha");

        Motocicleta moto1 = new Motocicleta();
        moto1.setAno(9999);
        moto1.setCor("amarela");

        Motocicleta moto2 = new Motocicleta();
        moto1.setAno(7777);
        moto1.setCor("roxa");

        Motocicleta moto3 = new Motocicleta();
        moto1.setAno(0000);
        moto1.setCor("rosa");

        motos.add(moto);
        motos.add(moto1);

        motos1.add(moto2);
        motos1.add(moto3);

        Date inicio = new Date();
        Date inicio1 = new Date();
        Date fim  = new Date();
        Date fim1  = new Date();


        user.efetuarAluguel(carros, motos, user, inicio, fim);
        user.efetuarAluguel(carros, motos, user, inicio, fim);

        // List<Aluguel> alugueis =  user.getUserAluguel(1); 
        // System.out.println(alugueis.toString());
        
        // Auth auth = new Auth();
        // auth.login("user_1", "senha");
        // auth.login("user_2", "outra_senha");
    
        //em.close();
        //emf.close();
    }
}
