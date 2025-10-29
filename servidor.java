import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {
  public static void main(String [] args) {
    int puerto = 4000;
    try {
      //Crear un socket del servidor
      ServerSocket socketServidor = new ServerSocket(puerto);
      System.out.println("Esperando conexiones...");

      while (true) {
        //Aceptar conexiones de los clientes
        Socket socketCliente = socketServidor.accept();
        System.out.println("Cliente conectado: " + socketCliente.getInetAddress().getHostAddress());

        //Crear un hilo para manejar la conexion con el cliente
        HilosCliente hiloCliente = new HilosCliente(socketCliente);
        hiloCliente.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

//PARA QUE NO SE CIERRE, DEBEMOS MODIFICAR EL SERVIDOR PARA QUE ACEPTE MULTIPLES CONEXIONES (se quede escuchando)