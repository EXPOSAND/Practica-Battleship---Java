package Practica2T;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jugador extends Tablero {

    protected Barco[] barcosJ1;
    protected int columnaDisparoJ1;
    protected char columnaCharDisparoJ1;
    protected int filaDisparoJ1;
    protected char dir=' ';
    protected int[] vidasJ1;

    public Jugador() {
        super();
        this.barcosJ1 = new Barco[10];
        this.vidasJ1 = new int[]{4, 6, 6, 4};
    }

    public void crearBarcos() {
        for (int x = 0; x < barcosJ1.length; x++) {
            if (x >= 0 && x < 4) {
                barcosJ1[x] = new Barco("Velero", 'V', 1, 4,'H');
            } else if (x >= 4 && x < 7) {
                barcosJ1[x] = new Barco("Fragata", 'F', 2, 3,'H');
            } else if (x >= 7 && x < 9) {
                barcosJ1[x] = new Barco("Buque", 'B', 3, 2,'H');
            } else if (x == 9) {
                barcosJ1[x] = new Barco("Portaaviones", 'P', 4, 4,'H');
            }
        }
    }

    //Pido la fila y columna al usuario en bruto (columna como char)
    public void existePosicion() {
        boolean valido = false;
        int numFila = 0;
        char letra = ' ';
        char dir = ' ';

        do {
            try {
                datos = new Scanner(System.in);
                System.out.println("Ingrese la fila en la que quiere colocar el barco (Numeros)");
                numFila = datos.nextInt();
                if (numFila >= 0 && numFila < 11) {
                    valido = true;
                } else {
                    System.out.println("La fila debe estar entre 1 y 10. Inserte una nueva");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un numero");
            }
        } while (valido == false);
        filaJ1 = numFila;

        do {
            System.out.println("Ingrese la columna en la que quiere colocar el barco (Letras)");
            letra = datos.next().toUpperCase().charAt(0);
            if (letra != 'A' && letra != 'B' && letra != 'C' && letra != 'D' && letra != 'E'
                    && letra != 'F' && letra != 'G' && letra != 'H' && letra != 'I' && letra != 'J') {
                System.out.println("La columna debe estar comprendida entre la A y la J");
            }
        } while (letra != 'A' && letra != 'B' && letra != 'C' && letra != 'D' && letra != 'E' && letra != 'F' && letra != 'G' && letra != 'H' && letra != 'I' && letra != 'J');
        columnaJ1 = letra;
        
        do {
            System.out.println("Ingrese la orientacion en la que desea colocar el barco (H o V)");
            dir = datos.next().toUpperCase().charAt(0);
            if (dir != 'H' && dir != 'V') {
                System.out.println("La orientacion solo puede ser horizontal (H) o Vertical (V)");
            }
        } while (dir != 'H' && dir != 'V');
        this.dir = dir;
    }

    //Traduzco la columna a entero y se lo paso a sePuedeColocar()
    public int traducirColumna(char col) {
        int posH = 0;
        switch (col) {
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
                posH = 7;
                break;
            case 'I':
                posH = 8;
                break;
            case 'J':;
                posH = 9;
                break;
        }
        return posH;
    }

    public boolean sePuedeColocar(Barco b) {
        int col = 0;
        boolean sePuede = false;
        boolean estaVacio = true;

        do {
            estaVacio = true;
            //Setea fila y columna
            existePosicion();
            //traduce columna y la devuelve como entero
            col = traducirColumna(columnaJ1);
            if (compruebaLimites(b) == false) {
                //Ese espacio NO esta vacio. Se repite el bucle
                estaVacio = false;
            } else {
                if (this.dir == 'H') {
                    if (tableroJ1[filaJ1 - 1][col] != '*') {
                        System.out.println("Este espacio ya esta ocupado. Debe elegir otra posicion inicial");
                        estaVacio = false;
                    } else {
                        for (int x = 0; x < b.tamaño; x++) {
                            if (tableroJ1[filaJ1 - 1][col + x] != '*') {
                                estaVacio = false;
                                System.out.println("Una de las posiciones donde iria el barco esta ocupada. Debe elegir otra");
                            }
                        }
                    }
                } else {
                    if (tableroJ1[filaJ1 - 1][col] != '*') {
                        System.out.println("Este espacio ya esta ocupado. Debe elegir otra posicion inicial");
                        estaVacio = false;
                    } else {
                        for (int x = 0; x < b.tamaño; x++) {
                            if (tableroJ1[filaJ1 - 1 + x][col] != '*') {
                                estaVacio = false;
                                System.out.println("Una de las posiciones donde iria el barco esta ocupada. Debe elegir otra");
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

    public void colocarBarco() {
        int col = 0;
        for (Barco actual : barcosJ1) {
            System.out.println("Barco a posicionar: " + actual.Nombre);

            if (sePuedeColocar(actual) == true) {
                actual.setOrientacion(this.dir);
                for (int x = 0; x < actual.tamaño; x++) {
                    col = traducirColumna(columnaJ1);
                    if (actual.getOrientacion()== 'H' && actual.Simbolo!='V') {
                        tableroJ1[filaJ1 - 1][col + x] = actual.Simbolo;
                    } else if (actual.getOrientacion() == 'V' && actual.Simbolo!='V') {
                        tableroJ1[filaJ1 - 1 + x][col] = actual.Simbolo;
                    }else{ //Se que este else es innecesario pero por alguna razon al intentar colocar la pos H como predeterminada al crear los barcos, en el primer velero no se me guardaba y por tanto, luego no me lo pintaba en el tablero. De ahi este else sin sentido.
                        tableroJ1[filaJ1 - 1][col + x] = actual.Simbolo;
                    }
                    mostrarTablero();
                }
            }
        }
    }

    

    public boolean compruebaLimites(Barco b) {
        boolean valido = false;
        int col;
        try {
            if(this.dir=='H'){
                col = traducirColumna(columnaJ1);
                if (col + b.getTamaño() > 10) {
                    throw new FueraLimites("El tamaño del barco sobrepasa los limites del tablero.Ingresa otra posicion");
                } else {
                    valido = true;
                }
            }else{
                if ((filaJ1-1) + b.getTamaño() > 10) {
                    throw new FueraLimites("El tamaño del barco sobrepasa los limites del tablero.Ingresa otra posicion");
                } else {
                    valido = true;
                }
            }
        } catch (FueraLimites e) {
            System.out.println(e.getMessage());
        }
        return valido;
    }

    private int traducirColumnaDisparo() {
        return traducirColumna(columnaCharDisparoJ1);
    }

    // Método que permite al J1 diaparar a la IA
    public void realizarDisparo(IA maquina) {
        boolean valido = false;
        do {
            try {
                datos = new Scanner(System.in);
                System.out.println("Ingrese la fila para disparar (1-10):");
                filaDisparoJ1 = datos.nextInt();
                if (filaDisparoJ1 < 1 || filaDisparoJ1 > 10) {
                    System.out.println("La fila debe estar comprendida entre el 1 y el 10. Introduzca los datos de nuevo");
                } else {
                    valido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número");
            }
        } while (valido == false);

        valido = false;
        do {
            datos = new Scanner(System.in);
            System.out.println("Ingrese la columna a la que quiere disparar (A-J):");
            columnaCharDisparoJ1 = datos.next().toUpperCase().charAt(0);
            if (columnaCharDisparoJ1 != 'A' && columnaCharDisparoJ1 != 'B' && columnaCharDisparoJ1 != 'C' && columnaCharDisparoJ1 != 'D' && columnaCharDisparoJ1 != 'E'
                    && columnaCharDisparoJ1 != 'F' && columnaCharDisparoJ1 != 'G' && columnaCharDisparoJ1 != 'H' && columnaCharDisparoJ1 != 'I' && columnaCharDisparoJ1 != 'J') {
                System.out.println("La columna debe estar comprendida entre la A y la J. Introduzca el dato otra vez.");
            } else {
                valido = true;
            }
        } while (valido == false);

        columnaDisparoJ1 = traducirColumnaDisparo();
        // -1 porque el array bidimensional va de 0 a 9
        filaDisparoJ1 = filaDisparoJ1 - 1;

        // Verifica si el disparo le dio a un barco de la IA
        if (maquina.tableroIA[filaDisparoJ1][columnaDisparoJ1] != '*') {
            System.out.println("¡TOCADO! Has dado a un barco de la IA");
            char simb = maquina.getTableroIA()[filaDisparoJ1][columnaDisparoJ1];
            maquina.tableroIA[filaDisparoJ1][columnaDisparoJ1] = 'X'; // Marca el acierto en el tab de la IA 
            maquina.registrarImpactoBarcoIA(simb);
        } else {
            System.out.println("AGUA... ");
            maquina.tableroIA[filaDisparoJ1][columnaDisparoJ1] = 'O'; //Marca el fallo en el tab de la IA
        }
        System.out.println("-------------------------");
        System.out.println("        <Tablero IA>      ");
        maquina.mostrarTableroIA();
        System.out.println("-------------------------");
    }

    // Método para registrar un impacto en un barco de J1
    public void registrarImpactoBarcoJ1(char simbolo) {
        for (Barco barco : barcosJ1) {
            if (barco.getSimbolo() == simbolo) {
                registrarImpacto(barco.getSimbolo());
                break;
            }
        }
    }

    // Método para registrar un impacto
    public void registrarImpacto(char simbolo) {
        char simb = simbolo;
        if (simbolo == 'V') {
            this.vidasJ1[0]--;
            System.out.println("¡TOCADO Y HUNDIDO! La IA ha destruido uno de tus veleros");
        } else if (simbolo == 'F') {
            this.vidasJ1[1]--;
        } else if (simbolo == 'B') {
            this.vidasJ1[2]--;
        } else if (simbolo == 'P') {
            this.vidasJ1[3]--;
        }
        barcoDestruido(simb);
    }

    // verifica si el barco ha sido destruido
    public void barcoDestruido(char simb) {
        if (simb == 'F') {
            //Si es multiplo de dos, significa que es o 2 o 0, por tanto es que se ha destruido una fragata.
            if (vidasJ1[1] % 2 == 0) {
                System.out.println("¡TOCADO Y HUNDIDO! La IA ha destruido una de tus fragatas");
            }
        } else if (simb == 'B') {
            //Si es multiplo de 3, significa que es o 3 o 0, por tanto es que se ha destruido un buque.
            if (vidasJ1[2] % 3 == 0) {
                System.out.println(" ¡TOCADO Y HUNDIDO! La IA ha destruido uno de tus buques");
            }
        } else if (simb == 'P') {
            //Si es multiplo de 4, significa que es 0, por tanto es que se ha destruido el portaaviones.
            if (vidasJ1[3] % 4 == 0) {
                System.out.println(" ¡TOCADO Y HUNDIDO! La IA ha destruido tu portaaviones");
            }
        }
    }

    public boolean flotaJ1Destruida() {
        // Verificar si todas las vidas de los barcos del jugador son menores o iguales a 0
        return vidasJ1[0] <= 0 && vidasJ1[1] <= 0 && vidasJ1[2] <= 0 && vidasJ1[3] <= 0;
    }
}
