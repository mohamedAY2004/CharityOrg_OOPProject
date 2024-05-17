import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class DonorWindow implements ActionListener {
    JFrame frame;
    JButton AddDonor;
    JButton DisplayDonor;
    JButton Back;
    JTextArea ff;
    DonorWindow()
    {
        frame =new JFrame();
        ff=new JTextArea();
        AddDonor=new JButton("Add Donor");
        AddDonor.setPreferredSize(new Dimension(510,110));
        AddDonor.setFocusable(false);
        AddDonor.addActionListener(this);
        DisplayDonor=new JButton("Display Donor");
        DisplayDonor.setPreferredSize(new Dimension(510,110));
        DisplayDonor.setFocusable(false);
        DisplayDonor.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,600);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setPreferredSize(new Dimension(1400,500));
        ff.setFocusable(false);
        frame.add(AddDonor);
        frame.add(DisplayDonor);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddDonor)
        {
            frame.dispose();
            new DonorInsertionWindow();

        } else if (e.getSource()== this.DisplayDonor)
        {
            DisplayDonor.setEnabled(false);
            Organiztion tmp=new Organiztion("tmp","tmp");
            try {
                Main.loadDonors(tmp);
                Main.loadDonations(tmp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ff.append(tmp.getDonors().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
