import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UserManagerUI extends JFrame {

    public nodoBinario root;

    private DefaultTableModel model;
    private static final String[] columnNames = {"ID","Nombre","Teléfono","Edad"};
    
    private static final Font BASE_FONT   = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    private static final Font LABEL_FONT  = BASE_FONT.deriveFont(Font.BOLD);
    private static final Font TITLE_FONT  = BASE_FONT.deriveFont(Font.BOLD, 22f);
    private static final Font BUTTON_FONT = BASE_FONT.deriveFont(Font.BOLD);

    // -------- Campos de texto que necesitamos fuera de las pestañas --------
    private JTextField addIdField, addNameField, addPhoneField, addAgeField;
    private JTextField delIdField;
    private JTextField searchIdField;

    public UserManagerUI(nodoBinario root) {
        this.root = root;

        /* ---------- Ventana ---------- */
        setTitle("Gestor de usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        /* ---------- Título ---------- */
        JLabel title = new JLabel("Gestor de usuarios", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        add(title, BorderLayout.NORTH);

        /* ---------- Pestañas ---------- */
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(BASE_FONT);

        tabs.addTab("Agregar",  buildAgregarTab());
        tabs.addTab("Eliminar", buildEliminarTab());
        tabs.addTab("Buscar",   buildBuscarTab());
        tabs.addTab("Visualizar", buildVisualizarTab());  

        add(tabs, BorderLayout.CENTER);
    }

    /* ========= Pestaña: AGREGAR ========= */
    private JPanel buildAgregarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        addIdField    = addLabelFieldPair(p, gbc, "Id:");
        addNameField  = addLabelFieldPair(p, gbc, "Nombre:");
        addPhoneField = addLabelFieldPair(p, gbc, "Teléfono:");
        addAgeField   = addLabelFieldPair(p, gbc, "Edad:");

        JButton addBtn = new JButton("Agregar");
        stylizeButton(addBtn);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        p.add(addBtn, gbc);

        // Acción del botón "Agregar"
        addBtn.addActionListener(e -> {
            String id   = addIdField.getText().trim();
            String name = addNameField.getText().trim();
            String tel  = addPhoneField.getText().trim();
            String age  = addAgeField.getText().trim();
            if (id.isEmpty() || name.isEmpty() || tel.isEmpty() || age.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                float idNum = (float) Integer.parseInt(id);
                byte ageNum = (byte) Integer.parseInt(age);
                nodoBinario newUser = new nodoBinario(idNum, name, tel, ageNum);
                if(root.insertar(newUser)){
                    recordR.appendNodo("files/registros2500_atras.txt", newUser);
                    cargarEnTabla(); // Actualizamos la tabla visualizada
                    JOptionPane.showMessageDialog(this, "Usuario agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "El Id ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Id y Edad deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    });

    return p;
    }

    /* ========= Pestaña: ELIMINAR ========= */
    private JPanel buildEliminarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        delIdField = addLabelFieldPair(p, gbc, "Id a eliminar:");

        JButton delBtn = new JButton("Eliminar");
        stylizeButton(delBtn);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        p.add(delBtn, gbc);

        delBtn.addActionListener(e -> {
        String id = delIdField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un Id.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        float idNum = (float) Integer.parseInt(id);
        this.root = root.eliminar(idNum, root);
        if(recordR.deleteByKey(idNum)) {
            cargarEnTabla(); // Actualizamos la tabla visualizada
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        });

        return p;
    }

    /* ========= Pestaña: BUSCAR ========= */
    private JPanel buildBuscarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        searchIdField = addLabelFieldPair(p, gbc, "Id:");

        JButton buscarID = new JButton("Buscar por Id");
        stylizeButton(buscarID);
        gbc.gridx = 1;          
        gbc.gridy++;  
        p.add(buscarID, gbc);

        JButton buscarMayor = new JButton("Buscar el mayor Id");
        stylizeButton(buscarMayor);
        gbc.gridy++; 
        p.add(buscarMayor, gbc);

        JButton buscarMenor = new JButton("Buscar el menor Id");
        stylizeButton(buscarMenor);
        gbc.gridy++; 
        p.add(buscarMenor, gbc);

        // Acciones de los botones

        buscarID.addActionListener(e -> {
            String id = searchIdField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa un Id.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            float idNum = (float) Integer.parseInt(id);
            nodoBinario user = root.buscar(idNum);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Usuario encontrado:\n" +
                        "ID: " + user.key + "\n" +
                        "Nombre: " + user.name + "\n" +
                        "Teléfono: " + user.phone + "\n" +
                        "Edad: " + user.age, "Usuario encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buscarMayor.addActionListener(e -> {
            nodoBinario mayor = root.maximo();
            if (mayor != null) {
                JOptionPane.showMessageDialog(this, "Usuario con mayor ID:\n" +
                        "ID: " + mayor.key + "\n" +
                        "Nombre: " + mayor.name + "\n" +
                        "Teléfono: " + mayor.phone + "\n" +
                        "Edad: " + mayor.age, "Usuario encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay usuarios en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buscarMenor.addActionListener(e -> {
            nodoBinario menor = root.minimo();
            if (menor != null) {
                JOptionPane.showMessageDialog(this, "Usuario con menor ID:\n" +
                        "ID: " + menor.key + "\n" +
                        "Nombre: " + menor.name + "\n" +
                        "Teléfono: " + menor.phone + "\n" +
                        "Edad: " + menor.age, "Usuario encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No hay usuarios en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return p;
    }

    /* ========= Pestaña: VISUALIZAR ========= */
    private JPanel buildVisualizarTab() {
        //Se crea el array de usuarios
        ArrayList<nodoBinario> listaUsuarios = inorder.recorrerInOrder(root);

        JPanel panel = new JPanel(new BorderLayout());

        Object[][] emptyData  = {}; // tabla vacía por defecto

        // insertamos los usuarios en la tabla
        for (nodoBinario user : listaUsuarios) {
            Object[] row = {user.key, user.name, user.phone, user.age};
            emptyData = java.util.Arrays.copyOf(emptyData, emptyData.length + 1);
            emptyData[emptyData.length - 1] = row;
        }

        model = new DefaultTableModel(emptyData, columnNames) {
            // evitamos edición directa de celdas
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        JTable table = new JTable(model);
        table.setFont(BASE_FONT);
        table.setRowHeight(24);
        table.getTableHeader().setFont(LABEL_FONT);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /* ========= Método de refresco ========= */
    private void cargarEnTabla() {
        model.setRowCount(0);     

        for (nodoBinario n : inorder.recorrerInOrder(root)) {
            model.addRow(new Object[] {
                n.key, n.name, n.phone, n.age
            });
        }
    }

    /* ========= Utilidades ========= */

    private void stylizeButton(JButton b) {
        b.setFont(BUTTON_FONT);
        b.setPreferredSize(new Dimension(200, 30));
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0;
        return gbc;
    }

    private JTextField  addLabelFieldPair(JPanel panel, GridBagConstraints gbc, String labelText) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(LABEL_FONT);

        JTextField txt = new JTextField(20);
        txt.setFont(BASE_FONT);
        txt.setPreferredSize(new Dimension(220, 28));

        gbc.gridx = 0; panel.add(lbl,  gbc);
        gbc.gridx = 1; panel.add(txt,  gbc);
        gbc.gridy++;         
        return txt;          
    }
}
