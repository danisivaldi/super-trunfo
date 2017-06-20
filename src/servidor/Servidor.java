package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Servidor serve para tratar a criação de um host do jogo de Super Trunfo,
 * conexões com os clientes e recebimento e envio de mensagens para os jogadores.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vinícius Gianini Silva 9293007
 *
 */
public class Servidor {
	private int porta;
	private List <PrintStream> jogadores;
	
	/**
	 * Construtor definido para inicializar a porta a ser utilizada para comunicação
	 * entre o servidor e os clientes, bem como sua interface de troca de mensagens
	 * @param porta
	 */
	public Servidor(int porta){
		this.porta = porta;
		this.jogadores = new ArrayList<PrintStream>();
	}
	
	/**
	 * método que executa o tratamento de conexão entre o servidor e os clientes
	 * @throws IOException
	 */
	public void executar() throws IOException{
		//abertura do host
		ServerSocket servidor = new ServerSocket (this.porta);
		System.out.println("Servidor aberto! IP: " + servidor.getInetAddress().getHostAddress());
		
		int count_clientes = 0;
	
		//obtendo os dois jogadores clientes
		while (count_clientes != 2){
			//aceitando o cliente na conexão
			Socket cliente = servidor.accept();
			System.out.println("Cliente conectado! IP: " + cliente.getInetAddress().getHostAddress());
			
			
			//adicionando a saída do cliente a lista
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.jogadores.add(ps);
			
			//trata cada jogador em uma thread diferente
			TrataCliente msgJogador = new TrataCliente(this, cliente.getInputStream());
			new Thread(msgJogador).start();
			
			//proximo jogador
			count_clientes++;
		}
		
		servidor.close();
	}
	
	/**
	 * Método que obtém uma mensagem do servidor e o envia para todos os clientes.
	 * @param msg
	 */
	public void distribuirMsg(String msg){
		//enviando a mensagem para os jogadores
		for (PrintStream proximo: this.jogadores){
			proximo.println(msg);
		}
	}
	
	/*public static void main(String[] args) throws IOException{
		new Servidor(9669).executar();
	}*/
}
