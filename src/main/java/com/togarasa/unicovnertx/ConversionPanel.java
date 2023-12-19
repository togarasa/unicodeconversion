package main.java.com.togarasa.unicovnertx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ConversionPanel extends JPanel {

    private JTextArea inputArea, outputArea;
    private JComboBox<String> formatComboBox;

    public ConversionPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        formatComboBox = new JComboBox<>(new String[]{"Decimal", "Hex", "Octal", "HTML"});
        inputArea = new JTextArea();
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton convertButton = new JButton("Convert to Unicode");
        convertButton.addActionListener(e -> convertToUnicode());

        // Create a new button for the example
        JButton exampleButton = new JButton("Example");
        exampleButton.addActionListener(e -> showExample());
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearInput());
       
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(formatComboBox, gbc);
        
        
        // Example button
        gbc.gridx = 1;
        add(exampleButton, gbc);
        
        gbc.gridx = 2;
        add(clearButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(inputArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JScrollPane(outputArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 0.0;
        add(convertButton, gbc);
        
        gbc.gridx = 1; // Adjust grid position
    }
    
    private void convertToUnicode() {
    	 try {
             String inputText = inputArea.getText().trim();
             String unicodeText = "";

             switch (formatComboBox.getSelectedItem().toString()) {
                 case "Decimal":
                     unicodeText = ConversionUtil.decimalToUnicode(inputText);
                     break;
                 case "Hex":
                     unicodeText = ConversionUtil.hexToUnicode(inputText);
                     break;
                 case "Octal":
                     unicodeText = ConversionUtil.octalToUnicode(inputText);
                     break;
                 case "HTML":
                     unicodeText = ConversionUtil.htmlToUnicode(inputText);
                     break;
             }

             outputArea.setText(unicodeText);
         } catch (Exception ex) {
             showExceptionPopup(ex);
         }
    }
    
    private void showExample() {
    	 String exampleText = "";
         switch (formatComboBox.getSelectedItem().toString()) {
             case "Decimal":
                 exampleText = "72 101 108 108 111"; // ASCII values for "Hello"
                 break;
             case "Hex":
                 exampleText = "48 65 6C 6C 6F"; // ASCII values in Hex for "Hello"
                 break;
             case "Octal":
                 exampleText = "110 145 154 154 157"; // ASCII values in Octal for "Hello"
                 break;
             case "HTML":
                 exampleText = "&#72;&#101;&#108;&#108;&#111;"; // HTML entities for "Hello"
                 break;
         }
        inputArea.setText(exampleText);
    }
    
    private void clearInput() {
        inputArea.setText("");
        outputArea.setText("");
    }
    
    private void showExceptionPopup(Exception exception) {
        String message = "An error occurred: " + exception.getMessage();
        String stackTrace = getStackTraceString(exception);

        JOptionPane.showMessageDialog(this, message + "\n\n" + stackTrace, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String getStackTraceString(Exception exception) {
        StringBuilder stackTrace = new StringBuilder();
        for (StackTraceElement element : exception.getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        return stackTrace.toString();
    }
}