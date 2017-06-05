import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ei10117 on 05/06/2017.
 */
public class FileBuilder {
    public  static JTextField  usernameChooser;
    public  static  JFrame    preFrame;
    public  static File selectedFile;


    public static File selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {

            return fileChooser.getSelectedFile();

        }
        return null;
    }


    public static void buildDot(SimpleNode node,File file){

        GeneratorCFG generatorCFG = new GeneratorCFG(node);
        generatorCFG.parseNodes();
        try {

            generatorCFG.saveFile(file.getName().substring(0,file.getName().indexOf(".")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LifetimeAnalysis buildLifetime(File file){
        SimpleNode node = null;


        LifetimeAnalysis lf = new LifetimeAnalysis(8, node);

        return lf;
    }



    static class enterServerButtonListener implements ActionListener {
        String  fileName;
        File file;
        public enterServerButtonListener(File file) {
            this.file = file;
        }

        public void actionPerformed(ActionEvent event) {
            fileName = usernameChooser.getText();
            if (fileName.length() < 1) {
                System.out.println("No!");
            } else {
                preFrame.setVisible(false);


                preFrame.dispose();
            }
        }

    }
}
