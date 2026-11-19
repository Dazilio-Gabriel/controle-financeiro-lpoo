package TrabFinal;

import java.time.LocalDate;

// Classe base de tudo que entra ou sai do orçamento.
// É abstrata porque não existe "lançamento genérico": ele sempre é
// uma Receita, uma Despesa ou uma DespesaParcelada.
public abstract class Lancamento implements Comparable<Lancamento> {

	private static final double VALOR_MAXIMO = 999999999.99;

	private String descricao;
	private double valor;
	private LocalDate data;
	private String categoria;

	public Lancamento(String descricao, double valor, LocalDate data, String categoria)
			throws LancamentoInvalidoException {
		double valorArredondado = Formatador.arredondar(valor);

		if (descricao == null || descricao.trim().isEmpty()) {
			throw new LancamentoInvalidoException("Informe a descrição do lançamento.");
		}

		if (valorArredondado <= 0 || valorArredondado > VALOR_MAXIMO) {
			throw new LancamentoInvalidoException("O valor deve ser maior que zero e menor que 1 bilhão.");
		}

		if (data == null) {
			throw new LancamentoInvalidoException("Informe a data do lançamento.");
		}

		if (categoria == null || categoria.trim().isEmpty()) {
			throw new LancamentoInvalidoException("Informe a categoria do lançamento.");
		}

		this.descricao = descricao.trim();
		this.valor = valorArredondado;
		this.data = data;
		this.categoria = categoria.trim();
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}

	public String getCategoria() {
		return categoria;
	}

	// nome do tipo, mostrado na tela e gravado no arquivo
	public abstract String getTipo();

	// quanto o lançamento mexe no saldo: positivo soma, negativo desconta.
	// Cada subclasse responde do seu jeito (polimorfismo).
	public abstract double getValorNoSaldo();

	// lançamento comum é pago de uma vez só; DespesaParcelada sobrescreve
	public int getParcelas() {
		return 1;
	}

	// texto extra mostrado na tabela; vazio para a maioria dos lançamentos
	public String getObservacao() {
		return "";
	}

	// usado pelo Collections.sort para deixar a lista em ordem de data
	@Override
	public int compareTo(Lancamento outro) {
		return data.compareTo(outro.data);
	}

}
