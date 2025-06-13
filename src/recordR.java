import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
}
