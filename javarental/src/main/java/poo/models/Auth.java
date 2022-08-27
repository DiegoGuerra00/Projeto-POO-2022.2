package poo.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Auth {
    public Auth() {
    }

    // talvez seja necessario retornar o usuario atual no caso de sucesso
    public Usuario login(String nomeUsuario, String senha) {
        if (nomeUsuario == null || senha == null) {
            return null;
            //adicionar exceção
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Usuario user;
        try {
            String queryText = "FROM Usuario WHERE nomeUsuario = :username AND senha = :passwd";
            Query query = em.createQuery(queryText);
            query.setParameter("username", nomeUsuario);
            query.setParameter("passwd", senha);
            user = (Usuario) query.getSingleResult();
            System.out.println(user.toString());
            return user;
        } catch (NoResultException e) {
            System.out.println("No Result found!");
            return null;
        } finally {
            em.close();
        }
    }


    public void logout() {
    }
}
