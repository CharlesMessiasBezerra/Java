
import java.util.Scanner;

/**
 * @author (Charles Messias Bezerra)
 */
public class TorresDeHanoi {

	static Integer movimentos = 0;

	/**
	 * Metodo - que vai recebe o n�mero de discos digitado pelo usu�rio
	 * 
	 */
	public static void main(String[] args) {

		int n;
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o n�mero de discos: ");
		n = entrada.nextInt();
		TorresDeHanoi.hanoi(n, 1, 3, 2);
		System.out.println("Total Movimentos : " + movimentos);
	}

	/**
	 * M�todo exibi os movimentos
	 * 
	 */
	private static void mover(int Inicio, int Destino) {
		movimentos += 1;
		System.out.println(Inicio + " -> " + Destino);
	}

	/**
	 * M�todo recursivo
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