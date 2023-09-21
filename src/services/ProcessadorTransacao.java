package services;

import entities.Cartao;
import entities.Transacao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ProcessadorTransacao {
    private int nsu;
    private Map<String, Cartao> cartoes;

    public ProcessadorTransacao(Map<String, Cartao> cartoes) {
        this.cartoes = cartoes;
        this.nsu = 0;
    }

    public synchronized int gerarNSU() {
        return ++nsu;
    }

    public Transacao decodificacao(String crip) {
        Transacao transacao = new Transacao();
//        transacao.setCodigoResposta(crip.substring(0, 4));
        transacao.setValor(Double.parseDouble(crip.substring(4, 14)+"."+crip.substring(14,16)));
        transacao.setHora(crip.substring(16, 22));
        transacao.setData(crip.substring(22, 26));
        transacao.setRedeTransmissora(crip.substring(26, 32));
        Cartao cartao = new Cartao();
        cartao.setNumero(crip.substring(32, 44));
        transacao.setCartao(cartao);
        transacao.setFormaPagamento(crip.substring(44));

        return transacao;
    }

    public void checkTransacao(Transacao transacao) {
        var dateTime = new Date();
        var cartaoCheck = cartoes.get(transacao.getCartao().getNumero());

        if (cartaoCheck == null) {
            transacao.setCodigoResposta("05");
        } else if (!cartaoCheck.sacar(transacao.getValor())) {
            transacao.setCodigoResposta("51");
        } else {
            transacao.setCodigoResposta("00");
            transacao.setNSU(Integer.toString(gerarNSU()));
        }

        transacao.setData(new SimpleDateFormat("ddMM").format(dateTime));
        transacao.setHora(new SimpleDateFormat("hhmmss").format(dateTime));
    }
}
