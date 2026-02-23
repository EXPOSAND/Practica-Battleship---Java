package Practica2T;

import java.util.Scanner;

public class Dinamica {

    public Scanner datos = new Scanner(System.in);

    public Dinamica() {

    }

    public void jugar() {
        boolean otraPartida = true;

        //Este es para jugar de nuevo
        while (otraPartida == true) {
            boolean finJuego = false;
            boolean otroTurnoIA = false;
            boolean otroTurnoJ1 = false;
            Tablero t1 = new Tablero();
            Jugador j1 = new Jugador();
            IA maquina = new IA();

            j1.crearBarcos();
            j1.colocarBarco();

            maquina.crearBarcosIA();
            maquina.colocarBarcosIA();

            //Este para alternacion de trunos
            while (finJuego == false) {
                //Estepara repetir turno si hay impacto
                do {
                    otroTurnoJ1 = false;
                    System.out.println("Turno del jugador");
                    j1.realizarDisparo(maquina);

                    // Verificar si la flota de la IA ha sido destruida
                    if (maquina.flotaDestruidaIA() == true) {
                        finJuego = true;
                        System.out.println("¡Has ganado la partida!");
                        break;
                    }

                    // Verificar si el jugador impactó un barco
                    if (maquina.getTableroIA()[j1.filaDisparoJ1][j1.columnaDisparoJ1] != 'X') {
                        otroTurnoJ1 = false; // El jugador no impactó un barco, termina su turno
                    } else {
                        otroTurnoJ1 = true;
                    }
                } while (otroTurnoJ1 == true && finJuego == false);

                do {
                    if (finJuego == false) {
                        System.out.println("Turno de la IA");
                        maquina.realizarDisparo(j1);

                        // Verificar si la flota del jugador ha sido destruida
                        if (j1.flotaJ1Destruida() == true) {
                            finJuego = true;
                            System.out.println("¡La IA ha ganado!");
                            break;
                        }

                        if (j1.getTableroJ1()[maquina.filaDisparoIA][maquina.columnaDisparoIA] != 'X') {
                            otroTurnoIA = false; // La IA no impactó un barco, termina su turno
                        } else {
                            otroTurnoIA = true;
                        }
                    }
                } while (otroTurnoIA == true && finJuego == false);
            }
            // Para sabeer si se juega de nuevo
            System.out.println("¿Otra partida? Si(S) /No(N)");
            char respuesta = datos.next().charAt(0);
            if (respuesta != 'S' && respuesta != 's') {
                otraPartida = false;
            }
        }
    }
}
