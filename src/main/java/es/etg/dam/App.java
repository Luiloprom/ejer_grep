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

    public static final String COMANDO = "grep PSP";
    public static final String RESULTADO = "Lineas que contienen PSP : ";

    public static void main(String[] args) throws Exception {

        Process p = lanzarProceso(COMANDO);
        escribir(p, CONTENIDO);
        leer(p);
        p.waitFor();

    }

    public static Process lanzarProceso(String comando) throws Exception {
        return Runtime.getRuntime().exec(comando);
    }

    public static void escribir(Process p, String contenido) throws Exception {
        OutputStream out = p.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        pw.println(contenido);
        pw.close();
    }

    public static void leer(Process p) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String linea;
        System.out.println(RESULTADO);
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        br.close();
    }
}