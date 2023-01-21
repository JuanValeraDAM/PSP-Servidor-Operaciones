package dam.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Encargado implements Runnable {
    private final Socket socket;
    private final int contador;
    private int suma = 0;
    private int producto = 1;

    public Encargado(Socket socketConectado, int contador) {
        this.socket = socketConectado;
        this.contador = contador;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            pw.println(String.format("Muy buenas, eres el cliente numero: " + contador));
            pw.flush();


            int cantidadPaquetes = Integer.parseInt(br.readLine());


            for (int i = 0; i < cantidadPaquetes; i++) {
                int numero = Integer.parseInt(br.readLine());
                suma += numero;
                producto *= numero;
            }

            pw.println(String.format("%d", suma));
            pw.flush();
            pw.println(String.format("%d", producto));
            pw.flush();

            try{
                socket.close();
            }catch (IOException e){
                System.err.println(e.getMessage());
                System.err.println(e.getMessage());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
