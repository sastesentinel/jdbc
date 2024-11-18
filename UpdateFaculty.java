import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateFaculty extends JInternalFrame {
    JComboBox<String> jcmbId; // ComboBox for faculty ID
    JLabel facultyId, facultyName, experience; // Labels for faculty information
    JTextField jtxtfname, jtxtexp; // TextFields for name and experience
    JButton jbtnupdate; // Button for updating faculty info

    // Constructor of the JInternalFrame
    public UpdateFaculty() {
        super("Update Faculty Details", true, // resizable
                true, // closable
                true, // maximizable
                true); // iconifiable

        // Set layout with 7 rows and 1 column
        setLayout(new GridLayout(7, 1));

        // Initialize JLabels for faculty ID, name, and experience
        facultyId = new JLabel("Faculty ID");
        facultyName = new JLabel("Faculty Name");
        experience = new JLabel("Experience");

        // Initialize JComboBox for selecting faculty ID and TextFields for faculty name and experience
        jcmbId = new JComboBox<>();
        jtxtfname = new JTextField(50);
        jtxtexp = new JTextField(3);

        // Initialize ComboBox with faculty IDs
        initComboBox();

        // Add ActionListener to ComboBox for selecting faculty details when an ID is selected
        jcmbId.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet res;
                try {
                    // SQL query to fetch faculty name and experience based on selected faculty ID
                    res = sqldb.fetchdata("SELECT name, exp FROM facultyinfo WHERE id='"
                            + jcmbId.getSelectedItem() + "'");
                    // Fill the text fields with the fetched data
                    while (res.next()) {
                        jtxtfname.setText(res.getString("name"));
                        jtxtexp.setText(res.getString("exp"));
                    }
                    res.close();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        });

        // Initialize JButton for updating faculty information
        jbtnupdate = new JButton("Update Faculty Info");
        jbtnupdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Ensure that both name and experience fields are filled
                if (!jtxtfname.getText().isEmpty() && !jtxtexp.getText().isEmpty()) {
                    // SQL query to update faculty name and experience
                    String str = "UPDATE facultyinfo SET name = '" + jtxtfname.getText() +
                            "', exp ='" + jtxtexp.getText() + "' WHERE id ='" +
                            jcmbId.getSelectedItem() + "'";
                    // Execute the query and check if it was successful
                    int val = sqldb.iud_data(str);
                    if (val == 1) {
                        // Show success message and clear text fields
                        JOptionPane.showMessageDialog(null, "Record updated successfully...");
                        jtxtfname.setText("");
                        jtxtexp.setText("");
                    }
                } else {
                    // Show error message if any field is empty
                    JOptionPane.showMessageDialog(null, "Please fill all faculty details");
                }
            }
        });

        // Add an internal frame listener to close the frame when it’s closing
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                dispose();
            }
        });

        // Add components to the internal frame
        add(facultyId);
        add(jcmbId);
        add(facultyName);
        add(jtxtfname);
        add(experience);
        add(jtxtexp);
        add(jbtnupdate);

        // Set size of the internal frame
        setSize(180, 200);
    }

    // Initialize ComboBox with faculty IDs from the database
    public void initComboBox() {
        ResultSet res;
        jcmbId.removeAllItems();
        try {
            // SQL query to get faculty IDs
            res = sqldb.fetchdata("SELECT id FROM facultyinfo");
            // Add each faculty ID to the ComboBox
            while (res.next()) {
                jcmbId.addItem(res.getString("id"));
            }
            res.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
