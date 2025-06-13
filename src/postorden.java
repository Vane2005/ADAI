import java.util.ArrayList;

public class postorden {

    
    public static ArrayList<nodoBinario> recorrerPostOrden(nodoBinario raiz) {
        ArrayList<nodoBinario> resultado = new ArrayList<>();
        postOrdenHelper(raiz, resultado);
        return resultado;
    }

    private static void postOrdenHelper(nodoBinario nodo, ArrayList<nodoBinario> resultado) {
        if (nodo != null) {
            postOrdenHelper(nodo.left, resultado);    
            postOrdenHelper(nodo.right, resultado);   
            resultado.add(nodo);                      
        }
    }
}
