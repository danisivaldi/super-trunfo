package super_trunfo;

/**
 * classe que representa uma carta do baralho do super trunfo.
 * contém:
 * - caracter identificador da carta: 'a', 'b', 'c', ou 'd'.
 * - número identificador da carta de 1 a 8.
 * - booleano para indicar se a carta é ou não o Super Trunfo.
 * - 4 atributos:
 * 		- altura (m)
 * 		- comprimento (m)
 * 		- peso (kg)
 * 		- viveu há (milhões de anos)
 * 
 * 
 * @author Daniel Sivaldi Feres 9912275
 */

public class Carta {
	private char id;
	private char num;
	private boolean isTrunfo;
	private double[] attributes;
	
	/**
	 * construtor da carta.
	 * @param id - char 'a', 'b', 'c', ou 'd'.
	 * @param num - char de 1 a 8.
	 * @param attributes - array com os 4 atributos da carta na ordem:
	 * [0]: altura
	 * [1]: comprimeto
	 * [2]: peso
	 * [3]: viveu há
	 */
	public Carta(char id, char num, double[] attributes) {
		this.id = id;
		this.num = num;
		this.isTrunfo = false;
		this.attributes = new double[4];
		for (int i = 0; i < 4; i++) {
			this.attributes[i] = attributes[i];
		}
	}
	/**
	 * setter usado apenas para a carta Super Trunfo, para identificá-la.
	 */
	public void setTrunfo() {
		this.isTrunfo = true;
	}
	
	/**
	 * getter para saber se a carta é ou não o Super Trunfo.
	 * @return 'true' se for o Super Trunfo, ou 'false' caso contrário.
	 */
	public boolean getTrunfo() {
		return this.isTrunfo;
	}
	
	/**
	 * getter de um determinado atributo da carta.
	 * @param i - identifica o atributo: 0, 1, 2 ou 3.
	 * @return o valor do atributo requisitado.
	 */
	public double getAttribute(int i) {
		return this.attributes[i];
	}
	
	/**
	 * getter para saber se o id é 'a' (nesse caso essa carta ganha do Super Trunfo).
	 * @return o id da carta.
	 */
	public char getId() {
		return this.id;
	}
	
	/**
	 * @return o número da carta.
	 */
	public char getNum() {
		return this.num;
	}
}
