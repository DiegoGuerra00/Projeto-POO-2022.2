package poo.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Aluguel {
   @Id
   @GeneratedValue
   private long id;
   @OneToOne //TODO check this relation
   private List<Veiculo> listaVeiculos;
   @OneToOne
   private Usuario locatario;
   private double precoTotal;
   // TODO change date or not?
   private Date dataLocacao;
   private Date dataDevolucao;

   public Aluguel() {
   }

   public int periodoAluguel(Date inicio, Date fim) {
      return 0;
   }

   public double precoFinal(double precoVeiculo, int periodo) {
      return precoVeiculo * periodo;
   }
}
