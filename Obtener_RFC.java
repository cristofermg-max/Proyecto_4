import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Cliente {
    private String[] rfcGenerados;

    public void obtenerRFC() {
        String archivo = "./Archivo/Nombres.txt";

        
        int totalLineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while (br.readLine() != null) {
                totalLineas++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar líneas: " + e.getMessage());
            return;
        }

        
        rfcGenerados = new String[totalLineas];

        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String fila;
            int index = 0;
            while ((fila = br.readLine()) != null) {
                String[] rfc = fila.split(" ");

                String f = rfc[3];
                String[] f2 = f.split("/");
                String a = f2[2].substring(2);
                String m = f2[1];
                String d = f2[0];
                String n = rfc[6].substring(0, 1);
                String ap = rfc[7];
                String letraP = ap.substring(0, 1);
                char vocal = 'X';
                for (char letra : ap.toCharArray()) {
                    if (String.valueOf(letra).matches("[AEIOUaeiou]")) {
                        vocal = letra;
                        break;
                    }
                }
                String am = rfc[8].substring(0, 1);

                //HOMOCLAVE
                String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder homoclave = new StringBuilder();
                Random random = new Random();

                for (int i = 0; i < 3; i++) {
                    int idx = random.nextInt(caracteres.length());
                    homoclave.append(caracteres.charAt(idx));
                }

                String rfcCompleto = (letraP + vocal + am + n + a + m + d + homoclave.toString()).toUpperCase();

                
                rfcGenerados[index] = rfcCompleto;
                index++;

                
                //System.out.println(rfcCompleto);
            }

        } catch (IOException e) {
            System.err.println("Error al leer archivo: ");
        }
    }

    public String[] getRFCs() {
        return rfcGenerados;
    }

    public String cargarDatos(String letra) {
        if (rfcGenerados == null) 
        	return null;
        for (String rfc : rfcGenerados) {
            if (rfc != null) {
                return rfc;
            }
        }
        return null;
    }
}

public class Obtener_RFC {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.obtenerRFC();

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println("1) Buscar RFCs");
            System.out.println("2) Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese letra:");
                    String letra = scanner.next();
                    String resultado = cliente.cargarDatos(letra);
                    if (resultado != null) {
                        System.out.println("RFC encontrado: " + resultado);
                    } else {
                        System.out.println("No se encontró ningún RFC que comience con esa letra.");
                    }
                    break;
                }
            }
        } while (opcion != 2);

        System.out.println("Saliendo...");
	System.out.println("GIT");

    }
}



