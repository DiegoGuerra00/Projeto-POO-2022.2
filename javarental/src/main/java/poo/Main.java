package poo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import poo.models.Endereco;
import poo.models.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        user.setEmail("meuemail@teste.com");
        user.setNomeUsuario("username");

        Endereco endereco = new Endereco();
        endereco.setRua("rua alegre");
        endereco.setNumero(123);

        user.setEndereco(endereco);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
