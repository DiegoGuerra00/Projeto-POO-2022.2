package poo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "moto_seq", sequenceName = "moto_seq", allocationSize = 1, initialValue = 1)
public class Motocicleta extends Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto_seq")
    private long id; // talvez trocar por placa
    private int cilindradas;
    private String tipoMoto;

    public Motocicleta() {
    }

    @Override
    public void cadatroVeiculo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
