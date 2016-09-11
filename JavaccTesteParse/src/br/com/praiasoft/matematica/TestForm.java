package br.com.praiasoft.matematica;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestForm {
	static EG1 parser = null;
	
	public static void main(String[] args) {
		JFrame guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Example GUI");
        guiFrame.setSize(300,600);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        //The first JPanel contains a label and text field for entering the expression
        final JPanel inputPanel = new JPanel();
        JLabel inputLbl = new JLabel("Enter your Expression: ");
        final JTextField inputText = new JTextField("");
        inputText.setColumns(20);
        
        inputPanel.add(inputLbl);
        inputPanel.add(inputText);
        
		// The bottom Panel contains a text box for showing results of a parse
        final JTextArea outputText = new JTextArea();
        outputText.setColumns(20);
        outputText.setRows(10);
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(inputPanel);
        mainPanel.add(outputText);
        
        // Textfield Action Listener callback - executed when user hits "return"
        inputText.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
                String sentence = inputText.getText();
                // Put parens around sentence so that parser knows scope
                sentence = "(" + sentence + ")";
                InputStream is = new ByteArrayInputStream(sentence.getBytes());
                if(parser == null) parser = new EG1(is);
                else EG1.ReInit(is);
                try
                {
                  switch (EG1.start())
                  {
                    case 0 :
                    	outputText.setText("expression parsed ok.");
                    break;
                    default :
                    break;
                  }
                }
                catch (Exception e)
                {
                  outputText.setText("error in expression.\n"+
                		  				e.getMessage());
                }
                catch (Error e)
                {
                 outputText.setText("error in expression.\n"+
    		  						   e.getMessage());
                }
                finally
                {
                  
                }
        	}
        });

        guiFrame.add(mainPanel, BorderLayout.NORTH);
        guiFrame.add(outputText, BorderLayout.CENTER);
        // Layout all component panels
        guiFrame.pack();
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
	}

}
