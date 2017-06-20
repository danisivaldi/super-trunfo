package servidor;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Classe utilizada para tratar o sistema de troca de mensagens entre o servidor e os clientes.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vin�cius Gianini Silva 9293007
 *
 */
public class TrataCliente implements Runnable {
	private InputStream jogador;
	private Servidor server;
	
	/**
	 * M�todo construtor. Inicializa os objetos de servidor e do cliente a ser tratado.
	 * @param server
	 * @param jogador
	 */
	public TrataCliente (Servidor server, InputStream jogador){
		this.server = server;
		this.jogador = jogador;
	}
	
	/**
	 * M�todo construtor que trata a troca de mensagens do servidor para todos os clientes
	 */
	@Override
	public void run(){
		//distribuindo a mensagem criada pelo servidor para todos os clientes
		Scanner s = new Scanner(this.jogador);
		String aux;
		
		while (s.hasNextLine()){
			aux = s.nextLine();
			System.out.println("Mensagem recebida: " + aux);
			server.distribuirMsg(aux);
		}
		
		s.close();
	}
}
