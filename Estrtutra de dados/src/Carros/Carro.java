package Carros;

public class Carro 
{

	public static void main(String[] args) 
	{
		Veiculo carro1 = new Veiculo();
		Veiculo carro2 = new Veiculo();
		Veiculo carro3 = new Veiculo("hb20",5,60,10);

		int range;

		carro1.setNome("Van");
		carro1.setPassageiros(4);
		carro1.setTanque(50);
		carro1.setConsumo(12);
		
		carro2.setNome("Moto");
		carro2.setPassageiros(2);
		carro2.setTanque(12);
		carro2.setConsumo(30);

		range = carro1.getTanque() * carro1.getConsumo();
		System.out.println("Eu levo " + carro1.getPassageiros()	+ " passageiros, pois sou uma " + carro1.getNome());
		System.out.println("Eu ando " + range + "Km");

		
		System.out.println("Range do "+ carro1.checkRange(carro2));
		System.out.println("carro3 = " + carro3.getNome());
	}
		
}


