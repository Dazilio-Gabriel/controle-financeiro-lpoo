package TrabFinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

// Interface gráfica do sistema (Swing).
// A tela só conversa com o usuário: as contas ficam em ControleFinanceiro
// e a leitura/gravação fica em ArquivoLancamentos.
public class TelaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String CAMINHO_ARQUIVO = "dados/lancamentos.csv";
	private static final String[] TIPOS = { Receita.TIPO, Despesa.TIPO, DespesaParcelada.TIPO };
	private static final String[] COLUNAS = { "Data", "Tipo", "Descrição", "Categoria", "Valor", "Observação" };
	private static final int COLUNA_VALOR = 4;
	private static final Color VERDE = new Color(0, 128, 0);
	private static final Color VERMELHO = new Color(190, 0, 0);

	private ControleFinanceiro controle;
	private ArquivoLancamentos arquivo;

	private JComboBox<String> comboTipo;
	private JTextField campoDescricao;
	private JTextField campoValor;
	private JTextField campoData;
	private JComboBox<String> comboCategoria;
	private JTextField campoParcelas;

	private JButton botaoAdicionar;
	private JButton botaoRemover;
	private JButton botaoResumo;

	private DefaultTableModel modeloTabela;
	private JTable tabela;

	private JLabel rotuloReceitas;
	private JLabel rotuloDespesas;
	private JLabel rotuloSaldo;

	public TelaPrincipal() {
		super("Controle Financeiro - Trabalho de LPOO");

		controle = new ControleFinanceiro();
		arquivo = new ArquivoLancamentos(CAMINHO_ARQUIVO);

		montarTela();
		carregarLancamentos();
		atualizarTela();
	}

	// ---------------------------------------------------------------
	// Montagem da tela
	// ---------------------------------------------------------------

	private void montarTela() {
		JPanel conteudo = new JPanel(new BorderLayout(10, 10));
		conteudo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		conteudo.add(criarPainelFormulario(), BorderLayout.NORTH);
		conteudo.add(criarPainelTabela(), BorderLayout.CENTER);
		conteudo.add(criarPainelResumo(), BorderLayout.SOUTH);

		setContentPane(conteudo);
		atualizarCamposDoTipo();

		// apertar Enter em qualquer campo funciona como clicar em Adicionar
		getRootPane().setDefaultButton(botaoAdicionar);

		setSize(960, 580);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel criarPainelFormulario() {
		comboTipo = new JComboBox<String>(TIPOS);
		comboTipo.addActionListener(this);
		campoDescricao = new JTextField();
		campoValor = new JTextField();
		campoData = new JTextField(Formatador.formatarData(LocalDate.now()));
		comboCategoria = new JComboBox<String>();
		campoParcelas = new JTextField();

		// linha de cima com os nomes, linha de baixo com os campos
		JPanel campos = new JPanel(new GridLayout(2, 6, 8, 4));
		campos.add(new JLabel("Tipo"));
		campos.add(new JLabel("Descrição"));
		campos.add(new JLabel("Valor (R$)"));
		campos.add(new JLabel("Data (dd/mm/aaaa)"));
		campos.add(new JLabel("Categoria"));
		campos.add(new JLabel("Parcelas"));
		campos.add(comboTipo);
		campos.add(campoDescricao);
		campos.add(campoValor);
		campos.add(campoData);
		campos.add(comboCategoria);
		campos.add(campoParcelas);

		botaoAdicionar = criarBotao("Adicionar");
		botaoRemover = criarBotao("Remover selecionado");
		botaoResumo = criarBotao("Resumo por categoria");

		JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		botoes.add(botaoAdicionar);
		botoes.add(botaoRemover);
		botoes.add(botaoResumo);

		JPanel painel = new JPanel(new BorderLayout(0, 8));
		painel.setBorder(BorderFactory.createTitledBorder("Novo lançamento"));
		painel.add(campos, BorderLayout.CENTER);
		painel.add(botoes, BorderLayout.SOUTH);

		return painel;
	}

	// todos os botões são criados do mesmo jeito, por isso um método só
	private JButton criarBotao(String texto) {
		JButton botao = new JButton(texto);
		botao.addActionListener(this);
		return botao;
	}

	private JScrollPane criarPainelTabela() {
		modeloTabela = new DefaultTableModel(COLUNAS, 0);
		tabela = new JTable(modeloTabela);

		// a tabela é só para consulta: sem editor, ninguém altera as células
		tabela.setDefaultEditor(Object.class, null);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.setRowHeight(22);

		// valores em dinheiro ficam alinhados à direita
		DefaultTableCellRenderer alinhadoDireita = new DefaultTableCellRenderer();
		alinhadoDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		tabela.getColumnModel().getColumn(COLUNA_VALOR).setCellRenderer(alinhadoDireita);

		JScrollPane rolagem = new JScrollPane(tabela);
		rolagem.setBorder(BorderFactory.createTitledBorder("Lançamentos"));

		return rolagem;
	}

	private JPanel criarPainelResumo() {
		rotuloReceitas = criarRotuloResumo(VERDE);
		rotuloDespesas = criarRotuloResumo(VERMELHO);
		rotuloSaldo = criarRotuloResumo(VERDE);

		JPanel painel = new JPanel(new GridLayout(1, 3, 8, 0));
		painel.setBorder(BorderFactory.createTitledBorder("Resumo"));
		painel.add(rotuloReceitas);
		painel.add(rotuloDespesas);
		painel.add(rotuloSaldo);

		return painel;
	}

	private JLabel criarRotuloResumo(Color cor) {
		JLabel rotulo = new JLabel("", SwingConstants.CENTER);
		rotulo.setFont(rotulo.getFont().deriveFont(Font.BOLD, 15f));
		rotulo.setForeground(cor);
		return rotulo;
	}

	// ---------------------------------------------------------------
	// Eventos
	// ---------------------------------------------------------------

	// todo clique de botão e toda troca do tipo passam por aqui
	@Override
	public void actionPerformed(ActionEvent evento) {
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
			String tipo = (String) comboTipo.getSelectedItem();
			String categoria = (String) comboCategoria.getSelectedItem();
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
		int linha = tabela.getSelectedRow();

		if (linha == -1) {
			mostrarErro("Selecione um lançamento na tabela para remover.");
			return;
		}

		int resposta = JOptionPane.showConfirmDialog(this, "Remover o lançamento selecionado?", "Confirmar",
				JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			// a tabela mostra a lista na mesma ordem, então a linha é a posição
			controle.remover(linha);
			salvarLancamentos();
			atualizarTela();
		}
	}

	private void mostrarResumoPorCategoria() {
		TreeMap<String, Double> totais = controle.calcularDespesasPorCategoria();

		if (totais.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nenhuma despesa lançada ainda.", "Despesas por categoria",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		String texto = "";

		for (String categoria : totais.keySet()) {
			texto = texto + categoria + ": " + Formatador.formatarMoeda(totais.get(categoria)) + "\n";
		}

		texto = texto + "\nTotal: " + Formatador.formatarMoeda(controle.calcularTotalDespesas());

		JOptionPane.showMessageDialog(this, texto, "Despesas por categoria", JOptionPane.INFORMATION_MESSAGE);
	}

	// troca as categorias e libera o campo de parcelas conforme o tipo escolhido
	private void atualizarCamposDoTipo() {
		String tipo = (String) comboTipo.getSelectedItem();
		String[] categorias = Despesa.CATEGORIAS;

		if (Receita.TIPO.equals(tipo)) {
			categorias = Receita.CATEGORIAS;
		}

		comboCategoria.removeAllItems();

		for (int i = 0; i < categorias.length; i++) {
			comboCategoria.addItem(categorias[i]);
		}

		if (DespesaParcelada.TIPO.equals(tipo)) {
			campoParcelas.setEnabled(true);
			campoParcelas.setText("2");
		} else {
			campoParcelas.setEnabled(false);
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
		campoDescricao.setText("");
		campoValor.setText("");
		campoDescricao.requestFocus();
	}

	// ---------------------------------------------------------------
	// Arquivo
	// ---------------------------------------------------------------

	private void carregarLancamentos() {
		try {
			controle.adicionarTodos(arquivo.ler());

			if (arquivo.getLinhasIgnoradas() > 0) {
				JOptionPane.showMessageDialog(this,
						arquivo.getLinhasIgnoradas() + " linha(s) do arquivo estavam com erro e foram ignoradas.",
						"Aviso", JOptionPane.WARNING_MESSAGE);
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
		modeloTabela.setRowCount(0);

		// polimorfismo: a tela não pergunta qual é a classe de cada objeto;
		// getTipo(), getValorNoSaldo() e getObservacao() respondem por ela
		for (Lancamento lancamento : controle.getLancamentos()) {
			modeloTabela.addRow(new Object[] {
					Formatador.formatarData(lancamento.getData()),
					lancamento.getTipo(),
					lancamento.getDescricao(),
					lancamento.getCategoria(),
					Formatador.formatarMoeda(lancamento.getValorNoSaldo()),
					lancamento.getObservacao() });
		}

		double saldo = controle.calcularSaldo();

		rotuloReceitas.setText("Receitas: " + Formatador.formatarMoeda(controle.calcularTotalReceitas()));
		rotuloDespesas.setText("Despesas: " + Formatador.formatarMoeda(controle.calcularTotalDespesas()));
		rotuloSaldo.setText("Saldo: " + Formatador.formatarMoeda(saldo));

		if (saldo < 0) {
			rotuloSaldo.setForeground(VERMELHO);
		} else {
			rotuloSaldo.setForeground(VERDE);
		}
	}

	private void mostrarErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.ERROR_MESSAGE);
	}

}
