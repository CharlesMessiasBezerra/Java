package ListaExercicio_01;

public class ex_01 {

	public static void main(String[] args) {
		
		int i;
		int j;
		
		for (i = 0; i < 2;i++)
		{
			for (j = 2; j >0;j--)
			{
				if(i == j)	break;
				System.out.println("valor de i = "+i +" , Valor de j = " + j);
								
			}
		}
		
	}

}
