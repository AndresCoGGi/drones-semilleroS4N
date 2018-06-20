package co.com.s4n.semillero.ejercicio.dominio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;


public class servicioDron {


    public static Drone haciaDelante(Drone drone){
       Drone droneNuevo;
       int x = drone.getX();
       int y = drone.getY();
       Direccion direccion = drone.getDireccion();
       if (direccion.equals(Direccion.NORTE)){
           y = y+1;
       }else if(direccion.equals(Direccion.SUR)){
           y = y -1;
       }else if(direccion.equals(Direccion.OESTE)){
           x = x -1;
       }else if(direccion.equals(Direccion.ESTE)){
           x = x +1;
       }
       droneNuevo = new Drone(x,y,direccion);
       return droneNuevo;
    }

    public static Drone girarIzquierda(Drone drone){
        Drone droneNuevo;
        Direccion direccion = drone.getDireccion();

        if (direccion.equals(Direccion.NORTE)){
            direccion = Direccion.OESTE;
        }else if(direccion.equals(Direccion.SUR)){
            direccion = Direccion.ESTE;
        }else if(direccion.equals(Direccion.OESTE)){
            direccion = Direccion.SUR;
        }else if(direccion.equals(Direccion.ESTE)){
            direccion = Direccion.NORTE;
        }
        droneNuevo = new Drone(drone.getX(), drone.getY(),direccion);
        return droneNuevo;
    }

    public static Drone girarDerecha(Drone drone){
        Drone droneNuevo;
        Direccion direccion = drone.getDireccion();

        if (direccion.equals(Direccion.NORTE)){
            direccion = Direccion.ESTE;
        }else if(direccion.equals(Direccion.SUR)){
            direccion = Direccion.OESTE;
        }else if(direccion.equals(Direccion.OESTE)){
            direccion = Direccion.NORTE;
        }else if(direccion.equals(Direccion.ESTE)){
            direccion = Direccion.SUR;
        }
        droneNuevo = new Drone(drone.getX(), drone.getY(),direccion);
        return droneNuevo;
    }
}
