import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
    
        nodoBinario root = new nodoBinario(15);
        nodoBinario node27 = new nodoBinario(27);
        nodoBinario node17 = new nodoBinario(17);
        nodoBinario node25 = new nodoBinario(25);
        nodoBinario node30 = new nodoBinario(30);
        nodoBinario node14 = new nodoBinario(14);
        nodoBinario node26 = new nodoBinario(26);
        nodoBinario node16 = new nodoBinario(16);
        nodoBinario node34 = new nodoBinario(34);
        nodoBinario node32 = new nodoBinario(32);
        nodoBinario node24 = new nodoBinario(24);
        //nodoBinario node31 = new nodoBinario(31);

        root.insertar(node17);
        root.insertar(node25);
        root.insertar(node27);
        root.insertar(node30);
        root.insertar(node34);
        root.insertar(node32);
        root.insertar(node24);
        root.insertar(node14);
        root.insertar(node16);
        root.insertar(node26);
        
        System.out.println("Raiz: " + root.key);
        System.out.println("Hijo izquierdo: " + root.left.key);
        System.out.println("hijo izquierdo del hijo izquierdo: " + root.left.left.key);
        System.out.println("hijo derecho del hijo izquierdo: " + root.left.right.key);
        System.out.println("Hijo derecho: " + root.right.key);
        System.out.println("hijo izquierdo del hijo derecho: " + root.right.left.key);
        System.out.println("hijo izquierdo del hijo izquierdo del derecho: " + root.right.left.left.key);
        System.out.println("hijo derecho del hijo izquierdo del derecho: " + root.right.left.right.key);
        System.out.println("hijo derecho del hijo derecho: " + root.right.right.key);
        System.out.println("hijo izquierdo del hijo derecho del derecho: " + root.right.right.left.key);
        System.out.println("hijo derecho del hijo derecho del derecho: " + root.right.right.right.key);
        System.out.println("COLORES");
        System.out.println("Raiz: " + root.color);
        System.out.println("Hijo izquierdo: " + root.left.color);
        System.out.println("hijo izquierdo del hijo izquierdo: " + root.left.left.color);
        System.out.println("hijo derecho del hijo izquierdo: " + root.left.right.color);
        System.out.println("Hijo derecho: " + root.right.color);
        System.out.println("hijo izquierdo del hijo derecho: " + root.right.left.color);
        System.out.println("hijo izquierdo del hijo izquierdo del derecho: " + root.right.left.left.color);
        System.out.println("hijo derecho del hijo izquierdo del derecho: " + root.right.left.right.color);
        System.out.println("papa del izquierdo del hijo izquierdo del derecho:" + root.right.left.left.parent.key + " de color: " + root.right.left.left.parent.color);
        System.out.println("hijo derecho del hijo derecho: " + root.right.right.color);
        System.out.println("hijo izquierdo del hijo derecho del derecho: " + root.right.right.left.color);
        System.out.println("hijo derecho del hijo derecho del derecho: " + root.right.right.right.color);


        ArrayList<nodoBinario> nodosInOrder = inorder.recorrerInOrder(root);
        System.out.println("Recorrido In-Order:");
        for (nodoBinario nodo : nodosInOrder) {
        System.out.println("Clave: " + nodo.key + ", Color: " + nodo.color);
        }

        ArrayList<nodoBinario> nodosPostOrden = postorden.recorrerPostOrden(root);
        System.out.println("Recorrido Post-Orden:");
        for (nodoBinario nodo : nodosPostOrden) {
        System.out.println("Clave: " + nodo.key + ", Color: " + nodo.color);
        }

        ArrayList<nodoBinario> nodosPreOrden = preorden.recorrerPreOrden(root);
        System.out.println("Recorrido Pre-Orden:");
        for (nodoBinario nodo : nodosPreOrden) {
        System.out.println("Clave: " + nodo.key + ", Color: " + nodo.color);
        }
        
        // Para cargar los registros desde un archivo y mostrarlos en diferentes recorridos
        String rutaArchivo = "files/registros.txt";
        nodoBinario raiz = recordR.cargarDesdeArchivo(rutaArchivo); // Se carga el árbol desde el archivo

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("\nSeleccione el tipo de recorrido en que desea ver los registros:");
        System.out.println("1. In-Order");
        System.out.println("2. Pre-Order");
        System.out.println("3. Post-Order");
        System.out.print("Opción: ");
        byte opcion = 1; // Valor por defecto
        try {
            opcion = (byte) scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada no válida. Se usará In-Order por defecto.");
            scanner.nextLine();
        }

        ArrayList<nodoBinario> lista = null;
        switch (opcion) {
            case 1:
                lista = inorder.recorrerInOrder(raiz);
                break;
            case 2:
                lista = preorden.recorrerPreOrden(raiz);
                break;
            case 3:
                lista = postorden.recorrerPostOrden(raiz);
                break;
            default:
                System.out.println("Opción no válida. Se usará In-Order por defecto.");
                lista = inorder.recorrerInOrder(raiz);
                break;
        }

        System.out.println("\n");
        for (nodoBinario nodo : lista) {
            System.out.println("ID: " + nodo.key + ", Nombre: " + nodo.name + ", Edad: " + nodo.age + ", Tel: " + nodo.phone);
        }
        scanner.close();
    }
}