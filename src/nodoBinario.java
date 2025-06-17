public class nodoBinario {

    // Atributos de la clase nodo
    float key;
    nodoBinario left;
    nodoBinario right;
    nodoBinario parent;
    String color;
    String name;
    int age;
    String phone;

    // Constructor de la clase nodo
    public nodoBinario(float key) {
        this.key = key;
        this.name = null;
        this.age = 0;
        this.phone = null;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = "red"; // Color por defecto al ser el primer nodo insertado
    }

    // Constructor por defecto
    public nodoBinario() {
    }


    // Método para insertar un nuevo nodo en el árbol

    // insercion binaria
    public void insercion(nodoBinario nuevoNodo){
        // Insertamos el nodo como un arbol binario normal
        if(nuevoNodo.key < this.key){
            if(this.left == null){
                this.left = nuevoNodo;
                nuevoNodo.parent = this;

            }
            else {
                this.left.insercion(nuevoNodo);
            }
        }
        else if(nuevoNodo.key > this.key){
            if(this.right == null){
                this.right = nuevoNodo;
                nuevoNodo.parent = this;
            }
            else{
                this.right.insercion(nuevoNodo);
            }
        }
        // Si el nodo ya existe, no hacemos nada
        else {
            System.out.println("El nodo con la clave " + nuevoNodo.key + " ya existe.");
        }
    }

    // Definimos las rotaciones necesarias para mantener las propiedades

    public void rotacionDerecha(nodoBinario nodo){

        if(nodo.parent != null){
            nodo.left.parent = nodo.parent;
            if(nodo.key < nodo.parent.key) {
                nodo.parent.left = nodo.left;
            }
            else {
                nodo.parent.right = nodo.left;
            }

            if(nodo.left.right != null) {
                nodo.left.right.parent = nodo;
                nodo.left = nodo.left.right;
            }
            nodo.left.right = nodo;
            nodo.parent = nodo.left;
            if(nodo.left == nodo.parent){
                nodo.left = null;
            }
        }
        
        else{
            // creacion de un nuevo nodo temporal que sera la nueva raiz
            nodoBinario temp = new nodoBinario(nodo.key);
            temp.left = nodo.left.right;
            temp.right = nodo.right;
            temp.parent = nodo.left;
            temp.color = nodo.color;
            if(temp.left != null) {
                temp.left.parent = temp;
            }
            if(temp.right != null) {
                temp.right.parent = temp;
            }

            this.key = nodo.left.key;
            this.left = nodo.left.left;
            this.right = temp;
            this.parent = nodo.parent;
            if(this.left != null) {
                this.left.parent = this;
            }
            if(this.right != null) {
                this.right.parent = this;
            }
            this.color = "black"; // La raíz siempre es negra
        }
    }

    public void rotacionIzquierda(nodoBinario nodo){

        if(nodo.parent != null){
            nodo.right.parent = nodo.parent;
            if(nodo.key < nodo.parent.key) {
                nodo.parent.left = nodo.right;
            }
            else {
                nodo.parent.right = nodo.right;
            }

            if(nodo.right.left != null) {
                nodo.right.left.parent = nodo;
                nodo.right = nodo.right.left;
            }
            nodo.right.left = nodo;
            nodo.parent = nodo.right;
            if(nodo.right == nodo.parent){
                nodo.right = null;
            }
        }
        
        else{
            // creacion de un nuevo nodo temporal que sera la nueva raiz
            nodoBinario temp = new nodoBinario(nodo.key);
            temp.right = nodo.right.left;
            temp.left = nodo.left;
            temp.parent = nodo.left;
            temp.color = nodo.color;
            if(temp.left != null) {
                temp.left.parent = temp;
            }
            if(temp.right != null) {
                temp.right.parent = temp;
            }

            this.key = nodo.right.key;
            this.left = temp;
            this.right = nodo.right.right;
            this.parent = nodo.parent;
            if(this.left != null) {
                this.left.parent = this;
            }
            if(this.right != null) {
                this.right.parent = this;
            }
            this.color = "black"; // La raíz siempre es negra
        }
    }

    // definimos si pertenecen a un caso
    public boolean isCaso1(nodoBinario nuevoNodo) {
        if(nuevoNodo.parent.parent.left == nuevoNodo.parent && nuevoNodo.parent.parent.right != null) {
            return nuevoNodo.parent.parent.right.color.equals("red");
        }
        else if(nuevoNodo.parent.parent.right == nuevoNodo.parent && nuevoNodo.parent.parent.left != null) {
            return nuevoNodo.parent.parent.left.color.equals("red");
        }
        return false;
    }

    public boolean isCaso2(nodoBinario nuevoNodo, String ladoPadre){
        if(nuevoNodo.parent.key < nuevoNodo.key && ladoPadre.equals("left")) {
            return (nuevoNodo.parent.parent.right != null && nuevoNodo.parent.parent.right.color.equals("black")) || (nuevoNodo.parent.parent.right == null);
        }
        else if(nuevoNodo.parent.key > nuevoNodo.key && ladoPadre.equals("right")) {
            return (nuevoNodo.parent.parent.left != null && nuevoNodo.parent.parent.left.color.equals("black")) || (nuevoNodo.parent.parent.left == null);
        }
        else return false;
    }

    public boolean isCaso3(nodoBinario nuevoNodo, String ladoPadre){
        if(nuevoNodo.parent.key > nuevoNodo.key && ladoPadre.equals("left")) {
            return (nuevoNodo.parent.parent.right != null && nuevoNodo.parent.parent.right.color.equals("black")) || (nuevoNodo.parent.parent.right == null);
        }
        else if(nuevoNodo.parent.key < nuevoNodo.key && ladoPadre.equals("right")) {
            return (nuevoNodo.parent.parent.left != null && nuevoNodo.parent.parent.left.color.equals("black")) || (nuevoNodo.parent.parent.left == null);
        }
        else return false;
    }


    // operacion de insercion
    public void insertar(nodoBinario nuevoNodo){

        // Primero, insertamos el nodo como un árbol binario normal
        this.insercion(nuevoNodo);

        // Ahora, debemos mantener las propiedades del árbol

        // Si el abuelo del nuevo nodo es nulo, significa que el padre del nuevo nodo es la raíz
        if (nuevoNodo.parent.parent == null){
            this.color = "black"; // Si es la raíz, debe ser negra
        }

        // Si el padre del nuevo nodo es rojo, entonces x tiene abuelo, y debemos hacer ajustes
        else if (nuevoNodo.parent.color.equals("red")) {

            while(nuevoNodo.color.equals("red") && nuevoNodo.parent.color.equals("red")){

                //cuando el padre de x es un nodo izquierdo
                if(nuevoNodo.parent.parent.key > nuevoNodo.parent.key){
                    if (isCaso1(nuevoNodo)){
                        nuevoNodo.parent.color = "black";
                        nuevoNodo.parent.parent.right.color = "black"; 
                        if(nuevoNodo.parent.parent.parent != null) {
                            nuevoNodo.parent.parent.color = "red"; // el abuelo se vuelve 
                        }
                        nuevoNodo = nuevoNodo.parent.parent;
                        
                    }

                    else if(isCaso2(nuevoNodo, "left")){
                        rotacionIzquierda(nuevoNodo.parent);
                        nuevoNodo = nuevoNodo.left;
                    }
                    else if(isCaso3(nuevoNodo, "left")){
                        nuevoNodo.parent.color = "black";
                        nuevoNodo.parent.parent.color = "red";
                        rotacionDerecha(nuevoNodo.parent.parent);
                    }
                    else{
                        break; // Si no se cumple ninguno de los casos, salimos del bucle
                    }
                }

                // Cuando el padre de x es un nodo derecho
                else{
                    if (isCaso1(nuevoNodo)){
                        nuevoNodo.parent.color = "black";
                        nuevoNodo.parent.parent.left.color = "black"; 
                        if(nuevoNodo.parent.parent.parent != null) {
                            nuevoNodo.parent.parent.color = "red"; // el abuelo se vuelve rojo
                        }
                        nuevoNodo = nuevoNodo.parent.parent;
                    }

                    else if(isCaso2(nuevoNodo, "right")){
                        rotacionDerecha(nuevoNodo.parent);
                        nuevoNodo = nuevoNodo.right;
                    }
                    else if(isCaso3(nuevoNodo, "right")){
                        nuevoNodo.parent.color = "black";
                        nuevoNodo.parent.parent.color = "red";
                        rotacionIzquierda(nuevoNodo.parent.parent);
                        nuevoNodo = nuevoNodo.parent.left;
                    }
                    else{
                        break; // Si no se cumple ninguno de los casos, salimos del bucle
                    }
                }
            
            }
        }

        else{
            return;
        }
    }


    // Método para eliminar un nodo en el árbol

    public nodoBinario eliminar(float key, nodoBinario root) {
        nodoBinario z = root.buscar(key);
        if (z == null) return root;
        //Variables necesarias para el proceso y por si toca hacer un cambio
        nodoBinario y = z;
        String Ocolor = y.color;
        nodoBinario x;
        //Aplicaciones de casos basado en el fix up
        //Caso 1: No hijo izquierdo
        if(z.left == null) {
            x = z.right;
            root = transplantar(root, z, z.right);
        }
        //Caso 2: No hijo derecho
        else if(z.right == null) {
            x = z.left;
            root = transplantar(root, z, z.left); 
        }
        //Caso 3: Dos hijos
        else {
            y = z.right.minimo();
            Ocolor = y.color;
            x = y.right;
            if (y.parent == z) {
                if (x != null) x.parent = y;
            } else {
                root = transplantar(root, y, y.right);
                y.right = z.right;
                if (y.right != null) y.right.parent = y;
            }
            root = transplantar(root, z, y);
            y.left = z.left;
            if (y.left != null) y.left.parent = y;
            y.color = z.color;
        }

        //Asegurarse de que los colores sean los adecuados y se mantengan las propiedades luego de la eliminación
        if ("black".equals(Ocolor)) {
            while (x != root && (x == null || "black".equals(x.color))) {
                nodoBinario w;
                //Revisión de distintos casos
                if (x == x.parent.left) {
                    w = x.parent.right;
                    //El hermano es rojo
                    if("red".equals(w.color)) {
                        w.color = "black";
                        x.parent.color = "red";
                        x.parent.rotacionIzquierda(root);
                        w = x.parent.right;
                    }
                    //Es N con dos hijos N
                    if((w.left == null || "black".equals(w.left.color)) &&
                        (w.right == null || "black".equals(w.right.color))) {
                        w.color = "red";
                        x = x.parent;
                    }
                    //Es N con un hijo izquierdo rojo y un hijo derecho N
                    else{
                        if (w.right == null || "black".equals(w.right.color)) {
                            if (w.left != null) w.left.color = "black";
                            w.color = "red";
                            w.rotacionDerecha(root);
                            w = x.parent.right;
                        }
                        //Hermano N con hijo derecho Rojo
                        w.color = x.parent.color;
                        x.parent.color = "black";
                        if (w.right != null) w.right.color = "black";
                        x.parent.rotacionIzquierda(root);
                        x = root;
                    }
                //Lo mismo pero con otra orientación
                }else{
                    w = x.parent.left;
                    if ("red".equals(w.color)) {
                        w.color = "black";
                        x.parent.color = "red";
                        x.parent.rotacionDerecha(root);
                        w = x.parent.left;
                    }
                    if((w.right == null || "black".equals(w.right.color)) &&
                        (w.left == null || "black".equals(w.left.color))) {
                        w.color = "red";
                        x = x.parent;
                    }else{
                        if (w.left == null || "black".equals(w.left.color)) {
                            if (w.right != null) w.right.color = "black";
                            w.color = "red";
                            w.rotacionIzquierda(root);
                            w = x.parent.left;
                        }
                        w.color = x.parent.color;
                        x.parent.color = "black";
                        if(w.left != null) w.left.color = "black";
                        x.parent.rotacionDerecha(root);
                        x = root;
                    }
                }
            }

            if(x != null) x.color = "black";
        }

        return root; 
    }

    private nodoBinario transplantar(nodoBinario root, nodoBinario u, nodoBinario v) {
        if(u.parent == null) {
            root = v;
        } else if(u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if(v != null) {
            v.parent = u.parent;
        }
        return root;
    }







    // Método para buscar un nodo en el árbol
    // operacion de busqueda
    public nodoBinario buscar(float key){
        if(this.key == key){
            return this;
        }

        else{
            if (key < this.key  && this.left != null){
                return this.left.buscar(key);
            }
            else if(key > this.key && this.right != null){
                return this.right.buscar(key);
            }
            else{
                return null; // no encontrado
            }
        }
    }


    // Método para buscar el mayor nodo en el árbol
    // operacion de maximo
    nodoBinario maximo(){
        nodoBinario current = this;
        while (current.right != null){
            current = current.right;
        }
        return current;
    }


    // Método para buscar el menor nodo en el árbol
    // operacion de minimo
    public nodoBinario minimo(){
        nodoBinario current = this;
        while (current.left != null){
            current = current.left;
        }
        return current;
    }

    
}
