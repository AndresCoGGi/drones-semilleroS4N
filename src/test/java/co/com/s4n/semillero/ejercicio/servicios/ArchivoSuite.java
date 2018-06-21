package co.com.s4n.semillero.ejercicio.servicios;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import co.com.s4n.semillero.ejercicio.dominio.valoresObjeto.Direccion;
import io.vavr.control.Try;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static co.com.s4n.semillero.ejercicio.archivos.servicioArchivos.EscribirArchivo;
import static co.com.s4n.semillero.ejercicio.archivos.servicioArchivos.leerArchivo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArchivoSuite {

  @Test
  public void testLeerArchivo(){
      List<String> listaRutas = leerArchivo();
      List<String> esperada = Arrays.asList("AAAAIAAD","DDAIAD","AAIADAD","AADAIIA","DDAAA");
      assertEquals(esperada,listaRutas);
  }

  @Test
  public void testEscribirArchivo(){
      List<Drone> reporteEntregas = Arrays.asList(new Drone(0,0,Direccion.NORTE),
                                                    new Drone(2,4,Direccion.SUR),
                                                        new Drone(3,8,Direccion.ESTE));
      Try<String> mensaje = EscribirArchivo(reporteEntregas);

      assertTrue(mensaje.isSuccess());

  }



}
