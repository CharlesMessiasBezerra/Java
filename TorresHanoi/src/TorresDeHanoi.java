
import java.util.Scanner;

/**
 * @author (Charles Messias Bezerra)
 */
public class TorresDeHanoi {

	static Integer movimentos = 0;

	/**
	 * Metodo - que vai recebe o número de discos digitado pelo usuário
	 * 
	 */
	public static void main(String[] args) {

		int n;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o número de discos: ");
		n = entrada.nextInt();
		TorresDeHanoi.hanoi(n, 1, 3, 2);
		System.out.println("Total Movimentos : " + movimentos);
	}

	/**
	 * Método exibi os movimentos
	 * 
	 */
	private static void mover(int Inicio, int Destino) {
		movimentos += 1;
		System.out.println(Inicio + " -> " + Destino);
	}

	/**
	 * Método recursivo
	 * 
	 * @param Inicio
	 *            (int)= pino origem
	 * @param Meio(int)
	 *            = pino auxiliar
	 * @param destino(int)=
	 *            pino objetico
	 * 
	 */
	static void hanoi(int n, int Inicio, int Destino, int Meio) {

		if (n > 0) {
			hanoi(n - 1, Inicio, Meio, Destino);
			mover(Inicio, Destino);
			hanoi(n - 1, Meio, Destino, Inicio);
		}
	}
}