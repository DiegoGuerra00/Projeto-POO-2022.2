package poo.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NoResultException;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Entity
public class Usuario {
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + "]";
    }

    @Id
    @GeneratedValue
    private long id; // talvez remover e manter cpf como PK
    private String nome;
    private String sobrenome;
    private String nomeUsuario;
    private String senha;
    private int cpf;
    private String email;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Aluguel.class)
    private List<Aluguel> alugueis; // TODO talvez desnecessario

    public Usuario() {
    }

    public void efetuarAluguel(List<Carro> listaCarros, List<Motocicleta> listaMotos, Usuario usuario, Date inicio,
            Date fim) {
        Aluguel aluguel = new Aluguel(listaCarros, listaMotos, usuario, inicio, fim);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(aluguel);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna os alugueis correpondentes a um usuário, baseado no seu ID
     * 
     * @param userID ID do usuário a ser consultado
     * @return Lista de Aluguel caso exista no DB, null caso não exista
     */
    public List<Aluguel> getUserAluguel(long userID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            String queryText = "FROM Aluguel WHERE id = :userID";
            Query query = em.createQuery(queryText);
            query.setParameter("userID", userID);

            return (List<Aluguel>) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
        finally {
            em.close();
        }
    }

    public void efetuarAluguel(Carro listaCarros, Motocicleta listaMotos, Usuario usuario, Date inicio, Date fim) {
        Aluguel aluguel = new Aluguel(listaCarros, usuario, inicio, fim);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(aluguel);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public int getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }
}
