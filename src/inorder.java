import java.util.ArrayList;

public class inorder {

    
    public static ArrayList<nodoBinario> recorrerInOrder(nodoBinario raiz) {
        ArrayList<nodoBinario> resultado = new ArrayList<>();
        inOrderHelper(raiz, resultado);
        return resultado;
    }

    
    private static void inOrderHelper(nodoBinario nodo, ArrayList<nodoBinario> resultado) {
        if (nodo != null) {
            inOrderHelper(nodo.left, resultado);     
            resultado.add(nodo);                     
            inOrderHelper(nodo.right, resultado);    
        }
    }
}
