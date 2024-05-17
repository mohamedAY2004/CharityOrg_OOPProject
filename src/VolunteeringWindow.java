import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VolunteeringWindow implements ActionListener {
    JFrame frame;
    JButton AddVolunteering;
    JButton DisplayVolunteering;
    JButton Back;
    JTextArea ff;
    VolunteeringWindow()
    {
        ff=new JTextArea();
        frame =new JFrame();
        AddVolunteering =new JButton("Add Volunteering");
        AddVolunteering.setPreferredSize(new Dimension(510,110));
        AddVolunteering.setFocusable(false);
        AddVolunteering.addActionListener(this);
        DisplayVolunteering =new JButton("Display Volunteering");
        DisplayVolunteering.setPreferredSize(new Dimension(510,110));
        DisplayVolunteering.setFocusable(false);
        DisplayVolunteering.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,500);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setFocusable(false);
        ff.setPreferredSize(new Dimension(1400,500));
        frame.add(AddVolunteering);
        frame.add(DisplayVolunteering);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddVolunteering)
        {

        } else if (e.getSource()== this.DisplayVolunteering)
        {
            DisplayVolunteering.setEnabled(false);
            Organiztion tmp=new Organiztion("tmp","tmp");
            try {
                Main.loadVolunteers(tmp);
                Main.loadPrograms(tmp);
                Main.loadVolunteerings(tmp);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            ff.append(tmp.getVolunteerings().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
