package poo.models;

import java.time.Duration;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "aluguel_seq", sequenceName = "aluguel_seq", allocationSize = 1, initialValue = 1)
public class Aluguel {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluguel_seq")
   public long id;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "carro_aluguel_id")
   private Carro carro;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "moto_aluguel_id")
   private Motocicleta moto;

   @ManyToOne(cascade = CascadeType.ALL) 
   @JoinColumn(name = "usuario_id")
   private Usuario locatario;
   private LocalDate dataLocacao;
   private LocalDate dataDevolucao;

   public Aluguel(Carro carro, Motocicleta moto, Usuario locatario, LocalDate dataLocacao,
   LocalDate dataDevolucao) {
      this();
      this.carro = carro;
      this.moto = moto;
      this.locatario = locatario;
      this.dataLocacao = dataLocacao;
      this.dataDevolucao = dataDevolucao;
   }

   public Aluguel() {
   }

   /**
    * Retorna o período total de um aluguel baseado nas datas de locação
    * e devolução
    * 
    * @param inicio Data da locação, no formato Date
    * @param fim    Data da devolução, no formato Date
    * @return Período total, em dias
    */
   public long periodoAluguel(LocalDate inicio, LocalDate fim) {
      return (Duration.between(inicio.atStartOfDay(), fim.atStartOfDay()).toDays());
   }

   public String precoFinal(Carro carro, Motocicleta moto, long periodo) {
      double precoCarro = 0;
      double precoMoto = 0;

      if (carro != null) {
         precoCarro = carro.precoDiario;
      }
      else {
         precoMoto = moto.precoDiario;
      }

      return (String.format("%.2f", (double)((precoCarro + precoMoto) * periodo)));
    }

   public void setId(long id) {
      this.id = id;
   }

   public long getId() {
      return id;
  }

   public void setCarro(Carro carro) {
      this.carro = carro;
   }

   public Carro getCarro() {
      return carro;
   }

   public void setMoto(Motocicleta moto) {
      this.moto = moto;
   }

   public Motocicleta getMoto() {
      return moto;
   }

   public void setLocatario(Usuario locatario) {
      this.locatario = locatario;
   }

   public Usuario getLocatario() {
      return locatario;
  }

   public void setDataLocacao(LocalDate dataLocacao) {
      this.dataLocacao = dataLocacao;
   }

   public LocalDate getDataLocacao() {
      return dataLocacao;
   }

   public void setDataDevolucao(LocalDate dataDevolucao) {
      this.dataDevolucao = dataDevolucao;
   }

   public LocalDate getDataDevolucao() {
      return dataDevolucao;
   }
}
