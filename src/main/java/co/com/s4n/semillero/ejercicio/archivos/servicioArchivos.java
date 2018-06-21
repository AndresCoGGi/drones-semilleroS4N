package co.com.s4n.semillero.ejercicio.archivos;

import co.com.s4n.semillero.ejercicio.dominio.entidades.Drone;
import io.vavr.control.Try;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class servicioArchivos {

    public static List<String> leerArchivo(){

        String fileName="/home/s4n/domicilios-drones/src/main/resource/int";

        Try<Stream<String>> rutas = Try.of(() -> Files.lines(Paths.get(fileName)));

        List<String> listaNueva = Arrays.asList();

        if(rutas.isSuccess()){
              listaNueva = rutas.get().collect(Collectors.toList());
        }else{
            System.out.printf("Error");
        }
        return listaNueva;
    }

    public static Try<String> EscribirArchivo(List<Drone> resporte){
        String mensaje="";

        String fileName="/home/s4n/domicilios-drones/src/main/resource/out";

        Try<String> escribir = Try.of(() -> {

            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int i=0; i<resporte.size(); i++){

                printWriter.println("("+resporte.get(i).getX()+", "+resporte.get(i).getY()+") " +
                        "direccion "+resporte.get(i).getDireccion());

            }
            printWriter.close();
            return "Creado";
        });
        return escribir;


    }
}
