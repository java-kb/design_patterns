# Builder Design Pattern in Java: Before and after
*Source: https://sourcemaking.com/design_patterns/builder/java/1*
## Before
In this example we render a tabular data read from a file into a GUI table. The requirement of this job is to be able to pick a different GUI implementation in run time.

This is the code BEFORE we applied the Builder pattern.

Note: for the sake of simplicity of comparison between BEFORE and AFTER, we have made all important classes internal, so that they can live together in one file. This is not a pattern limitation.

public class TableBuilderDemo {

    public static void main(String[] args) {
        (new TableBuilderDemo()).demo(args);
    }

    /**
     * Client code perspective.
     */
    public void demo(String[] args) {
        // Name of the GUI table class can be passed to the app parameters.
        String class_name = args.length > 0 ?  args[0] : "JTable_Table";

        // Then we read the tabular data from file...
        String file_name = getClass().getResource("../BuilderDemo.dat").getFile();
        String[][] matrix = read_data_file(file_name);

        // ..and pass it to specific GUI creator, which knows what GUI
        // component to create and how to initialize it.
        Component comp;
        if (class_name.equals("GridLayout_Table")) {
            comp = new GridLayout_Table(matrix).get_table();
        } else if (class_name.equals("GridBagLayout_Table")) {
            comp = new GridBagLayout_Table(matrix).get_table();
        } else {
            comp = new JTable_Table(matrix).get_table();
        }

        // Finally, create a GUI window and put there our table component.
        JFrame frame = new JFrame("BuilderDemo - " + class_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(comp);
        frame.pack();
        frame.setVisible(true);
    }

    class JTable_Table {
        private JTable m_table;

        public JTable_Table(String[][] matrix) {
            m_table = new JTable(matrix[0].length, matrix.length);

            TableModel model = m_table.getModel();
            for (int i = 0; i < matrix.length; ++i)
                for (int j = 0; j < matrix[i].length; ++j)
                    model.setValueAt(matrix[i][j], j, i);
        }

        public Component get_table() {
            return m_table;
        }
    }

    class GridLayout_Table {
        private JPanel m_table = new JPanel();

        public GridLayout_Table(String[][] matrix) {
            m_table = new JPanel();
            m_table.setLayout(new GridLayout(matrix[0].length, matrix.length));
            m_table.setBackground(Color.white);

            for (int i = 0; i < matrix[i].length; ++i)
                for (int j = 0; j < matrix.length; ++j)
                    m_table.add(new Label(matrix[j][i]));
        }

        public Component get_table() {
            return m_table;
        }
    }

    class GridBagLayout_Table {
        private JPanel m_table = new JPanel();

        public GridBagLayout_Table(String[][] matrix) {
            GridBagConstraints c = new GridBagConstraints();

            m_table.setLayout(new GridBagLayout());
            m_table.setBackground(Color.white);

            for (int i = 0; i < matrix.length; ++i)
                for (int j = 0; j < matrix[i].length; ++j) {
                    c.gridx = i;
                    c.gridy = j;
                    m_table.add(new Label(matrix[i][j]), c);
                }
        }

        public Component get_table() {
            return m_table;
        }
    }

    public static String[][] read_data_file(String file_name) {
        String[][] matrix = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_name));
            String line;
            String[] cells;

            if ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                int width = Integer.parseInt(cells[0]);
                int height = Integer.parseInt(cells[1]);
                matrix = new String[width][height];
            }

            int row = 0;
            while ((line = br.readLine()) != null) {
                cells = line.split("\\t");
                for (int i = 0; i < cells.length; ++i) {
                    matrix[i][row] = cells[i];
                }
                row++;France
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return matrix;
    }
}



## After
The main() creates a reader/parser, and configures it with a builder (an object that implements a standard interface and knows how to create one of many possible "results". The reader reads and parses the common input and delegates the construction to the configured builder.

This implementation demonstrates the spirit of the Builder pattern, but it is more intricate, and probably cannot be justified for this fairly limited context.

public class TableBuilderDemo {

    public static void main(String[] args) {
        (new TableBuilderDemo()).demo(args);
    }

    /**
     * Client code perspective.
     */
    public void demo(String[] args) {
        // Name of the GUI table class can be passed to the app parameters.
        String class_name = args.length > 0 ? args[0] : "JTable_Builder";

        Builder target = null;
        try {
            target = (Builder) Class.forName(getClass().getName() + "$" + class_name)
                    .getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String file_name = getClass().getResource("../BuilderDemo.dat").getFile();
        TableDirector director = new TableDirector(target);
        director.construct(file_name);
        Component comp = target.get_result();

        JFrame frame = new JFrame("BuilderDemo - " + class_name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(comp);
        frame.pack();
        frame.setVisible(true);
    }

    interface Builder {
        void set_width_and_height(int width, int height);

        void start_row();

        void build_cell(String value);

        Component get_result();
    }

    public static class JTable_Builder implements Builder {
        private JTable m_table;
        private TableModel m_model;
        private int x = 0, y = 0;

        public void set_width_and_height(int width, int height) {
            m_table = new JTable(height, width);
            m_model = m_table.getModel();
        }

        public void start_row() {
            x = 0;
            ++y;
        }

        public void build_cell(String value) {
            m_model.setValueAt(value, y, x++);
        }

        public Component get_result() {
            return m_table;
        }
    }

    public static class GridLayout_Builder implements Builder {
        private JPanel m_panel = new JPanel();

        public void set_width_and_height(int width, int height) {
            m_panel.setLayout(new GridLayout(height, width));
            m_panel.setBackground(Color.white);
        }

        public void start_row() {
        }

        public void build_cell(String value) {
            m_panel.add(new Label(value));
        }

        public Component get_result() {
            return m_panel;
        }
    }

    public static class GridBagLayout_Builder implements Builder {
        private JPanel m_panel = new JPanel();
        private GridBagConstraints c = new GridBagConstraints();
        private int x = 0, y = 0;

        public void set_width_and_height(int width, int height) {
            m_panel.setLayout(new GridBagLayout());
            m_panel.setBackground(Color.white);
        }

        public void start_row() {
            x = 0;
            ++y;
        }

        public void build_cell(String value) {
            c.gridx = x++;
            c.gridy = y;
            m_panel.add(new Label(value), c);
        }

        public Component get_result() {
            return m_panel;
        }
    }

    class TableDirector {
        private Builder m_builder;

        public TableDirector(Builder b) {
            m_builder = b;
        }

        public void construct(String file_name) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name));
                String line;
                String[] cells;

                if ((line = br.readLine()) != null) {
                    cells = line.split("\\t");
                    int width = Integer.parseInt(cells[0]);
                    int height = Integer.parseInt(cells[1]);
                    m_builder.set_width_and_height(width, height);
                }

                while ((line = br.readLine()) != null) {
                    cells = line.split("\\t");
                    for (int col = 0; col < cells.length; ++col) {
                        m_builder.build_cell(cells[col]);
                    }
                    m_builder.start_row();
                }

                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
