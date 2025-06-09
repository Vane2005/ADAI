public class App {
    public static void main(String[] args) throws Exception {
    
        nodoBinario root = new nodoBinario(15);
        nodoBinario node27 = new nodoBinario(27);
        nodoBinario node17 = new nodoBinario(17);
        nodoBinario node25 = new nodoBinario(25);
        nodoBinario node30 = new nodoBinario(30);
        nodoBinario node26 = new nodoBinario(26);
        nodoBinario node34 = new nodoBinario(34);
        nodoBinario node32 = new nodoBinario(32);
        //nodoBinario node31 = new nodoBinario(31);

        root.insertar(node17);
        root.insertar(node25);
        root.insertar(node27);
        root.insertar(node30);
        root.insertar(node34);
        root.insertar(node32);
        //root.insertar(node26);
        
        System.out.println("Raiz: " + root.key);
        System.out.println("Hijo izquierdo: " + root.left.key);
        System.out.println("Hijo derecho: " + root.right.key);
        System.out.println("hijo izquierdo del hijo derecho: " + root.right.left.key);
        System.out.println("hijo derecho del hijo derecho: " + root.right.right.key);
        System.out.println("hijo izquierdo del hijo derecho del derecho: " + root.right.right.left.key);
        System.out.println("hijo derecho del hijo derecho del derecho: " + root.right.right.right.key);
        System.out.println("COLORES");
        System.out.println("Raiz: " + root.color);
        System.out.println("Hijo izquierdo: " + root.left.color);
        System.out.println("Hijo derecho: " + root.right.color);
        System.out.println("hijo izquierdo del hijo derecho: " + root.right.left.color);
        System.out.println("hijo derecho del hijo derecho: " + root.right.right.color);
        System.out.println("hijo izquierdo del hijo derecho del derecho: " + root.right.right.left.color);
        System.out.println("hijo derecho del hijo derecho del derecho: " + root.right.right.right.color);

    }
}