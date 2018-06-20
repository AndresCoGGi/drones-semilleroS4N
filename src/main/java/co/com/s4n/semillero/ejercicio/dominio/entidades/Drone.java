package co.com.s4n.semillero.ejercicio.dominio.entidades;

import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;

public class Drone {

    int x;
    int y;
    Direccion direccion;

    public Drone(int x, int y, Direccion direccion){
        this.x = x;
        this.y = y;
        this.direccion = direccion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
