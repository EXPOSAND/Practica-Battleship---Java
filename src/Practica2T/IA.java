package Practica2T;

import java.util.Random;

public class IA {

    private Random random;
    protected Barco[] barcosIA = new Barco[10];
    protected char[][] tableroIA;
    protected char columnaIA;
    protected int filaIA;
    protected int columnaDisparoIA;
    protected int filaDisparoIA;
    protected int[] vidasIA;
    protected char dir = ' ';

    public IA() {
        this.tableroIA = new char[10][10];
        this.random = new Random();
        this.vidasIA = new int[]{4, 6, 6, 4};
        inicializaTableroIA();
    }

    public void crearBarcosIA() {
        for (int x = 0; x < barcosIA.length; x++) {
            if (x >= 0 && x < 4) {
                barcosIA[x] = new Barco("Velero", 'V', 1, 1, 'H');
            } else if (x >= 4 && x < 7) {
                barcosIA[x] = new Barco("Fragata", 'F', 2, 2, 'H');
            } else if (x >= 7 && x < 9) {
                barcosIA[x] = new Barco("Buque", 'B', 3, 3, 'H');
            } else if (x == 9) {
                barcosIA[x] = new Barco("Portaaviones", 'P', 4, 4, 'H');
            }
        }
    }

    // Método para generar una posición aleatoria
    public void generarPosicionAleatoria(Barco b) {
        boolean valido = false;
        do {
            // Generar fila aleatoria entre 1 y 10
            filaIA = random.nextInt(10) + 1;

            // Generar columna aleatoria entre 'A' y 'J'
            char[] columnas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
            columnaIA = columnas[random.nextInt(10)];

            char[] orientaciones = {'H', 'V'};
            dir = orientaciones[random.nextInt(2)];

            // Verificar si la posición es válida
            valido = compruebaLimitesIA(b);
        } while (!valido);
    }

    public int traducirColumnaIA() {
        int posH = 0;
        switch (columnaIA) {
            case 'A':
                posH = 0;
                break;
            case 'B':
                posH = 1;
                break;
            case 'C':
                posH = 2;
                break;
            case 'D':
                posH = 3;
                break;
            case 'E':

                posH = 4;
                break;
            case 'F':

                posH = 5;
                break;
            case 'G':
                posH = 6;
                break;
            case 'H':
                break;
            case 'I':
                posH = 8;
                break;
            case 'J':
                posH = 9;
                break;
        }
        return posH;
    }

    public boolean sePuedeColocarMaquina(Barco b) {
        int col = 0;
        boolean sePuede = false;
        boolean estaVacio = true;

        do {
            estaVacio = true;
            generarPosicionAleatoria(b);
            col = traducirColumnaIA();
            if (compruebaLimitesIA(b) == false) {
                estaVacio = false;
            } else {
                if (this.dir == 'H') {
                    if (tableroIA[filaIA - 1][col] != '*') {
                        estaVacio = false;
                    } else {
                        for (int x = 0; x < b.tamaño; x++) {
                            if (tableroIA[filaIA - 1][col + x] != '*') {
                                estaVacio = false;
                            }
                        }
                    }
                } else {
                    if (tableroIA[filaIA - 1][col] != '*') {
                        estaVacio = false;
                    } else {
                        for (int x = 0; x < b.tamaño; x++) {
                            if (tableroIA[filaIA - 1 + x][col] != '*') {
                                estaVacio = false;
                            }
                        }
                    }
                }

            }
            if (estaVacio == true) {
                sePuede = true;
            }
        } while (estaVacio == false);
        return sePuede;
    }

    // Método para colocar barcos de la máquina
    public void colocarBarcosIA() {
        int col = 0;
        for (Barco actual : barcosIA) {
            boolean colocado = false;
            do {
                if (sePuedeColocarMaquina(actual) == true) {
                    actual.setOrientacion(this.dir);
                    for (int x = 0; x < actual.tamaño; x++) {
                        col = traducirColumnaIA();
                        if (actual.getOrientacion() == 'H' && actual.Simbolo != 'V') {
                            tableroIA[filaIA - 1][col + x] = actual.Simbolo;
                        } else if (actual.getOrientacion() == 'V' && actual.Simbolo != 'V') {
                            tableroIA[filaIA - 1 + x][col] = actual.Simbolo;
                        } else {
                            tableroIA[filaIA - 1][col + x] = actual.Simbolo;
                        }
                        colocado = true;
                    }
                }
            } while (colocado==false);
        }
        //EN CASO DE QUE NO DESEES VER EL TABLERO DE LA IA, ESTE ES EL TABLERO QUE SE COMENTA PARA OCULTARLO
        //mostrarTableroIA();
    }

