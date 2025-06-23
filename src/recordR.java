import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class recordR {

    // Método estático que carga los datos desde un archivo y devuelve la raíz del árbol
    public static nodoBinario cargarDesdeArchivo(String rutaArchivo) {
        nodoBinario raiz = null; // Inicializa la raíz del árbol como nula

       
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {  // Se intenta abrir y leer el archivo línea por línea
            String linea;
            // Lee cada línea del archivo
            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                // Se extrae y convierte los datos de la línea a sus tipos correspondientes
                float key = Float.parseFloat(datos[0]);
                String name = datos[1];
                byte age = Byte.parseByte(datos[2]);
                String phone = datos[3];

                // Se crea un nuevo nodo con los datos extraídos
                nodoBinario nuevoNodo = new nodoBinario(key);
                nuevoNodo.name = name;
                nuevoNodo.age = age;
                nuevoNodo.phone = phone;

                // Si la raíz es nula, el nuevo nodo se convierte en la raíz
                if (raiz == null) {
                    raiz = nuevoNodo;
                    raiz.color = "black"; // La raíz debe comenzar negra
                } else {
                    raiz.insertar(nuevoNodo); // Si ya existe una raíz, se inserta el nuevo nodo en el árbol
                }
            }

                } catch (java.io.FileNotFoundException e) {
                    System.out.println("Archivo no encontrado: " + e.getMessage());
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }

        return raiz; // Devuelve la raíz del árbol construido
    }

    public static void appendNodo(String rutaArchivo, nodoBinario n) {
        try (BufferedWriter bw = new BufferedWriter(
                 new FileWriter(rutaArchivo, true))) {

            String linea = "\n" + n.key + "," + n.name + "," + n.age + "," + n.phone;
            bw.write(linea);
        } catch (IOException e) {
            System.err.println("Error escribiendo registro: " + e.getMessage());
        }
    }

    public static boolean deleteByKey(float key) {

        Path path = Paths.get("files/registros2500_atras.txt");
        List<String> restantes = new ArrayList<>();
        boolean eliminado = false;

        try {
            for (String linea : Files.readAllLines(path, StandardCharsets.UTF_8)) {

                String[] datos = linea.split(",", 2);   // dividimos solo una vez
                if (Float.parseFloat(datos[0]) == key) {
                    eliminado = true;                   // ──> marcamos como borrada
                } else {
                    restantes.add(linea);               // mantenemos la línea
                }
            }

            if (eliminado) {
                /*Construimos un único String SIN salto tras la última línea */
                String salida = String.join(System.lineSeparator(), restantes);
                Files.write(path,
                            salida.getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.TRUNCATE_EXISTING,
                            StandardOpenOption.WRITE);
            
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al eliminar registro: " + e.getMessage());
        }

        

        return eliminado;
    }

}
