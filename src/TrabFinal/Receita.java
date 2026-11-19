package TrabFinal;

import java.time.LocalDate;

// Dinheiro que entra: salário, renda extra etc.
public class Receita extends Lancamento {

	public static final String TIPO = "Receita";
	public static final String[] CATEGORIAS = { "Salário", "Renda extra", "Outras receitas" };

	public Receita(String descricao, double valor, LocalDate data, String categoria)
			throws LancamentoInvalidoException {
		super(descricao, valor, data, categoria);
	}

	@Override
	public String getTipo() {
		return TIPO;
	}

	// receita aumenta o saldo
	@Override
	public double getValorNoSaldo() {
		return getValor();
	}

}
