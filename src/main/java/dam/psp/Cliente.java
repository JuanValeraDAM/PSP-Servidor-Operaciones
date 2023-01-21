package dam.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 7000);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());

        String bienvenida = br.readLine();
        System.out.println(bienvenida);

        System.out.println("¿Cuántos números deseas enviar?");
        String cantidadPaquetes = sc.next();
        pw.println(cantidadPaquetes);
        pw.flush();

        for (int i = 0; i < Integer.parseInt(cantidadPaquetes); i++) {
            System.out.println("Introduce el siguiente número: ");
            String numero=sc.next();
            pw.println(String.format("%s",numero));
            pw.flush();
        }
        String suma = br.readLine();
        String producto=br.readLine();

        System.out.println("La suma de los números es "+suma);
        System.out.println("El producto de los números es: "+producto);

        try{
            socket.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }


    }
}
