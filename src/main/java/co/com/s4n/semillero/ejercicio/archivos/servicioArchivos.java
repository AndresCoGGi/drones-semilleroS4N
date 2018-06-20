package co.com.s4n.semillero.ejercicio.archivos;

import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class servicioArchivos {

    public static List<String> leerArchivo(){

        String fileName="/home/s4n/domicilios-drones/src/main/resource/int.txt";

        Try<Stream<String>> rutas = Try.of(() -> Files.lines(Paths.get(fileName)));

        List<String> listaNueva = Arrays.asList();

        if(rutas.isSuccess()){
              listaNueva = rutas.get().collect(Collectors.toList());
        }else{
            System.out.printf("Error");
        }
        return listaNueva;
    }

    public static void EscribirArchivo(){



    }
}
