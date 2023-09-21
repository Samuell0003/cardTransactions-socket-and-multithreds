package entities;

import java.util.List;

public class Cartao {
    private String numero;
    private String nomeCliente;
    private Double saldo;

    public String getNumero() {
        return numero;
    }

    public synchronized boolean sacar(Double valor) {
        if (valor > this.saldo) {
            return false;
        } else {
            this.saldo -=  valor;
            return true;
        }
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cartao(String numero, String nomeCliente, Double saldo) {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public Cartao() { }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("entities.Cartao{");
        sb.append("numero='").append(numero).append('\'');
        sb.append(", nomeCliente='").append(nomeCliente).append('\'');
        sb.append(", saldo=").append(saldo);
        sb.append('}');
        return sb.toString();
    }



}
