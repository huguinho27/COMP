import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ei10117 on 05/06/2017.
 */
public class FileChoose {
    public  JTextField  usernameChooser;
    public   JFrame    preFrame;
    public  File selectedFile;
    public static void main(String [] args) throws IOException, ParseException {
        JFileChooser fileChooser = new JFileChooser();
        FileChoose fileChoose = new FileChoose();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
                fileChoose.selectedFile = fileChooser.getSelectedFile();
                fileChoose.setName();
        }
    }


    public  void setName(){
        //frame studd
        preFrame = new JFrame("Name file");
        JPanel prePanel = new JPanel(new GridBagLayout());
        JLabel chooseUsernameLabel = new JLabel("File Name");
        JButton enterServer = new JButton("Save name");
        enterServer.addActionListener(new enterServerButtonListener());


        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(0, 0, 0, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(0, 10, 0, 10);
        // preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;


        usernameChooser = new JTextField(15);
        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);


        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setVisible(true);
        preFrame.setSize(300, 300);
    }


    String  fileName;
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            fileName = usernameChooser.getText();
            if (fileName.length() < 1) {
                System.out.println("No!");
            } else {
                preFrame.setVisible(false);
                SimpleNode node = null;
                try {
                    node = TacParser.parseFile(selectedFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                GeneratorCFG generatorCFG = new GeneratorCFG(node);
                generatorCFG.parseNodes();
                try {
                    generatorCFG.saveFile(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);

            }
        }

    }
}
