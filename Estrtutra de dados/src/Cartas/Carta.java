package Cartas;

public class Carta {
	
	private int nipe;
	private String valor;
	private Boolean estado;
	
	public Carta(){
		nipe = 1;
		valor = "5";
		setEstado(true);
	}
	
	public void setNipe(int nipe){
		this.nipe = nipe;
	}
	public int getNipe(){
		return nipe;
	}

	public void setValor(String valor){
		this.valor = valor;
	}	
	public String getValor(){
		return this.valor;
	}

	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	
}
