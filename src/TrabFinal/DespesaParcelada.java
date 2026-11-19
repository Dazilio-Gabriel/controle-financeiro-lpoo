package TrabFinal;

import java.time.LocalDate;

// Compra dividida em várias parcelas (cartão, crediário...).
// É uma Despesa com uma informação a mais: a quantidade de parcelas.
// O desconto no saldo é herdado de Despesa (o valor total da compra).
public class DespesaParcelada extends Despesa {

	public static final String TIPO = "Despesa parcelada";

	private int parcelas;

	public DespesaParcelada(String descricao, double valor, LocalDate data, String categoria, int parcelas)
			throws LancamentoInvalidoException {
		super(descricao, valor, data, categoria);

		if (parcelas < 2) {
			throw new LancamentoInvalidoException("Uma despesa parcelada precisa ter pelo menos 2 parcelas.");
		}

		this.parcelas = parcelas;
	}

	@Override
	public String getTipo() {
		return TIPO;
	}

	@Override
	public int getParcelas() {
		return parcelas;
	}

	public double getValorParcela() {
		return Formatador.arredondar(getValor() / parcelas);
	}

	// exemplo: "6x de R$ 300,00"
	@Override
	public String getObservacao() {
		return parcelas + "x de " + Formatador.formatarMoeda(getValorParcela());
	}

}
