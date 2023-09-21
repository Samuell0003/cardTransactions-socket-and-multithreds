import entities.Cartao;
import entities.Transacao;
import services.ProcessadorTransacao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Servidor {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(2001);

        Map<String, Cartao> cartoes = new HashMap<String, Cartao>();
        cartoes.put("401231021845", new Cartao("3245432", "SAMUEL LUCAS", 100D));
        cartoes.put("6543455", new Cartao("6543455", "SABRINA GOMES", 10D));
        cartoes.put("34566564", new Cartao("34566564", "DENIZIA PEREIRA", 1500D));
        cartoes.put("098778423", new Cartao("098778423", "JOAO ALMEIDA", 30D));
        ProcessadorTransacao processadorTransacao = new ProcessadorTransacao(cartoes);

        while (true) {
            System.out.println("Esperando conexao...");
            Socket conexao = s.accept();
            System.out.println("CONECTOU!");

            try (DataInputStream entrada = new DataInputStream(conexao.getInputStream())) {
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

                byte[] dados = new byte[45];
                entrada.readFully(dados);
                String linha = new String(dados);

                Transacao trans = processadorTransacao.decodificacao(linha);
                processadorTransacao.checkTransacao(trans);
                saida.write(trans.response());
                System.out.println("/----------/");
                System.out.println("RESPOSTA ENVIADA");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
