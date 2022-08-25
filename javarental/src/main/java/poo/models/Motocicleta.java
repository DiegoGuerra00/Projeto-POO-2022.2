package poo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity
public class Motocicleta extends Veiculo {
    private int cilindradas;
    private String tipoMoto;

    @Override
    public void cadatroVeiculo() {
    }
}
