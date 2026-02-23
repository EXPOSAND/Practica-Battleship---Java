package Practica2T;

public class Barco{

    protected String Nombre;
    protected char Simbolo;
    protected int tamaño;
    protected int vidas;
    protected char orientacion;

    public Barco(String nombre, char Simbolo, int tamaño, int vidas,char or) {
        this.Nombre=nombre;
        this.Simbolo=Simbolo;
        this.tamaño=tamaño;
        this.vidas=vidas;
        this.orientacion=or;
    }

    public String getNombre() {
        return Nombre;
    }

    public char getSimbolo() {
        return Simbolo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public int getCantidad() {
        return vidas;
    }

    public char getOrientacion() {
        return orientacion;
    }
    
    //Me permitira cambiar luego la orientacion en caso de que no sea horizontal
    public void setOrientacion(char orientacion) {
        this.orientacion = orientacion;
    } 
}
