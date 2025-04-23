package StuNotice;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class StuNotice {
    private JPanel rootPanel;
    private JTable showTable;



    public StuNotice() {
        createTable();
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void createTable(){
        Object [] [] data = {
                {"2025/02/15","Student Union","teclms.ruh.ac.lk"},
                {"2025/02/15","Student Union","teclms.ruh.ac.lk"},
                {"2025/02/15","Student Union","teclms.ruh.ac.lk"},
                {"2025/02/15","Student Union","teclms.ruh.ac.lk"},
                {"2025/02/15","Student Union","teclms.ruh.ac.lk"},


        };

        showTable.setModel(new DefaultTableModel(
                data,
                new String [] {"Date","Title","Download Link"}
        ));

        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMinWidth(250);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);


    }

}
