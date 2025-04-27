package ViewNotice;

import Database.DbConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ViewNotice {
    private JPanel rootPanel;
    private JTable noticeInfo;
    private JTextArea noticeDescription;
    private JLabel noticeTitle;
    private JTable showTable;
    private  JFrame frame;

    private String stuUsername;


    public ViewNotice() {
        frame = new JFrame("ViewNotice");
        frame.setContentPane(rootPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1100, 750);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        noticeDescription.setVisible(false);

        this.stuUsername = stuUsername;

        showNotice();

        noticeInfo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

            }
        });
        noticeInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNoticeDetails();
            }
        });
    }

    public void showNotice() {

        DbConnector db = new DbConnector();
        Connection conn = db.getConnection();
        if (conn == null) {
            return;
        }

        String showNotice_sql = "select title, content, posteddate from notice";

        try(PreparedStatement stmt = conn.prepareStatement(showNotice_sql)) {
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) noticeInfo.getModel();

            model.setRowCount(0);

            int columnsNumber = rsmd.getColumnCount();
            String[] columnNames = new String[columnsNumber];
            for (int i = 0; i < columnsNumber; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(columnNames);

            while (rs.next()) {
                Object[] rowData = new Object[columnsNumber];
                for (int i = 0; i < columnsNumber; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error showing notice details: " + e.getMessage(), e);
        }
    }

    public void showNoticeDetails() {
        int selectedRow = noticeInfo.getSelectedRow();
        if (selectedRow >= 0) {
            String title = noticeInfo.getModel().getValueAt(selectedRow, 0).toString();
            String content = noticeInfo.getModel().getValueAt(selectedRow, 1).toString();

            noticeTitle.setText(title);
            noticeDescription.setVisible(true);
            noticeDescription.setText(content);
        } else {
            noticeTitle.setText("");
            noticeDescription.setText("");
        }
    }
}
