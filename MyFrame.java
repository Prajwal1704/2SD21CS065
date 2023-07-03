import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class represents a custom exception for handling illegal X values.
 */
class IllegalX extends Exception {
    public IllegalX(String r) {
        super(r);
    }

    public String toString() {
        return "Input Error";
    }
}

/**
 * This class extends JFrame and implements ActionListener to create a GUI for a
 * grade calculator.
 */
class Pool extends JFrame implements ActionListener {
    JFrame f;
    JButton b;
    Container contpane;
    JPanel p, p1, p2, p3, p4;
    JLabel l, ia1, ia2, ia3, cta, see, ind, l7, l8;
    JTextField t1, t2, t3, t4, t5;

    /**
     * Constructs a Pool object with a given title.
     * title the title of the JFrame
     */
    Pool(String title) {
        super(title);

        // Create components
        b = new JButton("Result!!");
        b.addActionListener(this);
        ia1 = new JLabel("    Enter the IA1 marks:");
        t1 = new JTextField(5);
        ia2 = new JLabel("    Enter the IA2 marks:");
        t2 = new JTextField(5);
        ia3 = new JLabel("    Enter the IA3 marks:");
        t3 = new JTextField(5);
        cta = new JLabel("    Enter the CTA marks:");
        t4 = new JTextField(5);
        see = new JLabel("    Enter the SEE marks:");
        t5 = new JTextField(5);
        ind = new JLabel("                                                  Grade Calculator   ");
        l7 = new JLabel("  Total Marks:  ");
        l8 = new JLabel("   Grade:  ");
        l = new JLabel();
        p = new JPanel();
        p1 = new JPanel();
        p3 = new JPanel();
        p2 = new JPanel();
        p4 = new JPanel();

        // Add components to panels
        p.add(ia1);
        p1.add(t1);
        p.add(ia2);
        p1.add(t2);
        p.add(ia3);
        p1.add(t3);
        p.add(cta);
        p1.add(t4);
        p.add(see);
        p1.add(t5);
        p.add(l7);
        p.add(l);
        p1.add(l8);
        p4.add(b);

        // Set layouts
        p.setLayout(new GridLayout(7, 2, 30, 30));
        p1.setLayout(new GridLayout(7, 2, 30, 30));

        contpane = this.getContentPane();
        contpane.add(p, BorderLayout.WEST);
        contpane.add(ind, BorderLayout.NORTH);
        contpane.add(p1, BorderLayout.CENTER);
        contpane.add(p3, BorderLayout.EAST);
        contpane.add(p4, BorderLayout.SOUTH);
    }

    /**
     * This method is invoked when the "Result!!" button is clicked.
     * It calculates the grade based on the entered marks and displays the result.
     */
    @Override
    public void actionPerformed(ActionEvent r) {
        try {
            // Parse input marks
            int ia1 = Integer.parseInt(t1.getText());
            int ia2 = Integer.parseInt(t2.getText());
            int ia3 = Integer.parseInt(t3.getText());
            int cta = Integer.parseInt(t4.getText());
            int see = Integer.parseInt(t5.getText());

            // Validate input marks
            if (ia1 < 0 || ia1 > 20 || ia2 < 0 || ia2 > 20 || ia3 < 0 || ia3 > 20) {
                throw new IllegalArgumentException("IA marks should be between 0 and 20.");
            }
            if (cta < 0 || cta > 10) {
                throw new IllegalArgumentException("CTA marks should be between 0 and 10.");
            }
            if (see < 0 || see > 100) {
                throw new IllegalArgumentException("SEE marks should be between 0 and 100.");
            }

            // CIE marks Caculation
            int cie = Math.max(Math.max(ia1, ia2), ia3) + Math.min(Math.min(ia1, ia2), ia3) + cta;

            if (cie < 20) {
                l7.setText("Student is detained from taking SEE");
                l8.setText("");
            } else {
                // Upgrade SEE marks if 38 or 39
                if (see == 38 || see == 39) {
                    see = 40;
                }

                // Total marks calculation
                double totalMarks = cie + Math.round((double) see / 2);

                // Grade calculation
                String grade;
                if (totalMarks >= 90 && totalMarks <= 100) {
                    grade = "S";
                } else if (totalMarks >= 80 && totalMarks < 90) {
                    grade = "A";
                } else if (totalMarks >= 70 && totalMarks < 80) {
                    grade = "B";
                } else if (totalMarks >= 60 && totalMarks < 70) {
                    grade = "C";
                } else if (totalMarks >= 50 && totalMarks < 60) {
                    grade = "D";
                } else if (totalMarks >= 40 && totalMarks < 50) {
                    grade = "E";
                } else {
                    grade = "F";
                }

                l7.setText("Total marks: " + totalMarks);
                l8.setText("Grade: " + grade);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Switf class will represent the main entry point of the program.
     */
    public class Swift {
        public static void main(String[] args) {
            JFrame f = new Pool("Student's Grade Calculator");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ImageIcon image = new ImageIcon("C:\\Users\\LENOVO\\Pictures\\Saved Pictures\\sdm logo.png");
            f.setIconImage(image.getImage());
            f.setBounds(100, 100, 500, 500);
            f.setVisible(true);
        }
    }
}
