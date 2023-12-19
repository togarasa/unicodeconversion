package main.java.com.togarasa.unicovnertx;

import javax.swing.*;
import java.awt.*;

public class UnicodeConversionApp extends JFrame {

    public UnicodeConversionApp() {
        setTitle("Multi-Format Unicode Conversion App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        ConversionPanel conversionPanel = new ConversionPanel();
        setLayout(new BorderLayout());
        add(conversionPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UnicodeConversionApp());
    }

}



