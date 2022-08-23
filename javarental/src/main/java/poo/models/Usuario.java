package poo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Persistence;

@Entity
public class Usuario {
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + "]";
    }

    @Id
    @GeneratedValue
    private long id; //talvez remover e manter cpf como PK
    private String nome;
    private String sobrenome;
    private String nomeUsuario;
    private String senha;
    private int cpf;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @OneToMany
    private List<Aluguel> alugueis;

    public Usuario() {
    }

    public void efetuarAluguel() {

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
