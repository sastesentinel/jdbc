import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class Table extends JInternalFrame {
    public Table() {
        super("Faculty Data", true, true, true, true);
        setSize(600, 400); // Set the size of the table window

        // Create a panel to hold the table
        JPanel panel = new JPanel(new BorderLayout());

        // Create a table model to hold the database data
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Set the columns for the table (adjust based on your database schema)
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Experience");

        // Fetch data from the database and fill the table
        try {
            Statement stmt = sqldb.st; // Use the already established connection and statement from sqldb class
            String query = "SELECT * FROM facultyinfo"; // Adjust the query based on your table name and columns
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // Add rows to the table model
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("exp")
                });
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add the scroll pane (with table inside) to the panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the internal frame
        add(panel);

        // Make the internal frame visible
        setVisible(true);
    }
}
