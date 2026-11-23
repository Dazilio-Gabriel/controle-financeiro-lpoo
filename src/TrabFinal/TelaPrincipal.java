package TrabFinal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// Interface gráfica do sistema (JavaFX).
// A tela só conversa com o usuário: as contas ficam em ControleFinanceiro
// e a leitura/gravação fica em ArquivoLancamentos.
public class TelaPrincipal extends Application implements EventHandler<ActionEvent> {

	private static final String CAMINHO_ARQUIVO = "dados/lancamentos.csv";
	private static final String[] TIPOS = { Receita.TIPO, Despesa.TIPO, DespesaParcelada.TIPO };
	private static final int QUANT_CAMPOS = 6;
	private static final Color VERDE = Color.rgb(0, 128, 0);
	private static final Color VERMELHO = Color.rgb(190, 0, 0);

	private ControleFinanceiro controle;
	private ArquivoLancamentos arquivo;

	private Stage janela;

	private ComboBox<String> comboTipo;
	private TextField campoDescricao;
	private TextField campoValor;
	private TextField campoData;
	private ComboBox<String> comboCategoria;
	private TextField campoParcelas;

	private Button botaoAdicionar;
	private Button botaoRemover;
	private Button botaoResumo;

	private TableView<Lancamento> tabela;

	private Label rotuloReceitas;
	private Label rotuloDespesas;
	private Label rotuloSaldo;

	// o JavaFX chama este método quando o programa abre
	@Override
	public void start(Stage janela) {
		this.janela = janela;

		controle = new ControleFinanceiro();
		arquivo = new ArquivoLancamentos(CAMINHO_ARQUIVO);

		janela.setTitle("Controle Financeiro - Trabalho de LPOO");
		janela.setScene(montarTela());
		janela.show();

		carregarLancamentos();
		atualizarTela();
	}

	// ---------------------------------------------------------------
	// Montagem da tela
	// ---------------------------------------------------------------

	private Scene montarTela() {
		TitledPane painelFormulario = criarPainelFormulario();
		TitledPane painelTabela = criarPainelTabela();
		TitledPane painelResumo = criarPainelResumo();

		// os três painéis ficam um embaixo do outro
		VBox raiz = new VBox(10, painelFormulario, painelTabela, painelResumo);
		raiz.setPadding(new Insets(10));

		// quando a janela cresce, quem ganha o espaço é a tabela
		VBox.setVgrow(painelTabela, Priority.ALWAYS);

		atualizarCamposDoTipo();

		return new Scene(raiz, 960, 580);
	}

	private TitledPane criarPainelFormulario() {
		comboTipo = new ComboBox<String>();
		comboTipo.getItems().addAll(TIPOS);
		comboTipo.setValue(Receita.TIPO);
		comboTipo.setOnAction(this);
		campoDescricao = new TextField();
		campoValor = new TextField();
		campoData = new TextField(Formatador.formatarData(LocalDate.now()));
		comboCategoria = new ComboBox<String>();
		campoParcelas = new TextField();

		// linha de cima com os nomes, linha de baixo com os campos
		GridPane campos = new GridPane();
		campos.setHgap(8);
		campos.setVgap(4);
		adicionarCampo(campos, 0, "Tipo", comboTipo);
		adicionarCampo(campos, 1, "Descrição", campoDescricao);
		adicionarCampo(campos, 2, "Valor (R$)", campoValor);
		adicionarCampo(campos, 3, "Data (dd/mm/aaaa)", campoData);
		adicionarCampo(campos, 4, "Categoria", comboCategoria);
		adicionarCampo(campos, 5, "Parcelas", campoParcelas);

		botaoAdicionar = criarBotao("Adicionar");
		botaoRemover = criarBotao("Remover selecionado");
		botaoResumo = criarBotao("Resumo por categoria");

		// apertar Enter em qualquer campo funciona como clicar em Adicionar
		botaoAdicionar.setDefaultButton(true);

		HBox botoes = new HBox(8, botaoAdicionar, botaoRemover, botaoResumo);

		return criarPainel("Novo lançamento", new VBox(10, campos, botoes));
	}

	// coloca o nome em cima e o campo embaixo, na coluna indicada.
	// Todas as colunas ficam com a mesma largura.
	private void adicionarCampo(GridPane grade, int coluna, String nome, Control campo) {
		ColumnConstraints largura = new ColumnConstraints();
		largura.setPercentWidth(100.0 / QUANT_CAMPOS);
		grade.getColumnConstraints().add(largura);

		campo.setMaxWidth(Double.MAX_VALUE);

		grade.add(new Label(nome), coluna, 0);
		grade.add(campo, coluna, 1);
	}

