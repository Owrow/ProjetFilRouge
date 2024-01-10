package bo;

public class Table {

	private int id;
	private int numero;
	private int nombre_places;
	
	public Table() {
	}
	
	public Table(int id, int numero, int nombre_places) {
		super();
		this.id = id;
		this.numero = numero;
		this.nombre_places = nombre_places;
	}
	
	public Table(int numero, int nombre_places) {
		super();
		this.numero = numero;
		this.nombre_places = nombre_places;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNombre_places() {
		return nombre_places;
	}

	public void setNombre_places(int nombre_places) {
		this.nombre_places = nombre_places;
	}

	@Override
	public String toString() {
		return "Table [id=" + id + ", numero=" + numero + ", nombre_places=" + nombre_places + "]";
	}
	
	
}
