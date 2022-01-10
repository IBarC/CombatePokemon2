package models;

public class Entrenador {

	public String nombre;
	public Equipo equipo;

	/**
	 * Constructor de la clase entrenador
	 * 
	 * @param nombre
	 * @param equipo
	 */
	public Entrenador(String nombre, Equipo equipo) {
		super();
		this.nombre = nombre;
		this.equipo = equipo;
	}

	/**
	 * Devuelve el nombre del entrenador
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
}
