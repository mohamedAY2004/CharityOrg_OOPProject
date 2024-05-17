import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationInsertionWindow  {
    JFrame frame;
    private JComboBox<Integer> donorIDComboBox;
    private JTextField amountField;

    public DonationInsertionWindow()  {
        frame=new JFrame();
        frame.setTitle("Donation Information");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel donorIDLabel = new JLabel("Donor ID:");
        donorIDComboBox = new JComboBox<>();
        // Simulating donor IDs, you can replace it with actual donor IDs
        List<Integer> donorIDs = getDonorIDs();
        for (Integer id : donorIDs) {
            donorIDComboBox.addItem(id);
        }

        JLabel amountLabel = new JLabel("Donation Amount:");
        amountField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitDonation();

            }
        });
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DonationWindow();

            }
        });

        panel.add(donorIDLabel);
        panel.add(donorIDComboBox);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(backButton);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void submitDonation() {
        int donorID = (int) donorIDComboBox.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        Organiztion dummy=new Organiztion(null,null);
        try {
            Main.loadDonors(dummy);
            Main.loadDonations(dummy);
        dummy.addDonation(new Donation(amount, new Date(),donorID)) ;
        Main.WriteDonations(dummy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        amountField.setText("");
    }

    // Simulated list of donor IDs
    private List<Integer> getDonorIDs()  {
        List<Integer> donorIDs = new ArrayList<>();
        Organiztion dummy=new Organiztion(null,null);
        try {
            Main.loadDonors(dummy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dummy.getDonors().forEach(e->donorIDs.add(e.getDonorID()));
        return donorIDs;
    }
}
