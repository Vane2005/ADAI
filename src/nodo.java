public class nodo {

    // Atributos de la clase nodo
    float key;
    nodo left;
    nodo right;
    nodo parent;
    String color;
    String name;
    int age;
    String phone;

    // Constructor de la clase nodo
    public nodo(float key, String name, int age, String phone) {
        this.key = key;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = "red"; // Color por defecto al ser el primer nodo insertado
    }

    // Constructor por defecto
    public nodo() {
    }


    // Método para insertar un nuevo nodo en el árbol



    // Método para eliminar un nodo en el árbol



    // Método para buscar un nodo en el árbol



    // Método para buscar el mayor nodo en el árbol



    // Método para buscar el menor nodo en el árbol


    
}
