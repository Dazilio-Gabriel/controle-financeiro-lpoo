package TrabFinal;

import java.time.LocalDate;

// Dinheiro que sai: aluguel, mercado, transporte etc.
public class Despesa extends Lancamento {

	public static final String TIPO = "Despesa";
	public static final String[] CATEGORIAS = { "Alimentação", "Moradia", "Transporte", "Saúde", "Educação", "Lazer",
			"Outras despesas" };

	public Despesa(String descricao, double valor, LocalDate data, String categoria)
			throws LancamentoInvalidoException {
		super(descricao, valor, data, categoria);
	}

	@Override
	public String getTipo() {
		return TIPO;
	}

	// despesa diminui o saldo, por isso o valor volta negativo
	@Override
	public double getValorNoSaldo() {
		return -getValor();
	}

}
