package poo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Veiculo {
    @Id
    @GeneratedValue
    protected long id; // talvez trocar por placa
    protected String marca;
    protected String modelo;
    protected int ano;
    protected String categoria;
    protected int assentos;
    protected String cor;
    protected boolean isUsed;
    protected boolean isDisponivel;
    protected int quilometragem;
    protected double precoDiario;
    // protected ImageIcon icon;

    public abstract void cadatroVeiculo();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAssentos() {
        return assentos;
    }

    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isDisponivel() {
        return isDisponivel;
    }

    public void setDisponivel(boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getPrecoDiario() {
        return precoDiario;
    }

    public void setPrecoDiario(double precoDiario) {
        this.precoDiario = precoDiario;
    }
}
