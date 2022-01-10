package mainapp;

import java.util.ArrayList;

import models.Combate;
import models.Entrenador;
import models.Equipo;
import models.Estado;
import models.Movimiento;
import models.Pokemon;
import models.TipoPokemon;

public class Main {

	public static void main(String[] args) {

		int contTurnos = 1;
		int contEnv = 0;
		int contDormido = 0;
		int contCong = 0;

		// Estados en los que pueden estar los pokemons
		Estado par = new Estado("Paralizado");
		Estado que = new Estado("Quemado");
		Estado env = new Estado("Envenenado");
		Estado dor = new Estado("Dormido");
		Estado con = new Estado("Congelado");
		Estado nin = new Estado("Ninguno");

		// Tipos de pokemons que existen
		TipoPokemon nor = new TipoPokemon("Nor");
		TipoPokemon luc = new TipoPokemon("Luc");
		TipoPokemon vol = new TipoPokemon("Vol");
		TipoPokemon ven = new TipoPokemon("Ven");
		TipoPokemon tie = new TipoPokemon("Tie");
		TipoPokemon roc = new TipoPokemon("Roc");
		TipoPokemon bic = new TipoPokemon("Bic");
		TipoPokemon nulo = new TipoPokemon("Nulo");

		// Movimientos que se pueden hacer
		Movimiento pla = new Movimiento("Placaje", nor, 35, 0, 0, 0, 0, 0, nin, 40);
		Movimiento disDem = new Movimiento("Disparo demora", bic, 40, 0, 0, 0, 0, -1, nin, 0);
		Movimiento picVen = new Movimiento("Picotazo venenoso", ven, 35, 0, 0, 0, 0, 0, env, 15);
		Movimiento torn = new Movimiento("Tornado", vol, 35, 0, 0, 0, 0, 0, nin, 40);
		Movimiento lat = new Movimiento("Látigo", nor, 30, 0, -1, 0, 0, 0, nin, 0);
		Movimiento ara = new Movimiento("Arañazo", nor, 35, 0, 0, 0, 0, 0, nin, 40);
		Movimiento ataAr = new Movimiento("Ataque arena", tie, 15, 0, 0, 0, contEnv, 0, nin, 0);
		Movimiento golKar = new Movimiento("Golpe kárate", luc, 25, 0, 0, 0, 0, 0, nin, 50);
		Movimiento lanz = new Movimiento("Lanzarrocas", roc, 15, 0, 0, 0, 0, 0, nin, 50);

		// Pokemons que se pueden tener
		Pokemon cat = new Pokemon(10, "Caterpie", bic, nulo, 30, 35, 20, 20, 45, 45, 1, nin);
		cat.movimientos.add(pla);
		cat.movimientos.add(disDem);

		Pokemon wee = new Pokemon(13, "Weedle", bic, ven, 35, 30, 20, 20, 50, 40, 1, nin);
		wee.movimientos.add(picVen);
		wee.movimientos.add(disDem);

		Pokemon pid = new Pokemon(16, "Pidgey", nor, vol, 45, 40, 35, 35, 56, 40, 1, nin);
		pid.movimientos.add(torn);
		pid.movimientos.add(pla);

		Pokemon rat = new Pokemon(19, "Rattata", nor, nulo, 56, 35, 25, 35, 72, 30, 1, nin);
		rat.movimientos.add(pla);
		rat.movimientos.add(lat);

		Pokemon dig = new Pokemon(50, "Diglet", tie, nulo, 55, 25, 35, 45, 95, 10, 1, nin);
		dig.movimientos.add(ara);
		dig.movimientos.add(ataAr);

		Pokemon mac = new Pokemon(66, "Machop", luc, nulo, 80, 50, 35, 35, 35, 70, 1, nin);
		mac.movimientos.add(golKar);

		Pokemon geo = new Pokemon(74, "Geodude", roc, tie, 80, 100, 30, 30, 20, 40, 1, nin);
		geo.movimientos.add(lanz);
		geo.movimientos.add(pla);

		// Equipos que pueden haber
		Equipo equipo1 = new Equipo();
		equipo1.equipo.add(cat);
		equipo1.equipo.add(pid);
		equipo1.equipo.add(dig);
		equipo1.equipo.add(geo);

		Equipo equipo2 = new Equipo();
		equipo2.equipo.add(wee);
		equipo2.equipo.add(rat);
		equipo2.equipo.add(mac);
		equipo2.equipo.add(pid);

		// Entrenadores que se pueden enfrentar
		Entrenador e1 = new Entrenador("Irene", equipo1);
		Entrenador e2 = new Entrenador("Pepe", equipo2);

		System.out.println("¡Empieza el combate entre " + e1.nombre + " y " + e2.nombre + "!");
		Combate c = new Combate(e1, e2, e1.equipo.equipo.get(0), e2.equipo.equipo.get(0));

		int contPokE1 = 0;
		int contPokE2 = 0;

		while (!c.isFinished()) {
			boolean pok1V = true;
			boolean pok2V = true;
			if (contPokE1 < e1.equipo.equipo.size() && e1.equipo.equipo.get(contPokE1).actualHP <= 0) {
				System.out.println(e1.equipo.equipo.get(contPokE1).nombre + " se ha debilitado");
				contPokE1++;
				System.out.println("Ahora luchará " + e1.equipo.equipo.get(contPokE1).nombre);
				pok1V = false;
			}
			if (contPokE1 == e1.equipo.equipo.size() - 1 && e1.equipo.equipo.get(contPokE1).actualHP <= 0) {
				System.out.println(e1.equipo.equipo.get(contPokE1).nombre + " se ha debilitado");
				System.out.println("El combate ha terminado");
			}

			if (contPokE2 < e2.equipo.equipo.size() && e2.equipo.equipo.get(contPokE2).actualHP <= 0) {
				System.out.println(e2.equipo.equipo.get(contPokE2).nombre + " se ha debilitado");
				contPokE2++;
				System.out.println("Ahora luchará " + e2.equipo.equipo.get(contPokE2).nombre);
				pok2V = false;
			}
			if (contPokE2 == e2.equipo.equipo.size() - 1 && e2.equipo.equipo.get(contPokE2).actualHP <= 0) {
				System.out.println(e1.equipo.equipo.get(contPokE1).nombre + " se ha debilitado");
				System.out.println("El combate ha terminado");
			}
			c.quienEmpiezaTurno(contPokE1, contPokE2);
			c.aplicarMovimiento(contTurnos, contEnv, contDormido, contCong, contPokE1, contPokE2, pok1V, pok2V);
			c.pokemon1 = e1.equipo.equipo.get(contPokE1);
			c.pokemon2 = e2.equipo.equipo.get(contPokE2);

			contTurnos++;
		}

		boolean termina = false;
		for (Pokemon p : e1.equipo.equipo) {
			if (p.actualHP != 0) {
				termina = false;
				break;
			} else {
				termina = true;
			}
		}
		if (termina) {
			System.out.println("Todos los pokemon de " + e1.nombre + " se han debilitado.\nEl ganador es " + e2.nombre);
		} else {
			for (Pokemon p : e2.equipo.equipo) {
				if (p.actualHP != 0) {
					termina = false;
					break;
				} else {
					termina = true;
				}
			}
			if (termina) {
				System.out.println(
						"Todos los pokemon de " + e2.nombre + " se han debilitado.\nEl ganador es " + e1.nombre);
			}
		}
	}

}
