package models;

public class Movimiento {

	public String nombre;
	public TipoPokemon tipo;
	public int maxPP;
	public int actualPP;
	public int damage;
	public int cambiaAttack;
	public int cambiaDef;
	public int cambiaSpAttack;
	public int cambiaSpDef;
	public int cambiaSpeed;
	public Estado aplicaEstado;
	public int power;

	public Movimiento(String nombre, TipoPokemon tipo, int maxPP,int cambiaAttack,
			int cambiaDef, int cambiaSpAttack, int cambiaSpDef, int cambiaSpeed, Estado aplicaEstado, int power) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.maxPP = maxPP;
		this.cambiaAttack = cambiaAttack;
		this.cambiaDef = cambiaDef;
		this.cambiaSpAttack = cambiaSpAttack;
		this.cambiaSpDef = cambiaSpDef;
		this.cambiaSpeed = cambiaSpeed;
		this.aplicaEstado = aplicaEstado;
		this.power = power;
		this.actualPP = this.maxPP;
	}

	// Getters y setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoPokemon getTipo() {
		return tipo;
	}

	public void setTipo(TipoPokemon tipo) {
		this.tipo = tipo;
	}

	public int getMaxPP() {
		return maxPP;
	}

	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}

	public int getActualPP() {
		return actualPP;
	}

	public void setActualPP(int actualPP) {
		this.actualPP = actualPP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Estado getAplicaEstado() {
		return aplicaEstado;
	}

	public void setAplicaEstado(Estado aplicaEstado) {
		this.aplicaEstado = aplicaEstado;
	}

	public void setCambiaAttack(int cambiaAttack) {
		this.cambiaAttack = cambiaAttack;
	}

	public void setCambiaDef(int cambiaDef) {
		this.cambiaDef = cambiaDef;
	}

	public void setCambiaSpAttack(int cambiaSpAttack) {
		this.cambiaSpAttack = cambiaSpAttack;
	}

	public void setCambiaSpDef(int cambiaSpDef) {
		this.cambiaSpDef = cambiaSpDef;
	}

	public void setCambiaSpeed(int cambiaSpeed) {
		this.cambiaSpeed = cambiaSpeed;
	}

	public int getPower() {
		return power;
	}

	/**
	 * Establece la efectividad de un golpe contra un rival dependiendo del tipo de
	 * golpe
	 * 
	 * @param tipoRival tipo de pokemon del rivals
	 * @return el multiplicador de daño
	 */
	public double getEfectividad(TipoPokemon tipoRival) {

		double efect = 1;

		switch (this.tipo.nombre) {

		case "Nor":
			switch (tipoRival.nombre) {
			case "Roc":
				efect = 0.5;
				break;
			}

		case "Luc":
			switch (tipoRival.nombre) {
			case "Nor":
				efect = 2;
				break;
			case "Vol":
				efect = 0.5;
				break;
			case "Ven":
				efect = 0.5;
				break;
			case "Roc":
				efect = 2;
				break;
			case "Bic":
				efect = 0.5;
				break;
			}

		case "Vol":
			switch (tipoRival.nombre) {
			case "Luc":
				efect = 2;
				break;
			case "Roc":
				efect = 0.5;
				break;
			case "Bic":
				efect = 2;
				break;
			}

		case "Ven":
			switch (tipoRival.nombre) {
			case "Ven":
				efect = 0.5;
				break;
			case "Tie":
				efect = 0.5;
				break;
			case "Roc":
				efect = 0.5;
				break;
			}

		case "Tie":
			switch (tipoRival.nombre) {
			case "Vol":
				efect = 0;
				break;
			case "Ven":
				efect = 2;
				break;
			case "Roc":
				efect = 2;
				break;
			case "Bic":
				efect = 0.5;
				break;
			}

		case "Roc":
			switch (tipoRival.nombre) {
			case "Luc":
				efect = 0.5;
				break;
			case "Vol":
				efect = 2;
				break;
			case "Tie":
				efect = 0.5;
				break;
			case "Bic":
				efect = 2;
				break;
			}

		case "Bic":
			switch (tipoRival.nombre) {
			case "Luc":
				efect = 0.5;
				break;
			case "Vol":
				efect = 0.5;
				break;
			case "Ven":
				efect = 0.5;
				break;
			}
		}

		return efect;
	}
}
