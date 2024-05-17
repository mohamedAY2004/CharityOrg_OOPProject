import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.MarshalledObject;

public class VolunteerWindow implements ActionListener {
    JFrame frame;
    JButton AddVolunteer;
    JButton DisplayVolunteer;
    JButton Back;
    JTextArea ff;
    VolunteerWindow()
    {
        ff=new JTextArea();
        frame =new JFrame();
        AddVolunteer =new JButton("Add Volunteer");
        AddVolunteer.setPreferredSize(new Dimension(510,110));
        AddVolunteer.setFocusable(false);
        AddVolunteer.addActionListener(this);
        DisplayVolunteer =new JButton("Display Volunteer");
        DisplayVolunteer.setPreferredSize(new Dimension(510,110));
        DisplayVolunteer.setFocusable(false);
        DisplayVolunteer.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,500);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setFocusable(false);
        ff.setPreferredSize(new Dimension(1400,500));
        frame.add(AddVolunteer);
        frame.add(DisplayVolunteer);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddVolunteer)
        {

        } else if (e.getSource()== this.DisplayVolunteer)
        {
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
            ff.append(tmp.getVolunteers().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
