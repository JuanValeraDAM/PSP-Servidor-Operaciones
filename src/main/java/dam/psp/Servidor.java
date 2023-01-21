package dam.psp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Desarrolla una aplicación cliente/servidor que implemente un servicio de operaciones
matemáticas, con el siguiente servicio funcionando de manera continua:
 Aceptará conexiones por un puerto TCP de tu elección.
 El servidor mantendrá en memoria un contador de cuántos clientes ha atendido en
total. Tras conectarse un cliente, este contador se incrementará y se enviará al cliente,
quien lo visualizará (algo como “Eres el cliente número X”).
 Entonces, el cliente enviará un paquete indicando cuántos números desea procesar.
 Seguidamente, el cliente enviará tantos paquetes como haya indicado en el paso
anterior, conteniendo cada uno un número entero. Estos números, así como su
cantidad, se pedirán por teclado.
 El servidor calculará la suma y el producto de todos esos números, y enviará estos dos
resultados como respuesta, en dos paquetes diferentes. El cliente mostrará estos
resultados, y tras esto, cliente y servidor cerrarán la conexión.
 El cliente terminará su ejecución en cuanto cierre su conexión. El servidor siempre
estará activo para atender a más clientes, incluso a la vez.

 */
public class Servidor {
    private static int contador = 0;

    public static void main(String[] args) throws IOException {

        ExecutorService pool = Executors.newFixedThreadPool(8);
        ServerSocket serverSocket = new ServerSocket(7000);
        while (true) {
            Socket socketConectado = serverSocket.accept();
            contador++;
            Encargado encargado = new Encargado(socketConectado,contador);
            pool.submit(encargado);
        }

    }
}