package com.model.carros;

import java.util.LinkedList;
import java.util.List;

public class ModelCarro {

	private List<Carro> lstCarro;

	public ModelCarro() {
		lstCarro = new LinkedList<Carro>();
	}

	public void salvar(Carro carro) {
		lstCarro.add(carro);
	}

	public Boolean verifica(Carro carro) {
		for (Carro c : lstCarro) {
			if (carro.getPlaca().equals(c.getPlaca())) {
				return true;
			}
		}
		return false;
	}
	
	public List<Carro> getLstCarro() {
		return lstCarro;
	}
}
