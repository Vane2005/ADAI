import java.util.ArrayList;

public class preorden {

   
    public static ArrayList<nodoBinario> recorrerPreOrden(nodoBinario raiz) {
        ArrayList<nodoBinario> resultado = new ArrayList<>();
        preOrdenHelper(raiz, resultado);
        return resultado;
    }

    
    private static void preOrdenHelper(nodoBinario nodo, ArrayList<nodoBinario> resultado) {
        if (nodo != null) {
            resultado.add(nodo);                      
            preOrdenHelper(nodo.left, resultado);     
            preOrdenHelper(nodo.right, resultado);    
        }
    }
}

