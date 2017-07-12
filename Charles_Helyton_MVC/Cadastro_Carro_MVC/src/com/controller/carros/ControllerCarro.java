package com.controller.carros;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.model.carros.Carro;
import com.model.carros.ModelCarro;

public class ControllerCarro {
	ModelCarro modelCarro;

	public ControllerCarro() {
		modelCarro = new ModelCarro();
	}

	public Boolean CadastroCorreto(Carro carro) {

		if (isNullOrEmpty(carro.getCor()) || isNullOrEmpty(carro.getFabricante()) || isNullOrEmpty(carro.getPlaca())
				|| isNullOrEmpty(carro.getAno()) || isNullOrEmpty(carro.getModelo())) {

			return false;
		} else {
			return true;
		}
	}

	private boolean isNullOrEmpty(String s) {
		return (s == null || s.trim().equals(""));
	}

	public boolean Salvar(Carro carro) {
		boolean existe = modelCarro.verifica(carro);

		if (!existe) {
			modelCarro.salvar(carro);
		}

		return existe;
	}

	public boolean verificaPlaca(String placa) {
		for (int i = 0; i < modelCarro.getLstCarro().size(); i++) {
			if (modelCarro.getLstCarro().get(i).getPlaca().equals(placa)) {
				return false;
			}
		}
		return true;
	}

	public boolean placaValida(String text) {

		Pattern pattern = Pattern.compile("[A-Z]{3}-\\d{4}");
		Matcher match = pattern.matcher(text);

		if (match.find()) {
			return true;
		} else {
			return false;
		}
	}
}
