package Practica2T;

import java.util.Scanner;

public class Tablero {

    public static Scanner datos = new Scanner(System.in);
    protected char[][] tableroJ1;
    protected char columnaJ1;
    protected int filaJ1;

    public Tablero() {
        this.tableroJ1 = new char[10][10];
        inicializaTablero();
    }

    private void inicializaTablero() {
        for (int x = 0; x < this.tableroJ1.length; x++) {
            for (int y = 0; y < this.tableroJ1[x].length; y++) {
                this.tableroJ1[x][y] = '*';
            }
        }
    }

    public void dibujaTableroJ1() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < this.tableroJ1.length; x++) {
            System.out.print(x + 1);
            for (int y = 0; y < tableroJ1[x].length; y++) {
                System.out.print(" " + this.tableroJ1[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void dibujaTableroDisparosJ1() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        //Este for dibuja los numeros de las filas en el tablero
        for (int x = 0; x < this.tableroJ1.length; x++) {
            //Esto es para la primera columna (van los numeros)
            System.out.print(x + 1);
            //este for dibuja estrellas que representan el tablero
            for (int y = 0; y < this.tableroJ1.length; y++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }

    public boolean compruebaLimites() {
        boolean filasValidas = false;
        boolean columnasValidas = false;
        boolean limitesValidos = false;

        do {
            try {
                if (filaJ1 < 0 || filaJ1 >= 10) {
                    throw new FueraLimites("La fila esta fuera de los limites del tablero. Inserte una nueva");
                } else {
                    filasValidas = true;
                }
            } catch (FueraLimites e) {
                System.out.println(e.getMessage());
            }
        } while (filasValidas == false);

        do {
            try {
                if (columnaJ1 < 0 || columnaJ1 >= 10) {
                    throw new FueraLimites("La columna esta fuera de los limites del tablero");
                } else {
                    columnasValidas = true;
                }
            } catch (FueraLimites e) {
                System.out.println("Ingresa una nueva columna");
            }
        } while (filasValidas == false);

        //En este punto, tanto fila como columna son validos
        limitesValidos = true;
        return limitesValidos;
    }

    public void mostrarTablero() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < tableroJ1.length; x++) {
            System.out.print(x + 1);
            for (int y = 0; y < tableroJ1[x].length; y++) {
                if (tableroJ1[x][y] == 'V' || tableroJ1[x][y] == 'F' || tableroJ1[x][y] == 'B' || tableroJ1[x][y] == 'P' || tableroJ1[x][y] == 'X'|| tableroJ1[x][y] == 'O') {
                    System.out.print(" " + tableroJ1[x][y] + " ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public char[][] getTableroJ1() {
        return tableroJ1;
    }
}
