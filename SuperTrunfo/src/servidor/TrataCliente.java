package servidor;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Classe utilizada para tratar o sistema de troca de mensagens entre o servidor e os clientes.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vinícius Gianini Silva 9293007
 *
 */
public class TrataCliente implements Runnable {
	private InputStream jogador;
	private Servidor server;
	
	/**
	 * Método construtor. Inicializa os objetos de servidor e do cliente a ser tratado.
	 * @param server
	 * @param jogador
	 */
	public TrataCliente (Servidor server, InputStream jogador){
		this.server = server;
		this.jogador = jogador;
	}
	
	/**
	 * Método construtor que trata a troca de mensagens do servidor para todos os clientes
	 */
	@Override
	public void run(){
		//distribuindo a mensagem criada
		Scanner s = new Scanner(this.jogador);
		String aux;
		
		while (s.hasNextLine()){
			aux = s.nextLine();
			
			//4 é o número que determina que seja enviada uma mensagem de chat.
			//Isso deve ser programado na interface, para fazer aparecer em uma
			//caixa de mensagens
			if (aux.substring(0, 1).equals("4")){
				System.out.println(aux); //printa no servidor a mensagem criada
				server.distribuirMsg(aux);
			}
			
			else{
				//define a escolha do atributo da carta do jogador 2
				this.server.setEscolha_cliente(Integer.parseInt(aux.substring(0, 1)));
								
				//libera a flag de escolha da carta do jogador 2, no processo do jogo
				this.server.setFlag_escolha(true);
			}
		}
		
		s.close();
	}
}
