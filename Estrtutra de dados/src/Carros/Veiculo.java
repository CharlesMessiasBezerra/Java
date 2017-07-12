package Carros;

public class Veiculo 
{
	//Construtor
	public Veiculo()
	{
		
	}

	public Veiculo(String nome,int passageiros,int tanque, int consumo)
	{
		this.nome = nome;
		this.passageiros = passageiros;
		this.tanque = tanque ;
		this.consumo = consumo;
	}
	
	
	
	// Variaveis Privadas
	private String nome;
	
	private int passageiros;
	
	private int tanque;

	private int consumo;

	
	// Metodos Publicas
	public int getPassageiros() {
		return passageiros;
	}
	
	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
	}
	
	public int getTanque() {
		return tanque;
	}

	public void setTanque(int tanque) {
		this.tanque = tanque;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean checkRange(Veiculo veiculoOutro)
	{
		return this.range(this.tanque) > veiculoOutro.range(veiculoOutro.tanque) ;
	}

	public int range(int tanque) {
		return this.tanque * this.consumo;
	}
}