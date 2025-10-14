# EJERCICIO GREP

## Índice

- [EJERCICIO GREP](#ejercicio-grep)
  - [Índice](#índice)
    - [EJERCICIO GREP](#ejercicio-grep-1)
    - [Test con JUnit](#test-con-junit)


### EJERCICIO GREP

En mi archivo **App.java** tengo tres métodos, más mi método main: 

- Método **lanzarProceso** :
    ```java
        public static Process lanzarProceso(String comando) throws Exception {
            return Runtime.getRuntime().exec(comando);
        } 
    ```

- Método **escribir** : 
    ```java
        public static void escribir(Process p, String contenido) throws Exception {
            OutputStream out = p.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
            pw.println(contenido);
            pw.close();
        }
    ```
    > Estariamos escribiendo en el output stream del programa padre.

- Método **leer** : 
    ```java
        public static String leer(Process p) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            br.close();
            return sb.toString();
        }
    ```
    > Estariamos leyendo el inputStream con el programa padre.

- Método **main** : 
    ```java
        public static void main(String[] args) throws Exception {
            Process p = lanzarProceso(COMANDO);
            escribir(p, CONTENIDO);
            System.out.println(RESULTADO + leer(p));
            p.waitFor();
        }
    ```

- Constantes : 
    ```java
        public static final String CONTENIDO = """
                Me gusta PSP y java
                PSP se programa en java
                es un modulo de DAM
                y se programa de forma concurrente en PSP
                PSP es programacion.""";

        public static final String COMANDO = "grep PSP";
        public static final String RESULTADO = "Lineas que contienen PSP : \n";
    ```
    > En CONTENIDO estoy haciendo un Text Block con las """.

---

### Test con JUnit

Con JUnit he testeado los métodos en **AppTest.java**. 

- **testLanzarProceso** : 
    ```java
        @Test
        public void testLanzarProceso() throws Exception {
            Process p = App.lanzarProceso(App.COMANDO);
            assertNotNull(p);
        }
    ```
    > Unicamente compruebo que al generar un proceso no es nulo.

- **testEscribir** : 
    ```java
        @Test
        public void testEscribir() throws Exception {
            Process p = App.lanzarProceso("cat");
            App.escribir(p, "Hola");

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            assertEquals("Hola", br.readLine());
        }
    ```
    > Aqui compruebo que escribe bien en el proceso haciendo un cat y leyendo con BufferedReader

- **testLeer** : 
    ```java
        @Test
        public void testLeer() throws Exception {
            Process p = App.lanzarProceso("echo hola buenas");
            assertEquals("hola buenas", App.leer(p).strip());
        }   
    ```
    > Aqui compruebo que lee bien haciendo un echo y comparando los resultados

