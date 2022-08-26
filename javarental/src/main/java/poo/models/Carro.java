package poo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

@Entity
public class Carro extends Veiculo {
    private String tipoCarro;
    private boolean isDuasPortas;

    public Carro() {
    }

    @Override
    public void cadatroVeiculo() {
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
