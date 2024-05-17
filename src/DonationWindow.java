import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DonationWindow implements ActionListener {
    JFrame frame;
    JButton AddDonation;
    JButton DisplayDonations;
    JButton Back;
    JTextArea ff;
    DonationWindow()
    {
        frame =new JFrame();
        ff=new JTextArea();
        AddDonation =new JButton("Add Donations");
        AddDonation.setPreferredSize(new Dimension(510,110));
        AddDonation.setFocusable(false);
        AddDonation.addActionListener(this);
        DisplayDonations =new JButton("Display Donations");
        DisplayDonations.setPreferredSize(new Dimension(510,110));
        DisplayDonations.setFocusable(false);
        DisplayDonations.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,600);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setPreferredSize(new Dimension(1400,500));
        ff.setFocusable(false);
        frame.add(AddDonation);
        frame.add(DisplayDonations);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddDonation)
        {
            frame.dispose();
            new DonationInsertionWindow();
        } else if (e.getSource()== this.DisplayDonations)
        {
            DisplayDonations.setEnabled(false);
            Organiztion tmp=new Organiztion("tmp","tmp");
            try {
                Main.loadDonors(tmp);
                Main.loadDonations(tmp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ff.append(tmp.getDonations().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
