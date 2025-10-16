package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class App {

    public static final String CONTENIDO = """
            Me gusta PSP y java
            PSP se programa en java
            es un modulo de DAM
            y se programa de forma concurrente en PSP
            PSP es programacion.""";

    public static final String[] COMANDO = { "grep", "PSP" };
    public static final String RESULTADO = "Lineas que contienen PSP : \n";
    public static final String MSG_ERROR = "A ocurrido un error con el proceso";

    public static void main(String[] args) throws Exception {

        Process p = lanzarProceso(COMANDO);
        escribir(p, CONTENIDO);
        if (p.waitFor() != 0) {
            System.out.println(MSG_ERROR);
        } else {
            System.out.println(RESULTADO + leer(p));
        }

    }

    public static Process lanzarProceso(String[] comando) throws Exception {
        return Runtime.getRuntime().exec(comando);
    }

    // Escribir en el OutputStream
    public static void escribir(Process p, String contenido) throws Exception {
        OutputStream out = p.getOutputStream();
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(out))) {
            pw.println(contenido);
        } catch (Exception e) {
            throw e;
        }
    }

    // Leer el InputStream
    public static String leer(Process p) throws Exception {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}