package db;

public class DbExeption extends RuntimeException{
	private static final long serialVersionUID = 1;
	
	public DbExeption(String msn) {
		super(msn);
	}
}