	// todos os botões são criados do mesmo jeito, por isso um método só
	private Button criarBotao(String texto) {
		Button botao = new Button(texto);
		botao.setOnAction(this);
		return botao;
	}

	// painel com título em cima, usado nas três partes da tela
	private TitledPane criarPainel(String titulo, Node conteudo) {
		TitledPane painel = new TitledPane(titulo, conteudo);
		painel.setCollapsible(false);
		return painel;
	}

	private TitledPane criarPainelTabela() {
		tabela = new TableView<Lancamento>();
		tabela.setPlaceholder(new Label("Nenhum lançamento cadastrado."));

		// cada coluna mostra o resultado de um get de Lancamento
		TableColumn<Lancamento, String> colunaValor = criarColuna("Valor", "valorFormatado", 130);
		colunaValor.setStyle("-fx-alignment: CENTER-RIGHT;");

		tabela.getColumns().add(criarColuna("Data", "dataFormatada", 100));
		tabela.getColumns().add(criarColuna("Tipo", "tipo", 140));
		tabela.getColumns().add(criarColuna("Descrição", "descricao", 230));
		tabela.getColumns().add(criarColuna("Categoria", "categoria", 150));
		tabela.getColumns().add(colunaValor);
		tabela.getColumns().add(criarColuna("Observação", "observacao", 160));

		TitledPane painel = criarPainel("Lançamentos", tabela);
		painel.setMaxHeight(Double.MAX_VALUE);

		return painel;
	}

	// PropertyValueFactory("descricao") faz a coluna chamar getDescricao()
	// de cada lançamento; "tipo" chama getTipo(), e assim por diante
	private TableColumn<Lancamento, String> criarColuna(String titulo, String propriedade, double largura) {
		TableColumn<Lancamento, String> coluna = new TableColumn<Lancamento, String>(titulo);
		coluna.setCellValueFactory(new PropertyValueFactory<Lancamento, String>(propriedade));
		coluna.setPrefWidth(largura);

		// a ordem da tabela é sempre a da lista (por data)
		coluna.setSortable(false);
		coluna.setReorderable(false);

		return coluna;
	}

	private TitledPane criarPainelResumo() {
		rotuloReceitas = criarRotuloResumo(VERDE);
		rotuloDespesas = criarRotuloResumo(VERMELHO);
		rotuloSaldo = criarRotuloResumo(VERDE);

		return criarPainel("Resumo", new HBox(rotuloReceitas, rotuloDespesas, rotuloSaldo));
	}

	private Label criarRotuloResumo(Color cor) {
		Label rotulo = new Label();
		rotulo.setFont(Font.font("System", FontWeight.BOLD, 15));
		rotulo.setTextFill(cor);
		rotulo.setAlignment(Pos.CENTER);

		// os três rótulos dividem a largura do painel
		rotulo.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(rotulo, Priority.ALWAYS);

		return rotulo;
	}

	// ---------------------------------------------------------------
	// Eventos
	// ---------------------------------------------------------------

	// todo clique de botão e toda troca do tipo passam por aqui
	@Override
	public void handle(ActionEvent evento) {
		Object origem = evento.getSource();

		if (origem == botaoAdicionar) {
			adicionarLancamento();
		} else if (origem == botaoRemover) {
			removerLancamento();
		} else if (origem == botaoResumo) {
			mostrarResumoPorCategoria();
		} else if (origem == comboTipo) {
			atualizarCamposDoTipo();
		}
	}

	private void adicionarLancamento() {
		try {
			String tipo = comboTipo.getValue();
			String categoria = comboCategoria.getValue();
			double valor = lerValor();
			LocalDate data = Formatador.converterData(campoData.getText());
			int parcelas = lerParcelas();

			Lancamento novo = ControleFinanceiro.criarLancamento(tipo, campoDescricao.getText(), valor, data,
					categoria, parcelas);

			controle.adicionar(novo);
			salvarLancamentos();
			atualizarTela();
			limparCampos();
		} catch (LancamentoInvalidoException e) {
			// qualquer dado errado cai aqui: o lançamento não é criado
			// e o usuário fica sabendo o que precisa corrigir
			mostrarErro(e.getMessage());
		}
	}

