package fr.ajc.ProjetFinal.exception;

public class StockInsuffisantException extends Exception {

	private static final long serialVersionUID = -1896028244094168131L;

	public StockInsuffisantException(String m) {
		super(m);
	}
}
