package models;

import java.util.ArrayList;

public class Equipo {

	public ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();

	/**
	 * Constructor de la clase equipo
	 * 
	 * @param equipo
	 */
	public Equipo() {
		super();
	}

	/**
	 * Devuelve el equipo pokemon
	 * 
	 * @return
	 */
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
}
