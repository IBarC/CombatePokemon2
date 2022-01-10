package models;

public class TipoPokemon {

	public String nombre;

	/**
	 * Constructor de la clase TipoPokemon
	 * 
	 * @param nombre nombre del tipo de pokemon
	 */
	public TipoPokemon(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Devuelve el nombre del tipo de pokemon que es
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del tipo de pokemon que es
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
