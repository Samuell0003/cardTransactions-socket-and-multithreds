import services.ResponseTransacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket conexao = new Socket("127.0.0.1", 2001);
        DataInputStream entrada = new DataInputStream(conexao.getInputStream());
        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

        byte[] m_msgBytes_0200 = {
                '0', '2', '0', '0', //tipo da mensagem - 200 é uma mensagem de requisição de transação financeira
                '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '5', // bit 4, valor em centavos - neste
                '1', '0', '4', '4', '4', '6', // bit 12, hora local da transacao - 10:44:46
                '0', '5', '1', '2', // bit 13, data da transacao 05/12
                '0', '4', '0', '1', '0', '4', // bit 33, rede transmissora
                '4', '0', '1', '2', '3', '1', '0', '2', '1', '8', '4', '5', // bit 62, número do cartão 4012-3102-1845
                '1' // bit 62, forma de pagamento 1 - débito, 2 - crédito
        };
        saida.write(m_msgBytes_0200);
        byte[] data = new byte[46];
        entrada.readFully(data);
        String linha = new String(data);
        ResponseTransacao responseTransacao = new ResponseTransacao(linha);
        System.out.println(responseClient(responseTransacao.getCodigoResposta()));
        conexao.close();
    }

    public static String responseClient(String cod) {
        if ( cod.equals("51")) {
            return "Saldo indisponível";
        } else if  ( cod.equals("05") ) {
            return "Cartão inexistente";
        } else if (cod.equals("00")) {
            return "Transação executada";
        }

        return null;
    }
}

