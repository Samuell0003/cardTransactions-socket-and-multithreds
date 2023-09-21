package services;

public class ResponseTransacao {
    private String tipoMensagem;
    private Double valor;
    private String data;
    private String hora;
    private String redeTransmissora;
    private String NSU;
    private String codigoResposta;

    public ResponseTransacao(String info) {
        this.tipoMensagem = info.substring(0, 4);
        this.valor = Double.parseDouble(info.substring(4, 14)+"."+info.substring(14,16));
        this.hora = info.substring(16, 22);
        this.data = info.substring(22, 26);
        this.redeTransmissora = info.substring(26, 32);
        this.codigoResposta = info.substring(32, 34);
        this.NSU = info.substring(34);

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("services.ResponseTransacao{");
        sb.append("tipoResposta='").append(tipoMensagem).append('\'');
        sb.append(", valor=").append(valor);
        sb.append(", data='").append(data).append('\'');
        sb.append(", hora='").append(hora).append('\'');
        sb.append(", redeTransmissora='").append(redeTransmissora).append('\'');
        sb.append(", NSU='").append(NSU).append('\'');
        sb.append(", codigoResposta='").append(codigoResposta).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public String getRedeTransmissora() {
        return redeTransmissora;
    }

    public void setRedeTransmissora(String redeTransmissora) {
        this.redeTransmissora = redeTransmissora;
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
}
