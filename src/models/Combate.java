package models;

public class Combate {

	public Entrenador entrenador1;
	public Entrenador entrenador2;
	public Pokemon pokemon1;
	public Pokemon pokemon2;

	/**
	 * Constructor de la clase Combate
	 * 
	 * @param entrenador1
	 * @param entrenador2
	 * @param pokemon1    pokemon elegido para el combate por el entrenador1
	 * @param pokemon2    pokemon elegido para el combate por el entrenador2
	 */
	public Combate(Entrenador entrenador1, Entrenador entrenador2, Pokemon pokemon1, Pokemon pokemon2) {
		super();
		this.entrenador1 = entrenador1;
		this.entrenador2 = entrenador2;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
	}

	public Entrenador getEntrenador1() {
		return entrenador1;
	}

	public void setEntrenador1(Entrenador entrenador1) {
		this.entrenador1 = entrenador1;
	}

	public Entrenador getEntrenador2() {
		return entrenador2;
	}

	public void setEntrenador2(Entrenador entrenador2) {
		this.entrenador2 = entrenador2;
	}

	public Pokemon getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(Pokemon pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(Pokemon pokemon2) {
		this.pokemon2 = pokemon2;
	}

	/**
	 * Establece el pokemon que realiza el primer movimiento
	 * 
	 * @return el primer pokemon en moverse
	 */
	public Pokemon quienEmpiezaTurno(int contPokE1, int contPokE2) {

		// Se estalece mediante la velocidad
		if (pokemon1.speed >= pokemon2.speed) {
			System.out.println("Es el turno de " + entrenador1.nombre);
			return entrenador1.equipo.equipo.get(contPokE1);
		} else {
			System.out.println("Es el turno de " + entrenador2.nombre);
			return entrenador2.equipo.equipo.get(contPokE2);
		}

	}

	/**
	 * Se elige un movimiento dependiendo de quien sea el más rápido
	 * 
	 * @param contTurnos
	 * @param contEnv
	 * @param contDormido
	 * @param contCong
	 * @param contPokE1
	 * @param contPokE2
	 * @param pok1V
	 * @param pok2V
	 */
	public void aplicarMovimiento(int contTurnos, int contEnv, int contDormido, int contCong, int contPokE1,
			int contPokE2, boolean pok1V, boolean pok2V) {

		if (pokemon1.speed >= pokemon2.speed) {
			if (pok1V && pok2V) {
				Movimiento m = pokemon1.elegirMovimiento();
				System.out.println(pokemon1.nombre + " realiza el movimiento " + m.nombre);

				boolean noMov = false;

				// Estados en los que se puede encontrar el pokemon atacante
				switch (pokemon1.estado.nombre) {
				case "Paralizado":
					int random = (int) (Math.random() * 4);
					if (random == 0) {
						System.out.println(pokemon1.nombre + " está paralizado y no se puede mover");
						noMov = true;
					}
					break;

				case "Quemado":
					System.out.println(pokemon1.nombre + " está quemado y pierde " + (pokemon1.actualHP / 16) + " PS");
					pokemon1.actualHP -= pokemon1.actualHP / 16;
					break;

				case "Envenenado":
					contEnv++;
					System.out
							.println(pokemon1.nombre + " está envenenado y pierde " + (pokemon1.actualHP / 8) + " PS");
					pokemon1.actualHP -= pokemon1.actualHP / 8;
					if (contTurnos % contEnv == 0) {
						pokemon1.actualHP -= 1;
					}
					break;

				case "Dormido":
					contDormido++;
					if (contDormido < 7) {
						System.out.println(pokemon1.nombre + " está dormido y no puede atacar");
						noMov = true;
					} else {
						pokemon1.estado.nombre = "Ninguno";
						System.out.println(pokemon1.nombre + " se ha despertado.");
					}
					break;

				case "Congelado":
					contCong++;
					if (contCong < 3) {
						System.out.println(pokemon1.nombre + " está congelado y no puede atacar");
						noMov = true;
					} else {
						pokemon1.estado.nombre = "Ninguno";
						System.out.println(pokemon1.nombre + " se ha descongelado");
					}
					break;
				case "Ninguno":
					break;
				}

				// Se mueve si no sale la probabilidad al estar paralizado, dormido o congelado
				if (!noMov) {
					if (m.power > 0) {
						calcularDanio(pokemon1, pokemon2, m);
					}

					cambiosRealizados(pokemon2, m);

					aplicarEstadoPok(pokemon2, m);
					System.out.println("PS de " + pokemon2.nombre + "=" + pokemon2.actualHP);

					pok2V = estaVivo(pokemon2);

//---------------------- Turno del otro pokemon de hacer un movimiento //
					if (pok2V) {
						m = pokemon2.elegirMovimiento();
						System.out.println(pokemon2.nombre + " realiza el movimiento " + m.nombre);
						boolean noMov2 = false;

						// Estados en los que se puede encontrar el pokemon atacante
						switch (pokemon1.estado.nombre) {
						case "Paralizado":
							int random = (int) (Math.random() * 4);
							if (random == 0) {
								System.out.println(pokemon2.nombre + " está paralizado y no se puede mover");
								noMov2 = true;
							}
							break;

						case "Quemado":
							System.out.println(
									pokemon2.nombre + " está quemado y pierde " + (pokemon2.actualHP / 16) + " PS");
							pokemon2.actualHP -= pokemon2.actualHP / 16;
							break;

						case "Envenenado":
							contEnv++;
							System.out.println(
									pokemon2.nombre + " está envenenado y pierde " + (pokemon2.actualHP / 8) + " PS");
							pokemon2.actualHP -= pokemon1.actualHP / 8;
							if (contTurnos % contEnv == 0) {
								pokemon2.actualHP -= 1;
							}
							break;

						case "Dormido":
							contDormido++;
							if (contDormido < 7) {
								System.out.println(pokemon2.nombre + " está dormido y no puede atacar");
								noMov = true;
							} else {
								pokemon1.estado.nombre = "Ninguno";
								System.out.println(pokemon2.nombre + " se ha despertado.");
							}
							break;

						case "Congelado":
							contCong++;
							if (contCong < 3) {
								System.out.println(pokemon2.nombre + " está congelado y no puede atacar");
								noMov = true;
							} else {
								pokemon2.estado.nombre = "Ninguno";
								System.out.println(pokemon1.nombre + " se ha descongelado");
							}
							break;
						case "Ninguno":
							break;
						}

						// Se mueve si no sale la probabilidad al estar paralizado, dormido o congelado
						if (!noMov2) {
							if (m.power > 0) {
								calcularDanio(pokemon2, pokemon1, m);
							}
							cambiosRealizados(pokemon2, m);
							aplicarEstadoPok(pokemon2, m);
						}
						System.out.println("PS de " + pokemon1.nombre + "=" + pokemon1.actualHP);
					}
				}
			}

		} else {

			if (pok2V && pok1V) {
				Movimiento m = this.pokemon2.elegirMovimiento();
				System.out.println(this.pokemon2.nombre + " realiza el movimiento " + m.nombre);
				boolean noMov = false;

				// Estados en los que se puede encontrar el pokemon atacante
				switch (this.pokemon2.estado.nombre) {
				case "Paralizado":
					int random = (int) (Math.random() * 4);
					if (random == 0) {
						System.out.println(this.pokemon2.nombre + " está paralizado y no se puede mover");
						noMov = true;
					}
					break;

				case "Quemado":
					System.out.println(
							this.pokemon2.nombre + " está quemado y pierde " + (this.pokemon2.actualHP / 16) + " PS");
					this.pokemon2.actualHP -= this.pokemon2.actualHP / 16;
					break;

				case "Envenenado":
					contEnv++;
					System.out.println(
							this.pokemon2.nombre + " está envenenado y pierde " + (this.pokemon2.actualHP / 8) + " PS");
					this.pokemon2.actualHP -= pokemon1.actualHP / 8;
					if (contTurnos % contEnv == 0) {
						this.pokemon2.actualHP -= 1;
					}
					break;

				case "Dormido":
					contDormido++;
					if (contDormido < 7) {
						System.out.println(this.pokemon2.nombre + " está dormido y no puede atacar");
						noMov = true;
					} else {
						pokemon1.estado.nombre = "Ninguno";
						System.out.println(this.pokemon2.nombre + " se ha despertado.");
					}
					break;

				case "Congelado":
					contCong++;
					if (contCong < 3) {
						System.out.println(this.pokemon2.nombre + " está congelado y no puede atacar");
						noMov = true;
					} else {
						this.pokemon2.estado.nombre = "Ninguno";
						System.out.println(pokemon1.nombre + " se ha descongelado");
					}
				case "Ninguno":
					break;
				}

				// Se mueve si no sale la probabilidad al estar paralizado, dormido o congelado
				if (!noMov) {
					if (m.power > 0) {
						calcularDanio(this.pokemon2, this.pokemon1, m);
					}

					// Todos los cambios que puede realizar el movimiento
					cambiosRealizados(this.pokemon1, m);

					// Estado que se le puede aplicar con el movimiento al pokemon atacado
					aplicarEstadoPok(this.pokemon1, m);

				}
				System.out.println("PS de " + this.pokemon1.nombre + "=" + this.pokemon1.actualHP);

				pok1V = estaVivo(pokemon1);

//---------------------- Turno del otro pokemon de hacer un movimiento //
				if (pok1V) {
					m = this.pokemon1.elegirMovimiento();
					System.out.println(this.pokemon1.nombre + " realiza el movimiento " + m.nombre);
					boolean noMov2 = false;

					// Estados en los que se puede encontrar el pokemon atacante
					switch (this.pokemon1.estado.nombre) {
					case "Paralizado":
						int random = (int) (Math.random() * 4);
						if (random == 0) {
							System.out.println(this.pokemon1.nombre + " está paralizado y no se puede mover");
							noMov2 = true;
						}
						break;

					case "Quemado":
						System.out.println(this.pokemon1.nombre + " está quemado y pierde "
								+ (this.pokemon1.actualHP / 16) + " PS");
						this.pokemon1.actualHP -= this.pokemon1.actualHP / 16;
						break;

					case "Envenenado":
						contEnv++;
						System.out.println(this.pokemon1.nombre + " está envenenado y pierde "
								+ (this.pokemon1.actualHP / 8) + " PS");
						this.pokemon1.actualHP -= this.pokemon1.actualHP / 8;
						if (contTurnos % contEnv == 0) {
							this.pokemon1.actualHP -= 1;
						}
						break;

					case "Dormido":
						contDormido++;
						if (contDormido < 7) {
							System.out.println(this.pokemon1.nombre + " está dormido y no puede atacar");
							noMov2 = true;
						} else {
							this.pokemon1.estado.nombre = "Ninguno";
							System.out.println(this.pokemon1.nombre + " se ha despertado.");
						}
						break;

					case "Congelado":
						contCong++;
						if (contCong < 3) {
							System.out.println(this.pokemon1.nombre + " está congelado y no puede atacar");
							noMov2 = true;
						} else {
							this.pokemon1.estado.nombre = "Ninguno";
							System.out.println(this.pokemon1.nombre + " se ha descongelado");
						}
						break;
					case "Ninguno":
						break;
					}

					// Se mueve si no sale la probabilidad al estar paralizado, dormido o congelado
					if (!noMov2) {
						if (m.power > 0) {
							calcularDanio(this.pokemon1, this.pokemon2, m);
						}

						// Todos los cambios que puede realizar el movimiento
						cambiosRealizados(this.pokemon2, m);

						// Estado que se le puede aplicar con el movimiento al pokemon atacado
						aplicarEstadoPok(this.pokemon2, m);
					}
					System.out.println("PS de " + this.pokemon2.nombre + "=" + pokemon2.actualHP);
				}
			}
		}
	}

	/**
	 * Comprueba que todos los pokemons de ambos entrenadores esten vivos. Si no hay
	 * ninguno vivo en alguno devuelve true y termina la batalla
	 * 
	 * @return
	 */
	public boolean isFinished() {
		boolean termina = true;
		int i = 0;
		while (termina && i < entrenador1.equipo.equipo.size()) {
			Pokemon p = entrenador1.equipo.equipo.get(i);
			if (p.actualHP > 0) {
				termina = false;
			}
			i++;
		}
		if (termina) {
			System.out.println("Todos los pokemon de " + entrenador1.nombre + " se han debilitado.\nEl ganador es "
					+ entrenador2.nombre);
		} else {
			i = 0;
			while (termina && i < entrenador2.equipo.equipo.size()) {
				Pokemon p = entrenador2.equipo.equipo.get(i);
				if (p.actualHP > 0) {
					termina = false;
				}
				i++;
			}
			if (termina) {
				System.out.println("Todos los pokemon de " + entrenador2.nombre + " se han debilitado.\nEl ganador es "
						+ entrenador1.nombre);
			}
		}

		return termina;
	}

	/**
	 * Calcula el daño que recibe el pokemon al ser atacado
	 * 
	 * @param atacante
	 * @param defensor
	 * @param m
	 */
	public void calcularDanio(Pokemon atacante, Pokemon defensor, Movimiento m) {
		double efecT1 = m.getEfectividad(defensor.tipo1);
		double efecT2 = m.getEfectividad(defensor.tipo2);

		double danio = 0.5 * (atacante.attack / defensor.defense) * m.actualPP;

		if (m.nombre.equalsIgnoreCase(atacante.tipo1.nombre) || (m.nombre.equalsIgnoreCase(atacante.tipo2.nombre))) {
			danio = danio * 1.25;
		}

		if (efecT1 >= efecT2) {
			danio *= efecT1;
		} else {
			danio *= efecT2;
		}

		m.damage = (int) (danio / 10) + 1;

		defensor.actualHP -= m.damage;

		System.out.println("Le ha quitado " + m.damage + " PS");
	}

	/**
	 * Establece todos los cambios que pueden ocurrirle a un pokemon al ser atacado
	 * con algunos movimientos
	 * 
	 * @param p
	 * @param m
	 */
	public void cambiosRealizados(Pokemon p, Movimiento m) {
		if (m.cambiaAttack != 0) {
			System.out.println("¡El ataque de " + p.nombre + " ha disminuido en " + m.cambiaAttack + "!");
			p.attack += m.cambiaAttack;
		}
		if (m.cambiaDef != 0) {
			System.out.println("¡La defensa de " + p.nombre + " ha disminuido en " + m.cambiaDef + "!");
			p.defense += m.cambiaDef;
		}
		if (m.cambiaSpAttack != 0) {
			System.out.println("¡El ataque especial de " + p.nombre + " ha disminuido en " + m.cambiaSpAttack + "!");
			p.specialAttack += m.cambiaSpAttack;
		}
		if (m.cambiaSpDef != 0) {
			System.out.println("¡La defensa especial de " + p.nombre + " ha disminuido en " + m.cambiaSpDef + "!");
			p.specialDefense += m.cambiaSpDef;
		}
		if (m.cambiaSpeed != 0) {
			System.out.println("¡La velocidad de " + p.nombre + " ha disminuido en " + m.cambiaSpeed + "!");
			p.speed += m.cambiaSpeed;
		}
	}

	/**
	 * Establece un estado a un pokemon después de ser atacado por ciertos movimientos
	 * 
	 * @param p pokemon afectado
	 * @param m movimiento que realiza el atacante
	 */
	public void aplicarEstadoPok(Pokemon p, Movimiento m) {
		switch (m.aplicaEstado.nombre) {
		case "Paralizado":
			p.speed -= p.speed * 0.75;
			System.out.println(p.nombre + " ha sido paralizado y su velocidad se ha reducido a " + p.speed);
			p.estado.nombre = "Paralizado";
			break;
		case "Quemado":
			p.attack /= 2;
			System.out.println("¡" + p.nombre + " se ha quemado y su ataque se ha reducido a la mitad!");
			p.estado.nombre = "Quemado";
			break;
		case "Envenenado":
			System.out.println("¡" + p.nombre + " ha sido envenenado!");
			p.estado.nombre = "Envenenado";
			p.estado.setNombre("Envenenado");
			break;
		case "Dormido":
			System.out.println("¡Oh no, " + p.nombre + " se ha dormido!");
			p.estado.nombre = "Dormido";
			break;
		case "Congelado":
			System.out.println("¡" + p.nombre + " ha sido congelado!");
			p.estado.nombre = "Congelado";
			break;
		}
	}

	/**
	 * Comprueba que el pokemon sigue vivo
	 * 
	 * @param p
	 * @return
	 */
	public boolean estaVivo(Pokemon p) {
		if (p.actualHP <= 0) {
			return false;
		} else {
			return true;
		}
	}
}
