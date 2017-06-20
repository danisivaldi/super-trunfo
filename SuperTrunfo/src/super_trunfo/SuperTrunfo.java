package super_trunfo;

import java.io.IOException;

/**
 * classe que contém apenas o método main, para rodar o jogo.
 * 
 * @author Daniel Sivaldi Feres 9912275
 */

public class SuperTrunfo {

	/**
	 * método main do jogo.
	 * @throws IOException
	 */
	public void jogar() throws IOException {
		Baralho baralho = new Baralho();
		Monte monte = new Monte();
		Mao player1, maquina;
		Carta c1, c2;
		int score1, score2 , turn, attribute = 0;
		
		
		baralho.shuffle(); // embaralha o baralho, que inicialmente est· ordenado
		player1 = new Mao(baralho, 1, 15); // player 1 recebe as 16 primeiras cartas do baralho
		maquina = new Mao(baralho, 16, 31); // maquina recebe as 16 ultimas cartas do baralho
		
		score1 = 16; 
		score2 = 16; // variaveis pra marcar quantas cartas o jogador tem (inicialmente com 16)
		
		turn = 1; // variavel pra saber de quem È o turno; player 1 comeÁa a jogar
		
		while (score1 != 0 && score2 != 0) { // roda o jogo atÈ acabarem as cartas de um dos dois jogadores
			c1 = player1.poll(); 
			c2 = maquina.poll(); // cartas no topo da m„o do jogador
			
			monte.add(c1, c2); // j· coloca as cartas no monte, caso dÍ empate
			
			if (turn == 1) { // rodada do player 1
				
				if (c1.getTrunfo()) { // player 1 tem o Super Trunfo
					System.out.println("player 1 tem o Super Trunfo..");
					if (c2.getId() == 'a') {// player 2 tem uma carta 'A', ent„o ganhou do Super Trunfo
						System.out.println("... mas a carta da Máquina é " + c2.getNum() + "! A Máquina ganhou a rodada!");
						turn = 2;
					} else System.out.println("... e ganhou a rodada automaticamente!");
				} 
				
				else {
						System.out.println("player 1, escolha um atributo:");
						System.out.println("0 - altura " + c1.getAttribute(0) + " m");
						System.out.println("1 - comprimento " + c1.getAttribute(1) + " m");
						System.out.println("2 - peso " + c1.getAttribute(2) + " kg");
						System.out.println("3 - viveu há " + c1.getAttribute(3) + " milhões de anos");
						attribute = EntradaTeclado.leInt(); // escolha do atributo 
					
						System.out.println("player 1: " + c1.getAttribute(attribute));
						System.out.println("Máquina: " + c2.getAttribute(attribute)); // mostra o atributo escolhido das duas cartas
						System.out.println("");
						
					
						if (c1.getAttribute(attribute) > c2.getAttribute(attribute)) {
						System.out.println("player 1 venceu a rodada!");
						player1.add(monte.getMonte()); // player 1 recebe as cartas (que ja estavam no monte)
						monte.clear();
					} else if (c1.getAttribute(attribute) < c2.getAttribute(attribute)) { 
						System.out.println("Máquina venceu a rodada!");
						System.out.println("");
						maquina.add(monte.getMonte()); // player 2 recebe as cartas (que ja estavam no monte)
						monte.clear();
						turn = 2;
					} else {
						System.out.println("empatou! as cartas vão para o monte!");
						// cartas j· est„o no monte
					}
				}
			}
			
			if (turn == 2){ // rodada da m·quina
				
				if (c2.getTrunfo()) { // maquina tem o Super Trunfo
					System.out.println("Máquina tem o Super Trunfo..");
					if (c1.getId() == 'a')  { // player 1 tem uma carta 'A', ent„o ganhou do Super Trunfo
						System.out.println("... mas a carta do player 1 é " + c1.getNum() + "! player 1 ganhou a rodada!");
						turn = 1;
					} else System.out.println("... e ganhou a rodada automaticamente!");
				}
				
				
				
				
				else{
					System.out.println("Carta da Máquina:");
					System.out.println("0 - altura " + c2.getAttribute(0) + " m");
					System.out.println("1 - comprimento " + c2.getAttribute(1) + " m");
					System.out.println("2 - peso " + c2.getAttribute(2) + " kg");
					System.out.println("3 - viveu há " + c2.getAttribute(3) + " milhões de anos");
					//attribute = EntradaTeclado.leInt(); // escolha do atributo
					
					/*if (c2.getAttribute(0)> 4) attribute =0;
					if (c2.getAttribute(1)> 8.5) attribute =1;
					if (c2.getAttribute(2)>7025.5) attribute=2;
					if (c2.getAttribute(3)>155.5) attribute=3;*/
					
					//VericaÁ„o de qual o melhor atributo a ser escolhido, a partir das medias de cada atributo È feita a verifica e a escolha do atributo para a m·quina
					if ((c2.getAttribute(0)>4) && (c2.getAttribute(1)>8.5) && (c2.getAttribute(2)>7025.5) && (c2.getAttribute(3)>155.5) ) attribute = 2;
					if ((c2.getAttribute(0)>4) && (c2.getAttribute(1)>8.5) && (c2.getAttribute(2)>7025.5)&& (c2.getAttribute(3)<155.5) ) attribute = 2;	
					
					
					if ((c2.getAttribute(0)>4) && (c2.getAttribute(1)>8.5) && (c2.getAttribute(2)<7025.5) && (c2.getAttribute(3)>155.5) ) attribute = 0;
					if ((c2.getAttribute(0)>4) && (c2.getAttribute(1)>8.5)&& (c2.getAttribute(2)<7025.5) && (c2.getAttribute(3)<155.5) ) attribute = 0;
					
					
					if ((c2.getAttribute(0)<4) && (c2.getAttribute(1)>8.5)&& (c2.getAttribute(2)<7025.5) && (c2.getAttribute(3)>155.5) ) attribute = 1;
					
					if ((c2.getAttribute(0)<4) && (c2.getAttribute(1)<8.5)&& (c2.getAttribute(2)<7025.5) && (c2.getAttribute(3)>80) ) attribute = 3;
					
		
					
					System.out.println("\n Atributo escolhido pela Máquina : " +c2.getAttribute(attribute));
					System.out.println("");
					
					System.out.println("player 1: " + c1.getAttribute(attribute));
					System.out.println("Máquina: " + c2.getAttribute(attribute)); // mostra o atributo escolhido das duas cartas
					System.out.println("");
					
					if (c1.getAttribute(attribute) > c2.getAttribute(attribute)) {
						System.out.println("player 1 venceu a rodada!");
						System.out.println("");
						player1.add(monte.getMonte()); // player 1 recebe as cartas (que ja estavam no monte)
						monte.clear();
						turn = 1;
					} else if (c1.getAttribute(attribute) < c2.getAttribute(attribute)) { 
						System.out.println("Máquina venceu a rodada!");
						System.out.println("");
						maquina.add(monte.getMonte()); // player 2 recebe as cartas (que ja estavam no monte)
						monte.clear();
					} else {
						System.out.println("empatou! as cartas vão para o monte!");
						// cartas j· est„o no monte
					}
					
				}
				
			}
			
			score1 = player1.size(); 
			score2 = maquina.size(); // atualiza o n˙mero de cartas na m„o de cada jogador
			System.out.println("placar:" + score1 + "x" + score2);
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("");
			
			
		
		
		}
		
		if (score2 == 0) System.out.println("player 1 é o campeão! parabéns!");
		else System.out.println("A Máquina venceu!");
		
	}
}
