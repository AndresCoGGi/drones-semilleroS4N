package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron;
import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "co.com.s4n.tain.java.servicioDOminio")

@Mock
ServicioDominioc servicio*/

public class DroneSuite {

    @Test
    public void testHaciaDelante(){
        Drone drone =  new Drone(2,4,Direccion.NORTE);
        drone = servicioDron.haciaDelante(drone);

        String esperado = "(2,5) NORTE";

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }

    @Test
    public void testHaciaDelante2(){
        Drone drone =  new Drone(-1,-2,Direccion.SUR);
        drone = servicioDron.haciaDelante(drone);

        String esperado = "(-1,-3) SUR";

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }

    @Test
    public void testHaciaDelante3(){
        Drone drone =  new Drone(0,0,Direccion.OESTE);
        drone = servicioDron.haciaDelante(drone);

        String esperado = "(-1,0) OESTE";

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }


    @Test
    public void testgirarIzquierda(){
        Drone drone =  new Drone(0,0,Direccion.NORTE);
        drone = servicioDron.girarIzquierda(drone);

        String esperado = "(0,0) OESTE";

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }

    @Test
    public void testgirarDerecha(){
        Drone drone =  new Drone(0,0,Direccion.OESTE);
        drone = servicioDron.girarDerecha(drone);

        String esperado = "(0,0) NORTE";

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);


    }

}
