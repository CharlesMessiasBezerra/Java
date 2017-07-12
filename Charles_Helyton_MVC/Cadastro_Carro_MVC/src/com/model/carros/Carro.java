package com.model.carros;

public class Carro {

	private String fabricante;
	private String modelo;
	private String cor;
	private String placa;
	private String ano;

	public Carro(String fabricante, String modelo, String cor, String placa, String ano) {
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.cor = cor;
		this.placa = placa;
		this.ano = ano;
	}

	public String getFabricante() {
		return fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public String getCor() {
		return cor;
	}

	public String getPlaca() {
		return placa;
	}

	public String getAno() {
		return ano;
	}
}
