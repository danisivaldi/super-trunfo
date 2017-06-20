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
	private int escolha_cliente;

	private boolean flag_escolha;
	private List <PrintStream> jogadores;
	private String nome;
	ServerSocket servidor;
	
	/**
	 * Construtor definido para inicializar a porta a ser utilizada para comunicação
	 * entre o servidor e os clientes, bem como sua interface de troca de mensagens
	 * @param porta
	 */
	public Servidor(int porta, String nome){
		this.porta = porta;
		this.jogadores = new ArrayList<PrintStream>();
		this.nome = nome;
		this.flag_escolha = false;
		this.setEscolha_cliente(-1);
	}
	
	/**
	 * método que executa o tratamento de conexão entre o servidor e os clientes
	 * @throws IOException
	 */
	public void executar() throws IOException{
		//abertura do host
		this.servidor = new ServerSocket (this.porta);
		System.out.println("Servidor aberto! IP: " + servidor.getInetAddress().getHostAddress());
		
		int count_clientes = 0;
	
		//obtendo os dois jogadores clientes
		while (count_clientes != 1){
			System.out.println("esperando cliente: ");
			//aceitando o cliente na conexão
			Socket cliente = servidor.accept();
			System.out.println("Jogador conectado! IP: " + cliente.getInetAddress().getHostAddress());
			
			//adicionando a saída do cliente a lista
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.jogadores.add(ps);
			
			//trata cada jogador em uma thread diferente
			TrataCliente msgJogador = new TrataCliente(this, cliente.getInputStream());
			new Thread(msgJogador).start();
			
			//proximo jogador
			count_clientes++;
		}
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
	
	/**
	 * Método que distribui uma mensagem de chat
	 * @param msg
	 */
	public void distribuirMsgChat(String msg){
		this.distribuirMsg(this.nome + msg);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEscolha_cliente() {
		return escolha_cliente;
	}

	/**
	 * 
	 * @param escolha_cliente
	 */
	public void setEscolha_cliente(int escolha_cliente) {
		this.escolha_cliente = escolha_cliente;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFlag_escolha() {
		return flag_escolha;
	}

	/**
	 * 
	 * @param flag_escolha
	 */
	public void setFlag_escolha(boolean flag_escolha) {
		this.flag_escolha = flag_escolha;
	}

	/**
	 * Método que encerra a conexão do servidor
	 * @throws IOException
	 */
	public void fecharConexao() throws IOException{
		System.out.println("Fechando");
		this.servidor.close();
	}
}
