import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class AddFaculty extends JInternalFrame {
    // Declare labels and text fields for faculty information
    JLabel facultyId, facultyName, experience;
    JTextField jtxtfid, jtxtfname, jtxtexp;
    JButton jbtnadd;

    // Constructor of the JInternalFrame
    // Arguments specify the title, resizable, closable, maximizable, and iconifiable
    public AddFaculty() {
        super("Add Faculty Details", true, // resizable
                true, // closable
                false, // maximizable
                true); // iconifiable

        // Set layout with 7 rows and 1 column
        setLayout(new GridLayout(7, 1));

        // Initialize JLabels with faculty information labels
        facultyId = new JLabel("Faculty ID");
        facultyName = new JLabel("Faculty Name");
        experience = new JLabel("Experience (years)");

        // Initialize JTextFields for faculty id, name, and experience
        jtxtfid = new JTextField(15); // Field for faculty ID
        jtxtfname = new JTextField(50); // Field for faculty name
        jtxtexp = new JTextField(3); // Field for faculty experience

        // Initialize JButton for adding faculty information
        jbtnadd = new JButton("Add Faculty Info");

        // Add ActionListener to handle button clicks
        jbtnadd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if none of the text fields are empty
                if (!jtxtfid.getText().isEmpty() &&
                        !jtxtfname.getText().isEmpty() &&
                        !jtxtexp.getText().isEmpty()) {
                    // SQL query to insert faculty information into the database
                    String str = "INSERT INTO facultyinfo (id,name, exp) VALUES ('"
                            + jtxtfid.getText() + "', '"
                            + jtxtfname.getText() + "', "
                            + jtxtexp.getText() + ")";

                    // Execute the query and store the result (1 if successful)
                    int val = sqldb.iud_data(str);
                    if (val == 1) {
                        // Show success message and clear text fields
                        JOptionPane.showMessageDialog(null, "Record inserted successfully...");
                        jtxtfid.setText("");
                        jtxtfname.setText("");
                        jtxtexp.setText("");
                    }
                } else {
                    // If any field is empty, show error message
                    JOptionPane.showMessageDialog(null, "Please fill all faculty details");
                }
            }
        });

        // Add an internal frame listener to dispose of the frame when it's closing
        this.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                dispose(); // Close the internal frame
            }
        });

        // Add components to the internal frame
        add(facultyId); // Add label for faculty ID
        add(jtxtfid);   // Add text field for faculty ID
        add(facultyName); // Add label for faculty name
        add(jtxtfname);  // Add text field for faculty name
        add(experience);  // Add label for experience
        add(jtxtexp);    // Add text field for experience
        add(jbtnadd);    // Add the "Add Faculty Info" button

        // Set the size of the internal frame
        setSize(180, 200);
    }
}
