package cliente;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Classe criada para receber as mensagens do servidor, afim de trat�-las.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vin�cius Gianini Silva 9293007
 *
 */
public class Recebedor implements Runnable {

	private InputStream server;
	
	/**
	 * M�todo construtor. Inicializa o objeto de recebimento de mensagens do servidor.
	 * @param server
	 */
	public Recebedor(InputStream server){
		this.server = server;
	}
	
	/**
	 * M�todo que recebe a mensagem do servidor, tratando-a conforme o necessario	
	 */
	@Override
	public void run() {
		Scanner s = new Scanner(this.server);
		
		while (s.hasNextLine()){
			System.out.println(s.nextLine());
		}
	}

}
