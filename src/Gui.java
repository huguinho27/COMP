

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.swing.*;

public class Gui {
    public File filePath;

   public JFrame frame = new JFrame("JDialog Demo");
    public JButton selectFileBtn = new JButton("Select File");
    public JButton dotBuildBtn = new JButton("Build .dot");
    public JButton lifetimeBtn= new JButton("Lifetime");
    public JButton graphColorBtn = new JButton("GraphColor");
    public JTextArea area = new JTextArea("Welcome to javatpoint");


    public Gui(){
        frame = new JFrame("JDialog Demo");
        selectFileBtn = new JButton("Select File");
        dotBuildBtn = new JButton("Build .dot");;
        filePath = null;
    }
    public  void config() {
        area.setBounds(10, 30, 200, 200);
        area.setEditable(false);
        area.setLineWrap(true);
    }
    public static void main(String[] args) throws IOException {
        Gui gui = new Gui();
        gui.display();
        gui.selectFileBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gui.filePath = FileBuilder.selectFile();
                    }
                });

        gui.dotBuildBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gui.filePath != null)
                        FileBuilder.buildDot(gui.filePath);
                        else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");
                    }
                });

        gui.lifetimeBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gui.filePath != null)
                            FileBuilder.buildDot(gui.filePath);
                        else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");
                    }
                });

        gui.graphColorBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gui.filePath != null)
                            FileBuilder.buildDot(gui.filePath);
                        else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");
                    }
                });

        gui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.frame.setLocationRelativeTo(null);
        gui.frame.setSize(500, 500);
        gui.frame.setVisible(true);
    }


    public void display(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.darkGray);
        northPanel.setLayout(new GridBagLayout());

        mainPanel.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(mainPanel);

        GridBagConstraints select = new GridBagConstraints();
        select.anchor = GridBagConstraints.FIRST_LINE_START;
        select.fill = GridBagConstraints.HORIZONTAL;
        select.gridy = 0;
        select.weightx = 0.33;
        select.weighty = 1.0D;

        GridBagConstraints build = new GridBagConstraints();
        build.insets = new Insets(0, 10, 0, 0);
        build.anchor = GridBagConstraints.PAGE_START;
        build.fill = GridBagConstraints.HORIZONTAL;
        build.gridy = 0;
        build.weightx = 0.33;
        build.weighty = 1.0D;

        GridBagConstraints lifetime = new GridBagConstraints();
        lifetime.insets = new Insets(0, 10, 0, 0);
        lifetime.anchor = GridBagConstraints.FIRST_LINE_END;
        lifetime.fill = GridBagConstraints.HORIZONTAL;
        lifetime.gridy = 0;
        lifetime.weightx = 0.33;
        lifetime.weighty = 1.0D;

        GridBagConstraints graphColor = new GridBagConstraints();
        graphColor.insets = new Insets(0, 10, 0, 0);
        graphColor.anchor = GridBagConstraints.FIRST_LINE_END;
        graphColor.fill = GridBagConstraints.HORIZONTAL;
        graphColor.gridy = 0;
        graphColor.weightx = 0.33;
        graphColor.weighty = 1.0D;




        northPanel.add(selectFileBtn, select);
        northPanel.add(dotBuildBtn, build);
        northPanel.add(lifetimeBtn, lifetime);
        northPanel.add(graphColorBtn, graphColor);

        mainPanel.add(BorderLayout.NORTH,northPanel);

        frame.add(mainPanel);

    }

}