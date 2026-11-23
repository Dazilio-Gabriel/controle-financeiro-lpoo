package TrabFinal;

import javafx.application.Application;

// Ponto de entrada do programa: só manda o JavaFX abrir a tela.
// O main fica separado de TelaPrincipal de propósito: como esta classe não
// herda de Application, o programa roda só com os .jar da pasta lib no
// classpath, sem precisar configurar mais nada.
public class Main {

	public static void main(String[] args) {
		Application.launch(TelaPrincipal.class, args);
	}

}
