package poo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import poo.models.Auth;
import poo.models.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setEmail("teste@teste.com");
        user.setNomeUsuario("user_3");
        user.setNome("User");
        user.setSenha("senha");
        user.setCpf(123);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Usuario test = em.find(Usuario.class, 1);
        // System.out.println(test.toString());

        // em.getTransaction().begin();
        // em.persist(user);
        // em.getTransaction().commit();

        Auth auth = new Auth();
        // auth.login("user_1", "senha");
        // auth.login("user_2", "outra_senha");
        // auth.criarConta(user);
    
        em.close();
    }
}
