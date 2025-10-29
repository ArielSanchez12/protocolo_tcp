
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class  Cliente {
  public static void main(String[] args) {
    try {
      //Crear el socket para conectarse al servidor
      Socket socketCliente = new Socket("localhost", 4000);

      //Crear buffers para enviar y recibir datos
      BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
      Scanner scanner = new Scanner(System.in);

      //SI EN EL SERVIDOR PRIMERO SE ENVIAN DATOS, AQUI PRIMERO SE RECIBEN DATOS (o viceversa)
      while (true) {
        //Recibir datos
        String datosRecibidos = entrada.readLine();
        System.out.println(datosRecibidos);

        if (datosRecibidos.contains("fin del cuestionario")) {
          socketCliente.close();
          break;
          
        }

        //Enviar datos por teclado
        System.out.println("Ingrese un mensaje: ");
        String datosEnviar = scanner.nextLine();
        salida.println(datosEnviar); 
      }
      System.out.println("Saliendo del servidor...");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}