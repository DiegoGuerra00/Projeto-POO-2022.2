package poo.models;

import java.io.IOException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import poo.localdao.LocalDAO;

public class Auth {
    
    public Auth() {
    }

    public Usuario login(String nomeUsuario, String senha) throws IOException {
        if (nomeUsuario == null || senha == null) {
            throw new IOException();
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

    public void cadastro(String nome, String sobrenome, String nomeUsuario, 
    String senha, String cpf, String email, EntityManager em) throws IOException{
        if (nome == null || sobrenome == null || nomeUsuario == null || senha == null 
        || cpf == null || email == null || em == null) {
            throw new IOException();
        }

        Usuario user = new Usuario();
        user.setNome(nome);
        user.setSobrenome(sobrenome);
        user.setNomeUsuario(nomeUsuario);
        user.setSenha(senha);
        user.setCpf(cpf);
        user.setEmail(email);

        LocalDAO dao_cadastro = new LocalDAO();
        dao_cadastro.salvarUsuario(user,em);

    }
}