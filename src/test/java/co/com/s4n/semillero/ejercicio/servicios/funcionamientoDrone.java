package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.girarDerecha;
import static org.junit.Assert.*;

import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.girarIzquierda;
import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.haciaDelante;

public class funcionamientoDrone {


    List<Drone> resultados = new ArrayList<>();
    List<Drone> resultados2 = new ArrayList<>();
    int contador =0;



    public List<Drone> obtenerRutas(){

        List<String> listaRutas = Arrays.asList("AAAAIAAD","DDAIAD","AAIADAD");
        List<String> rutas = recogerAlmuerzos(listaRutas,contador);
        Future<Drone> futuro = Future.of( () -> new Drone(0,0,Direccion.NORTE));


        Future<Drone> finalRuta =  futuro
                .flatMap(a -> Future.of(() ->
                    generarPosicion(a,rutas.get(0)))
                         .flatMap(b -> Future.of(()->
                             generarPosicion(b,rutas.get(1)))
                                .flatMap(c -> Future.of(() ->
                                     generarPosicion(c,rutas.get(2))))));



        contador= contador+3;

        resultados.add(finalRuta.get());

        return resultados;

    }

    /*public void agrupar(){
        List<String> listaRutas = Arrays.asList("AAAAIAAD","DDAIAD","AAIADAD");
        int tamaño = listaRutas.

    }*/


    public Drone generarPosicion(Drone dron , String ruta){

        int x = dron.getX();
        int y = dron.getY();
        Direccion direccion = dron.getDireccion();

        Drone drone = new Drone(x,y,direccion);

        for (int i = 0; i<ruta.length();i++){
            String letra = String.valueOf(ruta.charAt(i));
            if(letra.equals("A")){
                drone = haciaDelante(drone);
            }else if(letra.equals("I")){
                drone = girarIzquierda(drone);
            }else if(letra.equals("D")){
                drone = girarDerecha(drone);
            }
        }
        resultados2.add(drone);

        return drone;
    }


    public List<String> recogerAlmuerzos(List<String> list ,int indice){

        List<String> rutas = new ArrayList<>();

        rutas.add(list.get(indice));
        rutas.add(list.get(indice+1));
        rutas.add(list.get(indice+2));

        return rutas;
    }

    @Test
    public void testFuncionamiento(){
        List<Drone> resultados = new ArrayList<>();

        Drone drone;
        String esperado = "(0,0) OESTE";

        resultados = obtenerRutas();
        drone = resultados.get(0);


        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);
    }
   /* @Test
    public void testFuncionamiento2(){
        List<Drone> resultados = new ArrayList<>();

        Drone drone;
        String esperado = "(-2,4) NORTE";
        drone = resultados2.get(0);


        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }
    @Test
    public void testFuncionamiento3(){
        List<Drone> resultados = new ArrayList<>();

        Drone drone;
        String esperado = "(-1,3) SUR";

        drone = resultados2.get(1);


        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);

    }*/
}
