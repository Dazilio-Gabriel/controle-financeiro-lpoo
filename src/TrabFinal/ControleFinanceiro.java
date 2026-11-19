package TrabFinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

// Guarda a lista de lançamentos e faz as contas do orçamento.
// Não sabe nada de tela nem de arquivo.
public class ControleFinanceiro {

	private ArrayList<Lancamento> lancamentos;

	public ControleFinanceiro() {
		lancamentos = new ArrayList<Lancamento>();
	}

	public ArrayList<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void adicionar(Lancamento lancamento) {
		lancamentos.add(lancamento);
		ordenarPorData();
	}

	public void adicionarTodos(ArrayList<Lancamento> novos) {
		lancamentos.addAll(novos);
		ordenarPorData();
	}

	public void remover(int posicao) {
		lancamentos.remove(posicao);
	}

	// o sort usa o compareTo de Lancamento, que compara pela data
	private void ordenarPorData() {
		Collections.sort(lancamentos);
	}

	public double calcularTotalReceitas() {
		double total = 0;

		for (Lancamento lancamento : lancamentos) {
			if (lancamento.getValorNoSaldo() > 0) {
				total = total + lancamento.getValorNoSaldo();
			}
		}

		return Formatador.arredondar(total);
	}

	public double calcularTotalDespesas() {
		double total = 0;

		for (Lancamento lancamento : lancamentos) {
			if (lancamento.getValorNoSaldo() < 0) {
				total = total - lancamento.getValorNoSaldo();
			}
		}

		return Formatador.arredondar(total);
	}

	public double calcularSaldo() {
		double saldo = 0;

		// polimorfismo: a lista é de Lancamento, mas cada objeto responde
		// conforme a sua classe (Receita soma, Despesa desconta)
		for (Lancamento lancamento : lancamentos) {
			saldo = saldo + lancamento.getValorNoSaldo();
		}

		return Formatador.arredondar(saldo);
	}

	// soma as despesas de cada categoria.
	// O TreeMap guarda pares categoria -> total e já deixa em ordem alfabética.
	public TreeMap<String, Double> calcularDespesasPorCategoria() {
		TreeMap<String, Double> totais = new TreeMap<String, Double>();

		for (Lancamento lancamento : lancamentos) {
			if (lancamento.getValorNoSaldo() < 0) {
				String categoria = lancamento.getCategoria();
				double acumulado = 0;

				if (totais.containsKey(categoria)) {
					acumulado = totais.get(categoria);
				}

				totais.put(categoria, Formatador.arredondar(acumulado - lancamento.getValorNoSaldo()));
			}
		}

		return totais;
	}

	// cria o objeto da classe certa a partir do nome do tipo.
	// É usado tanto pela tela quanto pela leitura do arquivo.
	public static Lancamento criarLancamento(String tipo, String descricao, double valor, LocalDate data,
			String categoria, int parcelas) throws LancamentoInvalidoException {
		if (Receita.TIPO.equals(tipo)) {
			return new Receita(descricao, valor, data, categoria);
		}

		if (Despesa.TIPO.equals(tipo)) {
			return new Despesa(descricao, valor, data, categoria);
		}

		if (DespesaParcelada.TIPO.equals(tipo)) {
			return new DespesaParcelada(descricao, valor, data, categoria, parcelas);
		}

		throw new LancamentoInvalidoException("Tipo de lançamento desconhecido: " + tipo);
	}

}
