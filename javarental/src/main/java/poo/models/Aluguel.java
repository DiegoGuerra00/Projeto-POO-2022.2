package poo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "aluguel_seq", sequenceName = "aluguel_seq", allocationSize = 1, initialValue = 1)
public class Aluguel {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluguel_seq")
   private long id;

   @OneToMany(cascade = CascadeType.ALL, targetEntity = Carro.class)
   @JoinColumn(name = "id")
   private List<Carro> listaCarros;

   @OneToMany(cascade = CascadeType.ALL, targetEntity = Motocicleta.class)
   @JoinColumn(name = "id")
   private List<Motocicleta> listaMotos;

   // @OneToOne(cascade = CascadeType.ALL)
   // private Carro carro;
   //@ManyToOne(cascade = CascadeType.ALL) 
   //@JoinColumn(name = "usuario")
   private Usuario locatario;
   private Date dataLocacao;
   private Date dataDevolucao;

   // public Aluguel(Carro carro, Usuario locatario, Date dataLocacao, Date dataDevolucao) {
   //    this.carro = carro;
   //    this.locatario = locatario;
   //    this.dataLocacao = dataLocacao;
   //    this.dataDevolucao = dataDevolucao;
   // }

   public Aluguel(List<Carro> listaCarros, List<Motocicleta> listaMotos, Usuario locatario, Date dataLocacao,
         Date dataDevolucao) {
      this();
      this.listaCarros = listaCarros;
      this.listaMotos = listaMotos;
      this.locatario = locatario;
      this.dataLocacao = dataLocacao;
      this.dataDevolucao = dataDevolucao;
   }

   public Aluguel() {
   }

   /**
    * Retorna o pedíodo total de um aluguel baseado nas datas de locação
    * e devolução
    * 
    * @param inicio Data da locação, no formato Date
    * @param fim    Data da devolução, no formato Date
    * @return Período total, em dias
    */
   public long periodoAluguel(Date inicio, Date fim) {
      long inicioMS = inicio.getTime();
      long fimMS = fim.getTime();
      long periodoMS = fimMS - inicioMS;

      return TimeUnit.MILLISECONDS.toDays(periodoMS);
   }

   // public double precoFinal() {
   // double precoCarros = 0;
   // double precoMotos = 0;
   // for (Carro carro : listaCarros) {
   // precoCarros += carro.precoDiario;
   // }
   // for (Motocicleta moto : listaMotos) {
   // precoMotos += moto.precoDiario;
   // }

   // return (precoCarros + precoMotos) * periodoAluguel(dataLocacao,
   // dataDevolucao);
   // }

   public void setId(long id) {
      this.id = id;
   }

   // public void setListaCarros(List<Carro> listaCarros) {
   // this.listaCarros = listaCarros;
   // }

   // public void setListaMotos(List<Motocicleta> listaMotos) {
   // this.listaMotos = listaMotos;
   // }

   public void setLocatario(Usuario locatario) {
      this.locatario = locatario;
   }

   public void setDataLocacao(Date dataLocacao) {
      this.dataLocacao = dataLocacao;
   }

   public void setDataDevolucao(Date dataDevolucao) {
      this.dataDevolucao = dataDevolucao;
   }

   // @Override
   // public String toString() {
   //    return "Aluguel [carro=" + carro + ", dataDevolucao=" + dataDevolucao + ", dataLocacao=" + dataLocacao + ", id="
   //          + id + ", locatario=" + locatario + "]";
   // }
}
