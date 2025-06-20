import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class UserManagerUI extends JFrame {

    private static final Font BASE_FONT   = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    private static final Font LABEL_FONT  = BASE_FONT.deriveFont(Font.BOLD);
    private static final Font TITLE_FONT  = BASE_FONT.deriveFont(Font.BOLD, 22f);
    private static final Font BUTTON_FONT = BASE_FONT.deriveFont(Font.BOLD);

    public UserManagerUI(nodoBinario root) {

        

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
        tabs.addTab("Visualizar", buildVisualizarTab(root));   // ← nueva pestaña

        add(tabs, BorderLayout.CENTER);
    }

    /* ========= Pestaña: AGREGAR ========= */
    private JPanel buildAgregarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        String[] labels = {"Id:", "Nombre:", "Teléfono:", "Edad:"};
        for (String text : labels) addLabelFieldPair(p, gbc, text);

        JButton addBtn = new JButton("Agregar");
        stylizeButton(addBtn);
        gbc.gridx = 1; gbc.gridy++; gbc.anchor = GridBagConstraints.LINE_START;
        p.add(addBtn, gbc);

        return p;
    }

    /* ========= Pestaña: ELIMINAR ========= */
    private JPanel buildEliminarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        addLabelFieldPair(p, gbc, "Id a eliminar:");

        JButton delBtn = new JButton("Eliminar");
        stylizeButton(delBtn);
        gbc.gridx = 1; gbc.gridy++; gbc.anchor = GridBagConstraints.LINE_START;
        p.add(delBtn, gbc);

        return p;
    }

    /* ========= Pestaña: BUSCAR ========= */
    private JPanel buildBuscarTab() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        addLabelFieldPair(p, gbc, "Id:");

        String[] btnNames = {"Buscar por Id", "Buscar el mayor Id", "Buscar el menor Id"};
        for (String name : btnNames) {
            JButton b = new JButton(name);
            stylizeButton(b);
            gbc.gridx = 1; gbc.gridy++; gbc.anchor = GridBagConstraints.LINE_START;
            p.add(b, gbc);
        }
        return p;
    }

    /* ========= Pestaña: VISUALIZAR ========= */
    private JPanel buildVisualizarTab(nodoBinario root) {

        //Se crea el array de usuarios
        ArrayList<nodoBinario> listaUsuarios = null;
        listaUsuarios = inorder.recorrerInOrder(root);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Teléfono", "Edad"};
        Object[][] emptyData  = {}; // tabla vacía por defecto
        // insertamos los usuarios en la tabla
        for (nodoBinario user : listaUsuarios) {
            Object[] row = {user.key, user.name, user.phone, user.age};
            emptyData = java.util.Arrays.copyOf(emptyData, emptyData.length + 1);
            emptyData[emptyData.length - 1] = row;
        }

        DefaultTableModel model = new DefaultTableModel(emptyData, columnNames) {
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

    private void addLabelFieldPair(JPanel panel, GridBagConstraints gbc, String labelText) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(LABEL_FONT);

        JTextField txt = new JTextField(20);
        txt.setFont(BASE_FONT);
        txt.setPreferredSize(new Dimension(220, 28));

        gbc.gridx = 0; panel.add(lbl, gbc);
        gbc.gridx = 1; panel.add(txt, gbc);
        gbc.gridy++; // siguiente fila
    }
}
