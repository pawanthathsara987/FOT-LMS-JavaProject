package StuViewMarks;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class StuViewMarks {
    private JPanel rootPanel;
    private JPanel MainPanal;
    private JPanel title;
    private JPanel Table;
    private JPanel middle;
    private JTable table1;


    public StuViewMarks() {
        createTable();
    }


    public JPanel getRootPanel() {
        return MainPanal;
    }

    public void createTable() {
        Object[][] data = {
                {"ICT2112", "A"},
                {"ICT2112", "A"},
                {"ICT2112", "A"},
                {"ICT2112", "A"},
                {"ICT2112", "A"},
        };

        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Subject code", "Results"}
        ));
        TableColumnModel columns = table1.getColumnModel();
        columns.getColumn(0).setMinWidth(250);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);

    }
}