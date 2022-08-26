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
        
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        //EntityManager em = emf.createEntityManager();
        
        // Usuario test = em.find(Usuario.class, 1);
        // System.out.println(test.toString());

        List<Carro> carros = new ArrayList<Carro>();
    
        Carro car = new Carro();
        car.setAno(1234);
        car.setCor("azul");

        carros.add(car);

        List<Motocicleta> motos = new ArrayList<Motocicleta>();

        Motocicleta moto = new Motocicleta();
        moto.setAno(2005);
        moto.setCor("vermelha");

        motos.add(moto);

        Date inicio = new Date();
        Date fim  = new Date();

        user.efetuarAluguel(carros, motos, user, inicio, fim);
        // List<Aluguel> alugueis =  user.getUserAluguel(1); 
        // System.out.println(alugueis.toString());
        
        // Auth auth = new Auth();
        // auth.login("user_1", "senha");
        // auth.login("user_2", "outra_senha");
    
        em.close();
        emf.close();
    }
}
