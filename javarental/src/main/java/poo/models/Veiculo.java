package poo.models;

import javax.swing.ImageIcon;

public abstract class Veiculo {
    protected long id;
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
    // TODO Trocar para java.fx
    protected ImageIcon icon;

    public abstract void cadatroVeiculo();
}