    private void inicializaTableroIA() {
        for (int x = 0; x < this.tableroIA.length; x++) {
            for (int y = 0; y < this.tableroIA[x].length; y++) {
                this.tableroIA[x][y] = '*';
            }
        }
    }

    public void dibujaTableroIA() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < this.tableroIA.length; x++) {
            System.out.print(x + 1);
            for (int y = 0; y < tableroIA[x].length; y++) {
                System.out.print(" " + this.tableroIA[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void mostrarTableroIA() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < tableroIA.length; x++) {
            System.out.print(x + 1);
            for (int y = 0; y < tableroIA[x].length; y++) {
                if (tableroIA[x][y] == 'V' || tableroIA[x][y] == 'F' || tableroIA[x][y] == 'B' || tableroIA[x][y] == 'P' || tableroIA[x][y] == 'X' || tableroIA[x][y] == 'O') {
                    System.out.print(" " + tableroIA[x][y] + " ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean compruebaLimitesIA(Barco b) {
        int col = traducirColumnaIA();
        boolean valido = false;
        if (this.dir == 'H') {
            if (col + b.getTamaño() > 10) {
                valido = false;
            } else {
                valido = true;
            }
        } else {
            if ((filaIA - 1) + b.getTamaño() > 10) {
                valido = false;
            } else {
                valido = true;
            }
        }
        return valido; // El barco no traspasa los límites
    }

    // Método para que la IA realice un disparo al jugador
    public void realizarDisparo(Jugador jugador) {
        Random random = new Random();
        filaDisparoIA = random.nextInt(10);
        columnaDisparoIA = random.nextInt(10);

        // Verificar si el disparo acertó a un barco del jugador
        if (jugador.getTableroJ1()[filaDisparoIA][columnaDisparoIA] != '*') {
            char simb = jugador.getTableroJ1()[filaDisparoIA][columnaDisparoIA];
            System.out.println("¡Tocado! ¡La IA ha acertado!");
            jugador.getTableroJ1()[filaDisparoIA][columnaDisparoIA] = 'X'; // Marcar impacto
            jugador.registrarImpactoBarcoJ1(simb);

        } else {
            System.out.println("La IA ha fallado...");
            jugador.getTableroJ1()[filaDisparoIA][columnaDisparoIA] = 'O'; // Marcar agua
        }
        System.out.println("-------------------------");
        System.out.println("        <Tablero J1>      ");
        jugador.mostrarTablero();
        System.out.println("-------------------------");
    }

    // Método para registrar un impacto en un barco de J1
    public void registrarImpactoBarcoIA(char simbolo) {
        for (Barco barco : barcosIA) {
            if (barco.getSimbolo() == simbolo) {
                registrarImpactoIA(barco.getSimbolo());
                break;
            }
        }
    }

    // Método para registrar un impacto
    public void registrarImpactoIA(char simbolo) {
        char simb = simbolo;
        if (simbolo == 'V') {
            this.vidasIA[0]--;
            System.out.println("Has destruido uno de los veleros de la IA");
        } else if (simbolo == 'F') {
            this.vidasIA[1]--;
        } else if (simbolo == 'B') {
            this.vidasIA[2]--;
        } else if (simbolo == 'P') {
            this.vidasIA[3]--;
        } else {
            System.out.println("Algo no va bien");
        }
        barcoDestruidoIA(simb);
    }

    // verifica si el barco ha sido destruido
    public void barcoDestruidoIA(char simb) {
        if (simb == 'F') {
            if (vidasIA[1] % 2 == 0) {
                System.out.println("Has destruido una de las fragatas de la IA");
            }
        } else if (simb == 'B') {
            if (vidasIA[2] % 3 == 0) {
                System.out.println("Has destruido uno de los buques de la IA");
            }
        } else if (simb == 'P') {
            if (vidasIA[3] % 4 == 0) {
                System.out.println("Has destruido el portaaviones de la IA");
            }
        }
    }

    public boolean flotaDestruidaIA() {
        // Verificar si todas las vidas de los barcos de la IA son menores o iguales a 0
        return vidasIA[0] <= 0 && vidasIA[1] <= 0 && vidasIA[2] <= 0 && vidasIA[3] <= 0;
    }

    public char[][] getTableroIA() {
        return tableroIA;
    }

    private int traducirColumnaDisparoIA() {
        return traducirColumnaIA();
    }
}
