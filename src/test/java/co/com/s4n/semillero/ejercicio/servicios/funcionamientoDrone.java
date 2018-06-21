package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;
import io.vavr.collection.Iterator;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static co.com.s4n.semillero.ejercicio.archivos.servicioArchivos.EscribirArchivo;
import static co.com.s4n.semillero.ejercicio.archivos.servicioArchivos.leerArchivo;
import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.girarDerecha;
import static org.junit.Assert.*;

import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.girarIzquierda;
import static co.com.s4n.semillero.ejercicio.dominio.servicios.servicioDron.haciaDelante;

public class funcionamientoDrone {


    static List<Drone> reporteEntregas = new ArrayList<>();

    public void obtenerRutas() {
        Try<String> validacion;
        List<String> listaRutasArchivo = leerArchivo();

        io.vavr.collection.List<String> listaRutas = io.vavr.collection.List.ofAll(listaRutasArchivo);

        Iterator<io.vavr.collection.List<String>> listaAgrupada = dividirLista(listaRutas,3);

        //recorrer la lista agrupada
        listaAgrupada.forEach(s -> {
            //cada elemento(s) es una lista- agrupada de a 3 rutas

            Drone drone = new Drone(0, 0, Direccion.NORTE);
            //obtener el dron en String
            String inicio = drone.toString();

            //Fold - > s1:posicion:que parte desde el inicio , s2:ruta(cada elemento de la listaAgrupada)

            //fold acumula las posiciones del drone
            s.fold(inicio, (s1, s2) -> convertirDrone(s1, s2));

        });
        validacion = revisarReporte(reporteEntregas);
        if(validacion.isSuccess()){
            EscribirArchivo(reporteEntregas);
        }else{
            System.out.println(validacion);
        }

    }

    public static Iterator<io.vavr.collection.List<String>> dividirLista(io.vavr.collection.List<String> listGeneral, int tamano){
        return listGeneral.grouped(tamano);
    }

    public static String convertirDrone(String posicionActual, String ruta) {
        String[] elementos = posicionActual.split("\\,");
        Drone drone = new Drone(Integer.parseInt(elementos[0]), Integer.parseInt(elementos[1]), cambiarDireccion(elementos[2]));
        //generar la nueva posicion del drone
        Drone droneNuevo = generarPosicion(drone, ruta);
        return droneNuevo.toString();
    }

    public static Direccion cambiarDireccion(String direccion) {
        Direccion direccionNueva = Direccion.NORTE;
        switch (direccion) {
            case "NORTE":
                direccionNueva = Direccion.NORTE;
                break;
            case "SUR":
                direccionNueva = Direccion.SUR;
                break;
            case "OESTE":
                direccionNueva = Direccion.OESTE;
                break;
            case "ESTE":
                direccionNueva = Direccion.ESTE;
                break;
        }
        return direccionNueva;
    }


    public static Drone generarPosicion(Drone dron, String ruta) {

        int x = dron.getX();
        int y = dron.getY();
        Direccion direccion = dron.getDireccion();

        Drone drone = new Drone(x, y, direccion);

        for (int i = 0; i < ruta.length(); i++) {
            String letra = String.valueOf(ruta.charAt(i));
            if (letra.equals("A")) {
                drone = haciaDelante(drone);
            } else if (letra.equals("I")) {
                drone = girarIzquierda(drone);
            } else if (letra.equals("D")) {
                drone = girarDerecha(drone);
            } else {
                drone = new Drone(0,0,Direccion.NORTE);
            }
        }
        reporteEntregas.add(drone);
        return drone;
    }

    public Try<String> revisarReporte(List<Drone> reporte){
        Try<String> error = Try.of(()->"ok");
        for (int i = 0;i<reporte.size();i++){
            if (reporte.get(i).getX()<-10 || reporte.get(i).getX()>10){
                error = Try.failure(new Exception("FUERA DE LOS LIMITES DEL BARRIO"));
            }
            if (reporte.get(i).getY()<-10 || reporte.get(i).getY()>10){
                error = Try.failure(new Exception("FUERA DE LOS LIMITES DEL BARRIO"));
            }
        }
        return error;
    }

    @Test
    public void testFuncionamiento() {
        Drone drone;
        String esperado = "(0,0) OESTE";

        obtenerRutas();

        for (int i = 0; i < reporteEntregas.size(); i++) {

             System.out.println(reporteEntregas.get(i));
        }

        drone = reporteEntregas.get(2);

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);
    }

    @Test
    public void testFuncionamiento2() {

        Drone drone;
        String esperado = "(4,0) ESTE";

        obtenerRutas();

        for (int i = 0; i < reporteEntregas.size(); i++) {

            System.out.println(reporteEntregas.get(i));
        }
        drone = reporteEntregas.get(6);

        String actual = "("+drone.getX()+","+drone.getY()+") "+drone.getDireccion();
        assertEquals(esperado,actual);
    }
}