import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
    JDesktopPane jdpDesktop; // Main desktop pane to hold internal frames
    JMenuBar jmb; // Menu bar

    public Main() {
        super("Faculty Database App");
        // Set the main window size and position it 50 pixels away from each edge of the screen
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

        // Add a window listener to close the database connection when the window closes
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sqldb.connclose(); // Close the database connection
                System.exit(0); // Exit the application
            }
        });

        // Create the desktop pane to hold the internal frames
        jdpDesktop = new JDesktopPane();
        setContentPane(jdpDesktop);
        // Set drag mode to "outline" to make dragging internal frames faster
        jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");

        // Create the menu bar
        jmb = new JMenuBar();

        JMenu facultyMenu = new JMenu("Faculty");
        facultyMenu.setMnemonic(KeyEvent.VK_F); // Shortcut key for Faculty menu

        // Create "Add Faculty" menu item
        JMenuItem addFaculty = new JMenuItem("Add Faculty");
        addFaculty.setMnemonic(KeyEvent.VK_A); // Shortcut: Alt+A for Add Faculty
        addFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddFaculty af = new AddFaculty(); // Create AddFaculty internal frame
                af.setVisible(true); // Make it visible
                jdpDesktop.add(af); // Add it to the desktop pane
            }
        });

        // Create "Update Faculty" menu item
        JMenuItem updateFaculty = new JMenuItem("Update Faculty");
        updateFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateFaculty uf = new UpdateFaculty(); // Create UpdateFaculty internal frame
                uf.setVisible(true); // Make it visible
                jdpDesktop.add(uf); // Add it to the desktop pane
            }
        });

        JMenuItem viewFaculty = new JMenuItem("View Faculty");
        viewFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table t=new Table();
                t.setVisible(true);
                jdpDesktop.add(t);
            }
        });

        // Add items to the Faculty menu
        facultyMenu.add(addFaculty);
        facultyMenu.add(updateFaculty);
        facultyMenu.add(viewFaculty);

        // Add the Faculty menu to the menu bar
        jmb.add(facultyMenu);

        // Set the menu bar in the main frame
        setJMenuBar(jmb);

        // Connect to the database
        sqldb.connect();

        // Set the main frame visible
        setVisible(true);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(); // Create and show the main application window
            }
        });
    }
}
