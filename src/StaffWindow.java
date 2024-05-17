import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StaffWindow implements ActionListener {
    JFrame frame;
    JButton AddStaff;
    JButton DisplayStaff;
    JButton Back;
    JTextArea ff;
    StaffWindow()
    {
        ff=new JTextArea();
        frame =new JFrame();
        AddStaff =new JButton("Add Staff");
        AddStaff.setPreferredSize(new Dimension(510,110));
        AddStaff.setFocusable(false);
        AddStaff.addActionListener(this);
        DisplayStaff =new JButton("Display Staff");
        DisplayStaff.setPreferredSize(new Dimension(510,110));
        DisplayStaff.setFocusable(false);
        DisplayStaff.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,500);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setFocusable(false);
        ff.setPreferredSize(new Dimension(1400,500));
        frame.add(AddStaff);
        frame.add(DisplayStaff);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddStaff)
        {
            frame.dispose();
            new StaffInsertionWindow();

        } else if (e.getSource()== this.DisplayStaff)
        {
            DisplayStaff.setEnabled(false);
            Organiztion tmp=new Organiztion("tmp","tmp");
            try {
               Main.loadStaff(tmp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ff.append(tmp.getStaff().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
