package db;

public class DbIntegrityExeption extends RuntimeException{

	/**
	 Criação da classe para uma exeção caso queria excluir uma 
	 chave estrangera que tenha ligação.
	 */
	private static final long serialVersionUID = 1L;
	
	public DbIntegrityExeption(String msn) {
		super(msn);
	}
	
	

}
