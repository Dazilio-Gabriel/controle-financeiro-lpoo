package TrabFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

// Leitura e gravação dos lançamentos em arquivo .csv (campos separados por ;).
// Cada linha do arquivo é um lançamento:
// tipo;data;descricao;categoria;valor;parcelas
public class ArquivoLancamentos {

	private static final String SEPARADOR = ";";
	private static final String CABECALHO = "tipo;data;descricao;categoria;valor;parcelas";
	private static final int QUANT_CAMPOS = 6;

	private File arquivo;
	private int linhasIgnoradas;

	public ArquivoLancamentos(String caminho) {
		arquivo = new File(caminho);
		linhasIgnoradas = 0;
	}

	// quantas linhas da última leitura estavam com erro e ficaram de fora
	public int getLinhasIgnoradas() {
		return linhasIgnoradas;
	}

	public ArrayList<Lancamento> ler() throws IOException {
		ArrayList<Lancamento> lancamentos = new ArrayList<Lancamento>();
		linhasIgnoradas = 0;

		// na primeira vez que o programa roda o arquivo ainda não existe
		if (!arquivo.exists()) {
			return lancamentos;
		}

		BufferedReader leitor = null;

		try {
			leitor = new BufferedReader(new FileReader(arquivo, StandardCharsets.UTF_8));

			// a primeira linha é o cabeçalho, por isso é lida e descartada
			String linha = leitor.readLine();
			linha = leitor.readLine();

			while (linha != null) {
				if (!linha.trim().isEmpty()) {
					try {
						lancamentos.add(converterLinha(linha));
					} catch (LancamentoInvalidoException e) {
						// uma linha estragada não impede a leitura das outras
						linhasIgnoradas++;
					}
				}

				linha = leitor.readLine();
			}
		} finally {
			// fecha o arquivo dando certo ou dando erro
			if (leitor != null) {
				leitor.close();
			}
		}

		return lancamentos;
	}

	public void gravar(ArrayList<Lancamento> lancamentos) throws IOException {
		File pasta = arquivo.getParentFile();

		if (pasta != null && !pasta.exists()) {
			pasta.mkdirs();
		}

		BufferedWriter escritor = null;

		try {
			escritor = new BufferedWriter(new FileWriter(arquivo, StandardCharsets.UTF_8));
			escritor.write(CABECALHO);
			escritor.newLine();

			for (Lancamento lancamento : lancamentos) {
				escritor.write(converterParaLinha(lancamento));
				escritor.newLine();
			}
		} finally {
			if (escritor != null) {
				escritor.close();
			}
		}
	}

	// transforma uma linha do arquivo em um objeto Lancamento
	private Lancamento converterLinha(String linha) throws LancamentoInvalidoException {
		String[] campos = linha.split(SEPARADOR, -1);

		if (campos.length != QUANT_CAMPOS) {
			throw new LancamentoInvalidoException("Linha com quantidade errada de campos: " + linha);
		}

		try {
			String tipo = campos[0].trim();
			LocalDate data = Formatador.converterData(campos[1]);
			String descricao = campos[2];
			String categoria = campos[3];
			double valor = Double.parseDouble(campos[4].trim());
			int parcelas = Integer.parseInt(campos[5].trim());

			return ControleFinanceiro.criarLancamento(tipo, descricao, valor, data, categoria, parcelas);
		} catch (NumberFormatException e) {
			throw new LancamentoInvalidoException("Número inválido na linha: " + linha);
		}
	}

	// transforma um objeto Lancamento em uma linha do arquivo.
	// Não precisa saber qual é a subclasse: getTipo() e getParcelas()
	// já respondem certo para cada uma (polimorfismo).
	private String converterParaLinha(Lancamento lancamento) {
		return lancamento.getTipo() + SEPARADOR
				+ Formatador.formatarData(lancamento.getData()) + SEPARADOR
				+ limparTexto(lancamento.getDescricao()) + SEPARADOR
				+ limparTexto(lancamento.getCategoria()) + SEPARADOR
				+ lancamento.getValor() + SEPARADOR
				+ lancamento.getParcelas();
	}

	// o ponto e vírgula separa os campos, então não pode aparecer dentro do texto
	private String limparTexto(String texto) {
		return texto.replace(SEPARADOR, ",");
	}

}
