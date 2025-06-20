import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Para cargar los registros desde un archivo y mostrarlos en diferentes recorridos
        String rutaArchivo = "files/registros.txt";
        nodoBinario raiz = recordR.cargarDesdeArchivo(rutaArchivo); // Se carga el árbol desde el archivo
        SwingUtilities.invokeLater(() -> new UserManagerUI(raiz).setVisible(true));
    }
}