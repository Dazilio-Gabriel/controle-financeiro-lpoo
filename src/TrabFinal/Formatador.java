package TrabFinal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

// Reúne as conversões de data e de dinheiro usadas pela tela e pelo arquivo,
// para o mesmo código não ficar repetido em vários lugares.
public class Formatador {

	private static final Locale BRASIL = Locale.forLanguageTag("pt-BR");

	// "uuuu" com STRICT faz o Java recusar datas que não existem, como 31/02/2026
	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	public static String formatarData(LocalDate data) {
		return data.format(FORMATO_DATA);
	}

	// transforma o texto digitado (dd/mm/aaaa) em data.
	// O erro do Java (DateTimeParseException) vira a exceção do nosso sistema.
	public static LocalDate converterData(String texto) throws LancamentoInvalidoException {
		try {
			return LocalDate.parse(texto.trim(), FORMATO_DATA);
		} catch (DateTimeParseException e) {
			throw new LancamentoInvalidoException("Data inválida: \"" + texto + "\". Use o formato dd/mm/aaaa.");
		}
	}

	// exemplo: 1500.5 vira "R$ 1.500,50"
	public static String formatarMoeda(double valor) {
		return String.format(BRASIL, "R$ %,.2f", valor);
	}

	// arredonda para 2 casas, para as somas com double não sobrarem
	// com valores como 0.30000000000000004
	public static double arredondar(double valor) {
		return Math.round(valor * 100) / 100.0;
	}

}
