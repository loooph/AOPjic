package minimizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Minimizewindow implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JFrame frame1;
	public JRadioButton karnaughbutton;
	public JRadioButton veitchbutton;
	public JTextPane output;
	public JTabbedPane functiontabs;
	public JButton closebutton;
	public JTextPane[] foutput;
	
	
	public Minimizewindow(ApplicationModel model) {
		frame1 = new JFrame();
		frame1.setSize(800, 400);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setTitle("KV-Diagramme");
		frame1.setVisible(true);
		
		initialiseGUI(model);
	}

	private void initialiseGUI(ApplicationModel model) {
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		frame1.add(panel1, BorderLayout.CENTER);
		
		JTabbedPane functiontabs = new JTabbedPane();
		Map kmaps = new KarnaughMap(model.getVariablesCount(),  new Font(Font.MONOSPACED, Font.BOLD, 24));
		
		try {
			setKMap(kmaps, model);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		kmaps.setBackground(Color.WHITE);
		panel1.add(kmaps, BorderLayout.CENTER);
		
		for(int i = model.getFunctionsCount(); i > 0; i--) {
			functiontabs.addTab("Ausgangsfunktion Y" + (model.getFunctionsCount()-i) + "", kmaps );
		}
		
		frame1.add(functiontabs, BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		frame1.add(rightPanel, BorderLayout.EAST);
		
		JLabel planmenu = new JLabel("Darstellungsart");
		rightPanel.add(planmenu);
		
		karnaughbutton = new JRadioButton("Karnaugh-Plan");
		karnaughbutton.setSelected(true);
		rightPanel.add(karnaughbutton);
		
		veitchbutton = new JRadioButton("Veitch-Diagramm   ");
		rightPanel.add(veitchbutton);
		
	    ButtonGroup method = new ButtonGroup();
	    method.add(karnaughbutton);
	    method.add(veitchbutton);
	    
	    JButton closebutton = new JButton();
	    closebutton.setText("Schlieﬂen");
	    closebutton.addActionListener(this);
	    rightPanel.add(closebutton);
	    
		JTextPane output = new JTextPane();
		output.setEditable(false);
		output.setText("Ausgangsfunktion:");
		output.setBackground(frame1.getBackground());
		SimpleAttributeSet s = new SimpleAttributeSet(); 
		StyleConstants.setBold(s, true);
		output.getStyledDocument().setCharacterAttributes(0, 17, s, false);
		Font f = new Font(Font.SANS_SERIF, 0, 16);
		output.setFont(f);
		frame1.add(output, BorderLayout.SOUTH);
		try {
			outputfunction(model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		if (source == closebutton) frame1.dispose();
	}

	private void outputfunction(ApplicationModel model) throws IOException {
		String inputFile = "min.pla";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			
			String variable;
			String outputtext = "";
			
			reader.readLine();
			reader.readLine();
			variable = reader.readLine();
			int fieldcounter = Integer.parseInt(variable.substring(3, 4));
			System.out.println(fieldcounter);

			
			while((variable = reader.readLine()) != null && !variable.startsWith("."))
			{
				for(int i = 0 ;  i < model.getVariablesCount(); i++) {
					switch(variable.substring(i, i+1).toString()) {
					case "0":
						outputtext = outputtext.concat("!X" + (model.getVariablesCount()-i-1));
						break;
					case "1":
						outputtext = outputtext.concat("X" + (model.getVariablesCount()-i-1));
						break;
					case "-":
						break;
					
					}

				}
				
//				foutput[fieldcounter] = new JTextPane();
//				foutput[fieldcounter].setEditable(false);
//				foutput[fieldcounter].setText(outputtext);
//				frame1.add(foutput[fieldcounter], BorderLayout.SOUTH);
				System.out.println(outputtext);
				outputtext = "";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	private void setKMap(Map kmaps, ApplicationModel model) throws IOException {
		String inputFile = "usr.pla";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			
			String variable;

			reader.readLine();
			reader.readLine();
			
			while((variable = reader.readLine()) != null && !variable.startsWith("."))
			{
				kmaps.setVal(variable.substring(0, model.getVariablesCount()), variable.substring(model.getVariablesCount()+1));
				System.out.println(variable.substring(0, model.getVariablesCount()));
				System.out.println(variable.substring(model.getVariablesCount()+1));
			}
			reader.close();
			kmaps.highlight(KMAPVAL.toKMAPVALArray("0---"), Color.GREEN, false);
			
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}