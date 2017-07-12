package com.view.carros;

import com.controller.carros.ControllerCarro;
import com.model.carros.Carro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TodosMenus {

	private BorderPane root;
	private Label lblFabricante;
	private Label lblModelo;
	private Label lblCor;
	private Label lblPlaca;
	private Label lblAno;
	private TextField txtFabricante;
	private TextField txtModelo;
	private TextField txtCor;
	private TextField txtPlaca;
	private TextField txtAno;
	private Group groupCarrosCadastrados;
	private GridPane gridCarrosCadastrados;
	private Label lblCarrosCadastrados;
	private ObservableList<String> carrosCadastrados;
	private ListView<String> carrosListView;
	private Alert alerta;
	private Carro carro;
	private ControllerCarro controllerCarro;

	public Scene createMenuPrincipal() {
		controllerCarro = new ControllerCarro();

		root = new BorderPane();
		root.setTop(createTitle());
		root.setCenter(createGridLista());
		root.setBottom(createBottonButtons());
		root.setLeft(createCaracteristicasLabels());

		return new Scene(root, 800, 340);
	}

	private GridPane createGridLista() {
		groupCarrosCadastrados = new Group();

		gridCarrosCadastrados = new GridPane();
		gridCarrosCadastrados.setPadding(new Insets(5));
		gridCarrosCadastrados.setHgap(10);
		gridCarrosCadastrados.setVgap(10);

		lblCarrosCadastrados = new Label("Carros Cadastrados");
		GridPane.setHalignment(lblCarrosCadastrados, HPos.CENTER);
		gridCarrosCadastrados.add(lblCarrosCadastrados, 0, 0);

		carrosCadastrados = FXCollections.observableArrayList();

		carrosListView = new ListView<String>(carrosCadastrados);

		carrosListView.setPrefWidth(450);
		carrosListView.setPrefHeight(190);

		gridCarrosCadastrados.add(carrosListView, 0, 1);

		groupCarrosCadastrados.getChildren().add(gridCarrosCadastrados);

		return gridCarrosCadastrados;

	}

	private HBox createTitle() {

		HBox titleTop = new HBox();

		Label title = new Label("Cadastro Carros");

		title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));

		titleTop.setAlignment(Pos.CENTER);
		titleTop.getChildren().add(title);

		return titleTop;
	}

	private HBox createBottonButtons() {

		HBox buttonBar = new HBox();

		Button btnNovoCad = new Button("Cadastrar");
		btnNovoCad.setOnAction(new ButtonHandler(TipoAcao.NOVO_CADASTRO));

		Button btnLimpar = new Button("Limpar Campos");
		btnLimpar.setOnAction(new ButtonHandler(TipoAcao.LIMPAR));

		Button btnSair = new Button("Sair");
		btnSair.setOnAction(new ButtonHandler(TipoAcao.SAIR));

		buttonBar.setPadding(new Insets(10, 15, 10, 15));
		buttonBar.setSpacing(10);
		buttonBar.getChildren().addAll(btnNovoCad, btnLimpar, btnSair);

		return buttonBar;
	}

	private HBox createCaracteristicasLabels() {

		HBox leftContainer = new HBox();

		VBox labels = new VBox();

		VBox textFields = new VBox();

		lblFabricante = new Label("Fabricante: ");
		lblModelo = new Label("Modelo: ");
		lblCor = new Label("Cor: ");
		lblPlaca = new Label("Placa: ");
		lblAno = new Label("Ano: ");
		labels.setPadding(new Insets(10, 15, 10, 15));
		labels.setSpacing(20);
		labels.setAlignment(Pos.CENTER_RIGHT);
		labels.getChildren().addAll(lblFabricante, lblModelo, lblCor, lblPlaca, lblAno);

		txtFabricante = new TextField();
		txtModelo = new TextField();
		txtCor = new TextField();
		txtPlaca = new TextField();
		txtAno = new TextField();
		textFields.setPadding(new Insets(10, 15, 10, 15));
		textFields.setSpacing(10);
		textFields.setAlignment(Pos.CENTER_LEFT);
		textFields.getChildren().addAll(txtFabricante, txtModelo, txtCor, txtPlaca, txtAno);

		leftContainer.getChildren().addAll(labels, textFields);

		return leftContainer;
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {

		private TipoAcao tipo;

		public ButtonHandler(TipoAcao tipo) {

			this.tipo = tipo;

		}

		@Override
		public void handle(ActionEvent event) {
			if (tipo.equals(TipoAcao.NOVO_CADASTRO)) {

				if (controllerCarro.placaValida(txtPlaca.getText())) {
					carro = new Carro(txtFabricante.getText(), txtModelo.getText(), txtCor.getText(),
							txtPlaca.getText(), txtAno.getText());

					if (controllerCarro.CadastroCorreto(carro)) {

						if (controllerCarro.verificaPlaca(carro.getPlaca())) {

							controllerCarro.Salvar(carro);
							preencheerGrid();
						} else {
							alerta = new Alert(AlertType.ERROR);
							alerta.setHeaderText(null);
							alerta.setTitle("Erro de Cadastro");
							alerta.setContentText("Placa já foi cadastrada! \nVerifique");
							alerta.showAndWait();
						}

					} else {
						alerta = new Alert(AlertType.ERROR);
						alerta.setHeaderText(null);
						alerta.setTitle("Erro de Cadastro");
						alerta.setContentText("Todos os campos devem ser preenchidos! \nVerifique");
						alerta.showAndWait();
					}
				} else {
					alerta = new Alert(AlertType.ERROR);
					alerta.setHeaderText(null);
					alerta.setTitle("Erro de Cadastro");
					alerta.setContentText("Placa inválida! \nUse o modelo XXX-0000");
					alerta.showAndWait();
				}

			} else if (tipo.equals(TipoAcao.SAIR)) {
				System.exit(0);
			} else {
				txtFabricante.clear();
				txtModelo.clear();
				txtCor.clear();
				txtPlaca.clear();
				txtAno.clear();
			}
		}

		private void preencheerGrid() {
			StringBuilder descricaoCompleta = new StringBuilder();

			descricaoCompleta.append("Fabricante: " + txtFabricante.getText() + " - ");
			descricaoCompleta.append("Modelo: " + txtModelo.getText() + " - ");
			descricaoCompleta.append("Cor: " + txtCor.getText() + " - ");
			descricaoCompleta.append("Placa: " + txtPlaca.getText() + " - ");
			descricaoCompleta.append("Ano: " + txtAno.getText());

			carrosCadastrados.add(descricaoCompleta.toString());
		}
	}
}
