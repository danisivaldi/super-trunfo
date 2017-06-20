package teste_escolha;

import java.io.IOException;
import java.util.Scanner;

import cliente.Cliente;
import servidor.Servidor;

/**
 * Classe de teste para criacao de servidor e clientes, e envio e recebimento
 * de mensagens.
 * @author Daniel Sivaldi Feres 9912275
 * @author Fernando Vinícius Gianini Silva 9293007
 *
 */
public class teste_main {
	/*public static void main (String[] args){
	new Cliente("", 9669);
	}*/
	
	/*public static void main(String[] args) throws IOException{
	new Servidor(9669).executar();
	}*/
	
	/*
	 * Testando os clientes e os servidores
	 */
	public static void main(String[] args) throws IOException{
		int escolha;
		Scanner s = new Scanner(System.in);
			
		System.out.println("Escolha: 1 - host; 2 - cliente");
		escolha = s.nextInt();
		s.nextLine();
		
		if (escolha == 1){
			new Servidor(12345).executar();
		}
		else if (escolha == 2){
			String nome;
			System.out.println("Informe o seu nome: ");
			nome = s.nextLine();

			new Cliente("0.0.0.0", 12345, nome).executar();
			
		}
		s.close();
	}
}
