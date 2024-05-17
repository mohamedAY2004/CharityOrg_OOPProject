import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ProgramWindow implements ActionListener {
    JFrame frame;
    JButton AddProgram;
    JButton DisplayPrograms;
    JButton Back;
    JTextArea ff;
    ProgramWindow()
    {
        frame =new JFrame();
        ff=new JTextArea();
        AddProgram =new JButton("Add Program");
        AddProgram.setPreferredSize(new Dimension(510,110));
        AddProgram.setFocusable(false);
        AddProgram.addActionListener(this);
        DisplayPrograms =new JButton("Display Program");
        DisplayPrograms.setPreferredSize(new Dimension(510,110));
        DisplayPrograms.setFocusable(false);
        DisplayPrograms.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,500);
        frame.setLayout(new FlowLayout());
        Back =new JButton("Back");
        Back.setPreferredSize(new Dimension(510,110));
        Back.setFocusable(false);
        Back.addActionListener(this);
        ff.setFocusable(false);
        ff.setPreferredSize(new Dimension(1400,500));
        frame.add(AddProgram);
        frame.add(DisplayPrograms);
        frame.add(Back);
        frame.add(ff);
        frame.setVisible(true);

    }
    private void loadPrograms(Organiztion org) throws IOException, ClassNotFoundException {
        FileInputStream filein=new FileInputStream("Program.ser");
        ObjectInputStream in = new ObjectInputStream(filein);
        while (true){
            try{
                org.addProgram((Program) in.readObject());
            }catch (EOFException e)
            {
                break;
            }
        }
        filein.close();
        in.close();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== this.AddProgram)
        {

        } else if (e.getSource()== this.DisplayPrograms)
        {
            DisplayPrograms.setEnabled(false);
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
           ff.append(tmp.getPrograms().toString());
        } else if (e.getSource()== this.Back)
        {
            frame.dispose();
            new LaunchPage();
        }
    }
}
