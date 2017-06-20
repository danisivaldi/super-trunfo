package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * A classe Cliente trata a conexão entre o cliente e o servidor a ser conectado,
 * assim como o sistema de troca de mensagens que cabe ao jogador.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vinícius Gianini Silva 9293007
 *
 */
public class Cliente{
	private String host_ip;
	private int host_porta;
	private String nome;
	
	/**
	 * Método construtor. Inicializa o número de ip e porta do servidor,
	 * e o nome do Jogador.
	 * @param host_ip
	 * @param host_porta
	 * @param nome
	 */
	public Cliente(String host_ip, int host_porta, String nome){
		this.host_ip = host_ip;
		this.host_porta = host_porta;
		this.nome = nome;
	}
	
	/**
	 * Método que executa o tratamento de conexão entre cliente e servidor,
	 * bem como o sistema de troca de mensagens.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void executar() throws UnknownHostException, IOException{
		String aux_msg;
		
		//conectando-se com o servidor:
		System.out.println("host e porta: " + this.host_ip + " " + this.host_porta);
		Socket jogador = new Socket(this.host_ip, this.host_porta);
		System.out.println("Conectado com o servidor!");
		
		//thread de mensagens recebidas do servidor
		Recebedor r = new Recebedor(jogador.getInputStream());
		new Thread(r).start();
		
		//lendo mensagens e enviando para o servidor
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(jogador.getOutputStream());
		while (teclado.hasNextLine()){
			aux_msg = teclado.nextLine();

			//verificando se é uma mensagem de chat
			if (aux_msg.substring(0, 1).equals("4")) 
				saida.println(aux_msg.substring(0, 1) + nome + aux_msg.substring(2));
			
			//escolha de atributo
			else saida.println(aux_msg);
		}
		
		//fechamento das conexoes e das streams
		saida.close();
		teclado.close();
		jogador.close();
	}

	
	
}
