package poo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity
public class Carro extends Veiculo {
    private String tipoCarro;
    private boolean isDuasPortas;

    @Override
    public void cadatroVeiculo() {
    }

}
