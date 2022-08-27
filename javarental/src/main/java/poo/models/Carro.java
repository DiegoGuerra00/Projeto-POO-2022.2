package poo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "carro_seq", sequenceName = "carro_seq", allocationSize = 1, initialValue = 1)
public class Carro extends Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
    private long id;
    
    private String tipoCarro;
    private boolean isDuasPortas;

    public Carro() {
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

    public String getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public boolean isDuasPortas() {
        return isDuasPortas;
    }

    public void setDuasPortas(boolean isDuasPortas) {
        this.isDuasPortas = isDuasPortas;
    }

}
