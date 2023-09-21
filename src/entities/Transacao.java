package entities;

import entities.Cartao;

import java.nio.charset.StandardCharsets;

public class Transacao {
    private Double valor;
    private String data;
    private String hora;
    private String redeTransmissora;
    private String formaPagamento;
    private String NSU = "000000000000";
    private String codigoResposta;
    private Cartao cartao;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getRedeTransmissora() {
        return redeTransmissora;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("entities.Transacao{");
        sb.append("valor=").append(valor);
        sb.append(", data='").append(data).append('\'');
        sb.append(", hora='").append(hora).append('\'');
        sb.append(", redeTransmissora='").append(redeTransmissora).append('\'');
        sb.append(", formaPagamento='").append(formaPagamento).append('\'');
        sb.append(", NSU='").append(NSU).append('\'');
        sb.append(", codigoResposta='").append(codigoResposta).append('\'');
        sb.append(", cartao=").append(cartao.toString());
        sb.append('}');
        return sb.toString();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setRedeTransmissora(String redeTransmissora) {
        this.redeTransmissora = redeTransmissora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getNSU() {
        return NSU;
    }

    public void setNSU(String NSU) {
        this.NSU = NSU;
    }

    public String getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(String codigoResposta) {
        this.codigoResposta = codigoResposta;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public byte[] response() {
        String text = "0210" + this.formatResponse(Double.toString(this.valor)) + this.hora + this.data + this.redeTransmissora + this.codigoResposta + formatResponse(this.NSU);

        return text.getBytes(StandardCharsets.UTF_8);
    }

    public StringBuffer formatResponse(String valor) {
        var value = new StringBuffer(valor);

        if (value.indexOf(".") != -1)
            value.deleteCharAt(value.indexOf("."));

        for ( ; value.length() < 12; ) {
            value.insert(0, '0');
        }

        return value;
    }
}
