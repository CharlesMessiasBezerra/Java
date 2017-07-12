package ContaBancaria;

public class Conta {

	public Conta(){
		this.saldo = (float) 50.00;
		this.limite = (float) 200.00;
		this.nome =  "Charles Messias";
		this.cheques =true;
	}
	
	private float saldo;
	private float limite;
	private String nome;
	private boolean cheques;
	
	public float getsaldo(){
		return this.saldo;
	}

	public void setsaldo(float saldo){
		this.saldo = saldo;
	}


	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean sacar(float valor)
	{
		boolean result = false;
		
		if(valor >= this.saldo && valor < this.limite)
		{
			this.saldo -= valor;
			result = true;
		}	
		return result;
	}
	
	@Override
	public String toString() {return "Conta [saldo=" + saldo + ", limite=" + limite + ", nome="+ nome + ", cheques=" + cheques + "]";}

	
	
	
	
}
