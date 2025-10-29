
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HilosCliente2 extends Thread{

  private Socket socketCliente2;
  String [] preguntas = {
    "¿Cúanto es 2+2?",
    "¿Cúanto es 3x2?",
    "¿Cúanto es 12/2?",
    "Cual es la capital de Ecuador?",
    "¿Cúanto es 5-3?",
    "¿Cúanto es 9+1?",
    "¿Cúanto es 7x1?",
    "¿Cúanto es 8/2?"
  };
  String [] respuestas = {
    "4",
    "6",
    "6",
    "Quito",
    "2",
    "10",
    "7",
    "4"
  };

  public HilosCliente2(Socket socketCliente2) {
    this.socketCliente2 = socketCliente2;
  }

  @Override
  public void run() {
    try {
      //Crear buffers para enviar y recibir datos
      BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente2.getInputStream())); //en este buffer se almacena todo lo que el cliente envie
      PrintWriter salida = new PrintWriter(socketCliente2.getOutputStream(), true); //en este buffer se almacena todo lo que se le envie al cliente
      String respuesta = ""; //vacio para que en la primera iteracion del for no concatene nada
      int puntaje = 0;

      while (true) {
        
        for(int i = 0; i < preguntas.length; i++) {
          //Enviar datos
          String datosEnviar = preguntas[i]; //Envio la pregunta
          salida.println(respuesta+ " "+datosEnviar); //dado que la inicializamos en vacio a la variable respuesta, en la primera vuelta no concatena nada 

          //Recibir datos
          String datosRecibidos = entrada.readLine(); //recibo su respuesta
          System.out.println(datosRecibidos); 

          //entonces comparo una por una
          if (datosRecibidos.equalsIgnoreCase(respuestas[i])) {
            respuesta = "Correcto";
            puntaje++;
          } else {
            respuesta = "Incorrecto";
          }
        }
        salida.println("fin del cuestionario2, tu puntaje es: " + puntaje + "/" + preguntas.length);
        System.out.println("Cliente desconectado: " + socketCliente2.getInetAddress().getHostAddress());
        socketCliente2.close();
        break; // Salir del while después de cerrar el socket
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
