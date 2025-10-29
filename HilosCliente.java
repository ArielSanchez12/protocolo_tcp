
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HilosCliente extends Thread{

  private Socket socketCliente;

  public HilosCliente(Socket socketCliente) {
    this.socketCliente = socketCliente;
  }

  @Override
  public void run() {
    try {
      //Crear buffers para enviar y recibir datos
      BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream())); //en este buffer se almacena todo lo que el cliente envie
      PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true); //en este buffer se almacena todo lo que se le envie al cliente
      String respuesta;
      int puntaje = 0;

      while (true) {
        //Enviar datos
        String datosEnviar = "Pregunta 1: ¿Cúanto es 2+2?";
        salida.println(datosEnviar); //con esto guardamos el mensaje en el buffer de salida

        //Recibir datos
        String datosRecibidos = entrada.readLine(); //con esto leemos el mensaje que el cliente envio y lo guardamos en la variable datosRecibidos
        System.out.println(datosRecibidos); //aca imprimimos para poder ver lo que el cliente envio

        if (datosRecibidos.equalsIgnoreCase("4")) {
          respuesta = "Correcto";
          puntaje++;
        }else{
          respuesta = "Incorrecto";
        }

        salida.println(respuesta + ": pregunta 2: ¿Cúanto es 3x2?"); //enviamos la respuesta al cliente
        datosRecibidos = entrada.readLine();
        System.out.println(datosRecibidos);
        if (datosRecibidos.equalsIgnoreCase("6")) {
          respuesta = "Correcto";
          puntaje++;
        } else {
          respuesta = "Incorrecto";
        }
        salida.println(respuesta + ": pregunta 3: ¿Cúanto es 12/2?");

        datosRecibidos = entrada.readLine();
        System.out.println(datosRecibidos);
        if (datosRecibidos.equalsIgnoreCase("6")) {
          respuesta = "Correcto";
          puntaje++;
        } else {
          respuesta = "Incorrecto";
        }
        salida.println(respuesta + ": fin del cuestionario, tu puntaje es: " + puntaje + "/3");
        break;
      }
      System.out.println("Cliente desconectado");
      socketCliente.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
