package models;

import java.util.ArrayList;

import java.util.Scanner;

public class Pokemon {

	Scanner sc = new Scanner(System.in);
	public int numero;
	public String nombre;
	public TipoPokemon tipo1;
	public TipoPokemon tipo2;
	public Estado estado;
	public int attack;
	public int defense;
	public int specialAttack;
	public int specialDefense;
	public int speed;
	public ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
	public int maxHP;
	public int actualHP;
	public int level;

	public Pokemon(int numero, String nombre, TipoPokemon tipo1, TipoPokemon tipo2, int attack, int defense,
			int specialAttack, int specialDefense, int speed, int maxHP, int level, Estado estado) {
		super();
		this.numero = numero;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.maxHP = maxHP;
		this.level = level;
		this.actualHP = maxHP;
		this.estado = estado;
	}

	// Getters y setters

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoPokemon getTipo1() {
		return tipo1;
	}

	public void setTipo1(TipoPokemon tipo1) {
		this.tipo1 = tipo1;
	}

	public TipoPokemon getTipo2() {
		return tipo2;
	}

	public void setTipo2(TipoPokemon tipo2) {
		this.tipo2 = tipo2;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getActualHP() {
		return actualHP;
	}

	public void setActualHP(int actualHP) {
		this.actualHP = actualHP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Movimiento elegirMovimiento() {

		int i = 1;
		boolean opVal=true;
		do {
			opVal=true;
			try {
				System.out.println("¿Qué quieres que haga " + this.nombre + "?:");
				mostrarMovimentos();
				i = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				opVal=false;
				System.out.println("Elige un movimiento de la lista.");
			}
			
			if(i<1 || i>=movimientos.size()) {
				System.out.println("No es un movimiento de la lista");
				opVal=false;
			}
			
		} while (!opVal);
		i--;
		Movimiento m = this.movimientos.get(i);

		return m;
	}

	public void mostrarMovimentos() {
		int i = 1;
		for (Movimiento m : this.movimientos) {
			System.out.println(i + ". " + m.nombre + "\n");
			i++;
		}
	}
}
