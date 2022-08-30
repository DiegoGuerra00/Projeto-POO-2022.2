package poo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import poo.localdao.LocalDAO;
import jakarta.persistence.GenerationType;

@Entity
@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1, initialValue = 1)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private long id; // talvez remover e manter cpf como PK

    private String nome;
    private String sobrenome;
    private String nomeUsuario;
    private String senha;
    private String cpf;
    private String email;

    public Usuario() {
    }

    public void efetuarAluguel(Carro carro, Motocicleta moto, Usuario usuario, LocalDate inicio,
    LocalDate fim, EntityManager em) {
        Aluguel aluguel = new Aluguel();
        aluguel.setDataLocacao(inicio);
        aluguel.setDataDevolucao(fim);
        aluguel.setCarro(carro);
        aluguel.setMoto(moto);
        aluguel.setLocatario(usuario);

        long periodo_aluguel = aluguel.periodoAluguel(inicio, fim);

        //System.out.println("início: " + inicio);
        //System.out.println("fim: " + fim);
        //System.out.println("período total (em dias): " + periodo_aluguel);
        //System.out.println("preço final: R$" + aluguel.precoFinal(carro, moto, periodo_aluguel));

        LocalDAO dao = new LocalDAO();
        dao.salvarAluguel(aluguel,em);
    }


    // public void efetuarAluguel(Carro listaCarros, Motocicleta listaMotos, Usuario usuario, Date inicio, Date fim) {
    //     Aluguel aluguel = new Aluguel(listaCarros, usuario, inicio, fim);

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

}
