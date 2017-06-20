package principal;

import java.io.IOException;
import java.util.Scanner;

import cliente.*;
import servidor.*;
import super_trunfo.*;



public class Main {

	/*public static void main (String[] args){
	new Cliente("", 9669);
	}*/
	
	/*public static void main(String[] args) throws IOException{
	new Servidor(9669).executar();
	}*/
	
	/*
	 * Testando os clientes e os servidores
	 */
	public static void main(String[] args) throws IOException, InterruptedException{
		int escolha, op_jogo;
		String nome;
		Scanner s = new Scanner(System.in);
		
		System.out.println("Bem Vindo ao Super Trunfo!\n");

		//nome do jogador
		System.out.println("Informe seu nome:");
		nome = s.nextLine();
		
		//opçao de jogo: single player ou multiplayer
		System.out.println("\nEscolha a opção de jogo:" +
							"\n1 - single player;" +
							"\n2- multiplayer"+
							"\nqualquer outra tecla - sair");
		op_jogo = s.nextInt();
		
		//single
		if (op_jogo == 1){
			SuperTrunfo supert = new SuperTrunfo();
			supert.jogar();
		}
		
		//multi
		else if (op_jogo == 2){
			//para o multiplayer, tem que escolher se o jogador criará a sala
			//ou se entrará em uma		
			System.out.println("Escolha: 1 - criar sala; 2 - entrar em uma sala");
			escolha = s.nextInt();
			s.nextLine();
			
			//se criar a sala, o servidor deve controlar o jogo
			if (escolha == 1){
				Servidor host = new Servidor(8080, nome);
				host.executar();
				
				//jogo
				SuperTrunfoOnline st = new SuperTrunfoOnline(host);
				st.jogar();
			}
			//se entrar em uma sala, o cliente apenas recebe e envia as informações
			//das jogadas, mas quem processa o jogo é o servidor
			else if (escolha == 2){

				new Cliente("0.0.0.0", 8080, nome).executar();
				
			}
			
			s.close();
		}
		
	}
}
