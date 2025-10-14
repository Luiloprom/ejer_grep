package es.etg.dam;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testLanzaProceso() throws Exception {
        Process p = App.lanzarProceso(App.COMANDO);
        assertNotNull(p);
    }

    @Test
    public void testEscribir() throws Exception {
        Process p = App.lanzarProceso("cat");
        App.escribir(p, App.CONTENIDO);

        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        assertEquals(App.CONTENIDO, br.readLine());
    }

    @Test
    public void testLeer() throws Exception {
        Process p = App.lanzarProceso(App.COMANDO);
        App.escribir(p, App.CONTENIDO);
        App.leer(p);
        p.waitFor();
        assertEquals(0, p.exitValue());
    }

}
