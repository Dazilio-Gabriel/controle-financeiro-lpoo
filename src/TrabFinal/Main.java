package TrabFinal;

import javax.swing.UIManager;

// Ponto de entrada do programa: só prepara o visual e abre a tela.
public class Main {

	public static void main(String[] args) {
		usarVisualDoSistema();

		TelaPrincipal tela = new TelaPrincipal();
		tela.setVisible(true);
	}

	// deixa as janelas com a aparência do Windows em vez da aparência padrão do Java
	private static void usarVisualDoSistema() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// se não der para trocar, o programa segue com o visual padrão
			System.out.println("Não foi possível usar o visual do sistema: " + e.getMessage());
		}
	}

}
