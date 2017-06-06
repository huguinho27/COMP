
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Gui {
	public File filePath;
	public SimpleNode node;

	private JFrame frame;
	private JButton selectFileBtn;
	private JButton dotBuildBtn;
	private JButton lifetimeBtn;
	private JButton graphColorBtn;
	private JTextArea lifetimeArea;
	private JTextArea messagesArea;
	private JLabel filelabel;
	private JTextArea fileText;
	private static JSlider slider;

	public Gui() {
		frame = new JFrame("Three-Address Code");
		selectFileBtn = new JButton("Select File");
		dotBuildBtn = new JButton("Build .dot and .c");
		lifetimeBtn = new JButton("Lifetime");
		graphColorBtn = new JButton("GraphColor");
		lifetimeArea = new JTextArea();
		messagesArea = new JTextArea();
		filelabel = new JLabel("<html> <font color='white'>FileName</font></html>");
		fileText = new JTextArea("Please select a file");
		slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 4);

		filePath = null;
		JTextAreaOutputStream out = new JTextAreaOutputStream(messagesArea);

		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent ce) {
				JSlider slider = (JSlider) ce.getSource();
				if (!slider.getValueIsAdjusting()) {
					slider.setToolTipText(Integer.toString(slider.getValue()));
				}
			}
		});

	}

	public void config() {
		lifetimeArea.setSize(200, 300);
		lifetimeArea.setEditable(false);
		lifetimeArea.setLineWrap(true);

		messagesArea.setSize(200, 300);
		messagesArea.setEditable(false);
		messagesArea.setLineWrap(true);
		JTextAreaOutputStream out = new JTextAreaOutputStream(messagesArea);
	}

	public static void main(String[] args) throws IOException {
		Gui gui = new Gui();
		gui.display();
		gui.selectFileBtn.addActionListener(new ActionListener() {
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

		gui.dotBuildBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.filePath != null) {
					FileBuilder.buildDot(gui.node, gui.filePath);
					JOptionPane.showMessageDialog(new Frame(),
							"Dot File " + gui.filePath.getName().substring(0, gui.filePath.getName().indexOf("."))
									+ ".dot " + "created in: "
									+ " C:\\Users\\account-name\\Documents\\TacParserOutput\\");
					try {

						TacToC.saveFile(gui.node, gui.filePath.getName().substring(0,gui.filePath.getName().indexOf(".")));
						JOptionPane.showMessageDialog(new Frame(),
								"C File " + gui.filePath.getName().substring(0, gui.filePath.getName().indexOf("."))
										+ ".c " + "created in: "
										+ " C:\\Users\\account-name\\Documents\\TacParserOutput\\");
					} catch (IOException e1) {

					}
				} else
					JOptionPane.showMessageDialog(new Frame(), "No file selected.");
			}
		});

		gui.lifetimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.lifetimeArea.setText("");
				if (gui.filePath != null) {
					JTextAreaOutputStream out = new JTextAreaOutputStream(gui.lifetimeArea);
					System.setOut(new PrintStream(out));
					LifetimeAnalysis lf = new LifetimeAnalysis(8, gui.node);
					lf.printLifetimeGraph(gui.node);
					System.out.println();
					System.out.println();
					System.out.println();
					System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

				} else
					JOptionPane.showMessageDialog(new Frame(), "No file selected.");
			}
		});

		gui.graphColorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gui.filePath != null) {
					int registers = slider.getValue();
					try {
						GraphColoring gc = new GraphColoring(registers, gui.filePath);
					} catch (FileNotFoundException | ParseException e1) {
						e1.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(new Frame(), "No file selected.");
			}
		});

		gui.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.frame.setLocationRelativeTo(null);
		gui.frame.setSize(500, 500);
		gui.frame.setVisible(true);
	}

	public void display() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		/*
		 * JPanel mainPanel2 = new JPanel(); mainPanel2.setLayout(new
		 * BorderLayout());
		 */

		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.darkGray);
		northPanel.setLayout(new GridBagLayout());

		JPanel northPanel2 = new JPanel();
		northPanel2.setBackground(Color.darkGray);
		northPanel2.setLayout(new GridBagLayout());

		JScrollPane panelLifetime = new JScrollPane(lifetimeArea);
		mainPanel.add(panelLifetime, BorderLayout.CENTER);
		// panel1.setSize(300,300);

		/*
		 * JScrollPane panel2= new JScrollPane(messagesArea);
		 * panel2.setSize(300,300);
		 */

		GridBagConstraints select = new GridBagConstraints();
		select.anchor = GridBagConstraints.FIRST_LINE_START;
		select.fill = GridBagConstraints.HORIZONTAL;
		// select.gridy = 0;
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
		// lifetime.gridy = 0;
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
		fileLabelPos.weighty = 1.5;

		GridBagConstraints filenamePos = new GridBagConstraints();
		filenamePos.insets = new Insets(0, 10, 0, 0);
		filenamePos.anchor = GridBagConstraints.CENTER;
		filenamePos.fill = GridBagConstraints.HORIZONTAL;
		filenamePos.gridy = 1;
		filenamePos.weightx = 0.66;
		filenamePos.weighty = 1.5;

		GridBagConstraints sliderPos = new GridBagConstraints();
		sliderPos.insets = new Insets(0, 10, 0, 0);
		sliderPos.anchor = GridBagConstraints.LINE_END;
		sliderPos.fill = GridBagConstraints.HORIZONTAL;
		sliderPos.gridy = 1;
		sliderPos.weightx = 1.6;
		sliderPos.weighty = 1.5;

		GridBagConstraints sliderlabelPos = new GridBagConstraints();
		sliderlabelPos.insets = new Insets(0, 10, 0, 0);
		sliderlabelPos.anchor = GridBagConstraints.LINE_END;
		sliderlabelPos.fill = GridBagConstraints.HORIZONTAL;
		sliderlabelPos.gridy = 1;
		sliderlabelPos.weightx = 1.6;
		sliderlabelPos.weighty = 1.5;

		northPanel.add(selectFileBtn, select);
		northPanel.add(dotBuildBtn, build);
		northPanel.add(lifetimeBtn, lifetime);
		northPanel.add(graphColorBtn, graphColor);
		northPanel.add(filelabel, fileLabelPos);
		northPanel.add(fileText, filenamePos);
		northPanel.add(slider, sliderPos);

		mainPanel.add(BorderLayout.NORTH, northPanel);
		mainPanel.add(BorderLayout.AFTER_LAST_LINE, northPanel2);

		// mainPanel.add(panel2, BorderLayout.SOUTH);

		frame.add(mainPanel);

	}

	public void setTextBox() {

	}

}