	private void removerLancamento() {
		Lancamento selecionado = tabela.getSelectionModel().getSelectedItem();

		if (selecionado == null) {
			mostrarErro("Selecione um lançamento na tabela para remover.");
			return;
		}

		Alert confirmacao = criarAlerta(AlertType.CONFIRMATION, "Confirmar",
				"Remover o lançamento \"" + selecionado.getDescricao() + "\"?");
		confirmacao.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		// showAndWait espera o usuário responder e devolve o botão escolhido
		Optional<ButtonType> resposta = confirmacao.showAndWait();

		if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
			controle.remover(selecionado);
			salvarLancamentos();
			atualizarTela();
		}
	}

	private void mostrarResumoPorCategoria() {
		TreeMap<String, Double> totais = controle.calcularDespesasPorCategoria();
		String texto = "";

		if (totais.isEmpty()) {
			texto = "Nenhuma despesa lançada ainda.";
		} else {
			for (String categoria : totais.keySet()) {
				texto = texto + categoria + ": " + Formatador.formatarMoeda(totais.get(categoria)) + "\n";
			}

			texto = texto + "\nTotal: " + Formatador.formatarMoeda(controle.calcularTotalDespesas());
		}

		criarAlerta(AlertType.INFORMATION, "Despesas por categoria", texto).showAndWait();
	}

	// troca as categorias e libera o campo de parcelas conforme o tipo escolhido
	private void atualizarCamposDoTipo() {
		String tipo = comboTipo.getValue();
		String[] categorias = Despesa.CATEGORIAS;

		if (Receita.TIPO.equals(tipo)) {
			categorias = Receita.CATEGORIAS;
		}

		comboCategoria.getItems().setAll(categorias);
		comboCategoria.setValue(categorias[0]);

		if (DespesaParcelada.TIPO.equals(tipo)) {
			campoParcelas.setDisable(false);
			campoParcelas.setText("2");
		} else {
			campoParcelas.setDisable(true);
			campoParcelas.setText("1");
		}
	}

	// ---------------------------------------------------------------
	// Leitura dos campos
	// ---------------------------------------------------------------

	// aceita tanto 150,90 quanto 150.90
	private double lerValor() throws LancamentoInvalidoException {
		try {
			return Double.parseDouble(campoValor.getText().trim().replace(",", "."));
		} catch (NumberFormatException e) {
			throw new LancamentoInvalidoException("Valor inválido. Digite só números, por exemplo: 150,90");
		}
	}

	private int lerParcelas() throws LancamentoInvalidoException {
		try {
			return Integer.parseInt(campoParcelas.getText().trim());
		} catch (NumberFormatException e) {
			throw new LancamentoInvalidoException("Quantidade de parcelas inválida. Digite um número inteiro.");
		}
	}

	private void limparCampos() {
		campoDescricao.clear();
		campoValor.clear();
		campoDescricao.requestFocus();
	}

	// ---------------------------------------------------------------
	// Arquivo
	// ---------------------------------------------------------------

	private void carregarLancamentos() {
		try {
			controle.adicionarTodos(arquivo.ler());

			if (arquivo.getLinhasIgnoradas() > 0) {
				criarAlerta(AlertType.WARNING, "Aviso",
						arquivo.getLinhasIgnoradas() + " linha(s) do arquivo estavam com erro e foram ignoradas.")
						.showAndWait();
			}
		} catch (IOException e) {
			mostrarErro("Não foi possível ler o arquivo de lançamentos:\n" + e.getMessage());
		}
	}

	private void salvarLancamentos() {
		try {
			arquivo.gravar(controle.getLancamentos());
		} catch (IOException e) {
			mostrarErro("Não foi possível gravar o arquivo de lançamentos:\n" + e.getMessage());
		}
	}

	// ---------------------------------------------------------------
	// Saída na tela
	// ---------------------------------------------------------------

	// redesenha a tabela e o resumo a partir da lista de lançamentos
	private void atualizarTela() {
		// polimorfismo: a tabela recebe a lista de Lancamento e chama os gets
		// de cada objeto sem saber qual é a classe dele. getTipo(),
		// getValorFormatado() e getObservacao() respondem conforme a subclasse.
		tabela.getItems().setAll(controle.getLancamentos());

		double saldo = controle.calcularSaldo();

		rotuloReceitas.setText("Receitas: " + Formatador.formatarMoeda(controle.calcularTotalReceitas()));
		rotuloDespesas.setText("Despesas: " + Formatador.formatarMoeda(controle.calcularTotalDespesas()));
		rotuloSaldo.setText("Saldo: " + Formatador.formatarMoeda(saldo));

		if (saldo < 0) {
			rotuloSaldo.setTextFill(VERMELHO);
		} else {
			rotuloSaldo.setTextFill(VERDE);
		}
	}

	// todas as janelas de aviso são montadas aqui
	private Alert criarAlerta(AlertType tipo, String titulo, String mensagem) {
		Alert alerta = new Alert(tipo);
		alerta.initOwner(janela);
		alerta.setTitle(titulo);
		alerta.setHeaderText(null);
		alerta.setContentText(mensagem);
		return alerta;
	}

	private void mostrarErro(String mensagem) {
		criarAlerta(AlertType.ERROR, "Atenção", mensagem).showAndWait();
	}

}
