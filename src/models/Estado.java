package models;

public class Estado {

	public String nombre;

	/**
	 * Constructor de la clase estado
	 * 
	 * @param nombre
	 */
	public Estado(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Devuelve el nombre del estado del pokemon
	 * 
	 * @return nombre del estado en el que se encuentra el pokemon
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del estado en el que se encuentra el pokemon
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
