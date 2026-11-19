package TrabFinal;

// Exceção própria do sistema: é lançada sempre que um lançamento
// tem algum dado errado (descrição vazia, valor zerado, data que não existe...).
// Como herda de Exception, quem chama é obrigado a tratar com try/catch.
public class LancamentoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public LancamentoInvalidoException(String mensagem) {
		super(mensagem);
	}

}
