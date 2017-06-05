

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.swing.*;

public class Gui {
    public File filePath;
    public SimpleNode node;

   public JFrame frame = new JFrame("JDialog Demo");
    public JButton selectFileBtn = new JButton("Select File");
    public JButton dotBuildBtn = new JButton("Build .dot");
    public JButton lifetimeBtn= new JButton("Lifetime");
    public JButton graphColorBtn = new JButton("GraphColor");
    public JTextArea lifetimeArea = new JTextArea();
    public JTextArea messagesArea = new JTextArea();
    public JLabel filelabel = new JLabel("<html> <font color='white'>FileName</font></html>");
    public JTextArea fileText = new JTextArea("No file added");


    public Gui(){
        frame = new JFrame("JDialog Demo");
        selectFileBtn = new JButton("Select File");
        dotBuildBtn = new JButton("Build .dot");;
        filePath = null;
        JTextAreaOutputStream out = new JTextAreaOutputStream (messagesArea);

    }
    public  void config() {
        lifetimeArea.setSize(200,300);
        lifetimeArea.setEditable(false);
        lifetimeArea.setLineWrap(true);

        messagesArea.setSize(200,300);
        messagesArea.setEditable(false);
        messagesArea.setLineWrap(true);
        JTextAreaOutputStream out = new JTextAreaOutputStream (messagesArea);
    }
    public static void main(String[] args) throws IOException {
        Gui gui = new Gui();
        gui.display();
        gui.selectFileBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gui.filePath = FileBuilder.selectFile();
                        gui.fileText.setText(gui.filePath.getName());

                        try {
                            gui.node = TacParser.parseFile(gui.filePath);
                        } catch (FileNotFoundException a) {
                            a.printStackTrace();
                        } catch (ParseException a) {
                            a.printStackTrace();
                        }
                    }
                });

        gui.dotBuildBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gui.filePath != null) {
                            FileBuilder.buildDot(gui.node, gui.filePath);
                            JOptionPane.showMessageDialog(new Frame(),
                                    "File " + gui.filePath.getName().substring(0,gui.filePath.getName().indexOf(".")) + ".dot " + "created in: "  + " C:\\Users\\account-name\\Documents\\dot-files\\");
                        }
                        else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");
                    }
                });

        gui.lifetimeBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gui.lifetimeArea.setText("");
                        if(gui.filePath != null) {
                            JTextAreaOutputStream out = new JTextAreaOutputStream (gui.lifetimeArea);
                            System.setOut (new PrintStream(out));
                            LifetimeAnalysis lf = new LifetimeAnalysis(8,gui.node);
                            lf.printLifetimeGraph(gui.node);
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));



                        }else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");
                    }
                });

        gui.graphColorBtn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       /* if(gui.filePath != null)
                            FileBuilder.buildDot(gui.filePath);
                        else
                            JOptionPane.showMessageDialog(new Frame(),
                                    "Any file selected.");*/
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

        /*JPanel mainPanel2 = new JPanel();
        mainPanel2.setLayout(new BorderLayout());*/


        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.darkGray);
        northPanel.setLayout(new GridBagLayout());

        JPanel northPanel2 = new JPanel();
        northPanel2.setBackground(Color.darkGray);
        northPanel2.setLayout(new GridBagLayout());

        JScrollPane panelLifetime= new JScrollPane(lifetimeArea);
        mainPanel.add(panelLifetime, BorderLayout.CENTER);
     //   panel1.setSize(300,300);

       /* JScrollPane panel2= new JScrollPane(messagesArea);
        panel2.setSize(300,300);*/




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


        GridBagConstraints fileLabelPos = new GridBagConstraints();
        fileLabelPos.anchor = GridBagConstraints.LINE_START;
        fileLabelPos.fill = GridBagConstraints.HORIZONTAL;
        fileLabelPos.gridy = 1;
        fileLabelPos.weightx = 0.33;
        fileLabelPos.weighty = 1.0D;

        GridBagConstraints filenamePos = new GridBagConstraints();
        filenamePos.insets = new Insets(0, 10, 0, 0);
        filenamePos.anchor = GridBagConstraints.CENTER;
        filenamePos.fill = GridBagConstraints.HORIZONTAL;
        filenamePos.gridy = 1;
        filenamePos.weightx = 0.66;
        filenamePos.weighty = 1.0D;




        northPanel.add(selectFileBtn, select);
        northPanel.add(dotBuildBtn, build);
        northPanel.add(lifetimeBtn, lifetime);
        northPanel.add(graphColorBtn, graphColor);
        northPanel.add(filelabel, fileLabelPos);
        northPanel.add(fileText, filenamePos);



        mainPanel.add(BorderLayout.NORTH,northPanel);
        mainPanel.add(BorderLayout.AFTER_LAST_LINE,northPanel2);



       // mainPanel.add(panel2, BorderLayout.SOUTH);

        frame.add(mainPanel);

    }

    public void setTextBox(){



    }

}