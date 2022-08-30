package poo.localdao;

import org.hibernate.Session;

//import java.util.List;

import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
import poo.models.Aluguel;
import poo.models.Carro;
import poo.models.Motocicleta;
import poo.models.Usuario;

public class LocalDAO {
    public void salvarAluguel(Aluguel aluguel, EntityManager em){
        try{
            em.getTransaction().begin();
            em.unwrap(Session.class).saveOrUpdate(aluguel);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarCarro(Carro carro, EntityManager em){
        try{
            em.getTransaction().begin();
            em.persist(carro);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarMoto(Motocicleta moto, EntityManager em){
        try{
            em.getTransaction().begin();
            em.persist(moto);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarUsuario(Usuario user, EntityManager em){
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}