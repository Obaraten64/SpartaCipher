import add.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpartaScreens {
    public static void mainScreen(JFrame f) {
        GridBagConstraints gdc = InterfaceInitializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel("Select between encoding and decoding your text");
        label.setOpaque(true);
        f.add(label, gdc);

        gdc.anchor = GridBagConstraints.WEST;
        gdc.gridy++;
        JButton codeButton = new JButton("Code");
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpartaScreens.secondScreen(f, "Code");
            }
        });
        f.add(codeButton, gdc);

        gdc.anchor = GridBagConstraints.EAST;
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpartaScreens.secondScreen(f, "Decode");
            }
        });
        f.add(decodeButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void secondScreen(JFrame f, String chosen) {
        GridBagConstraints gdc = InterfaceInitializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel keyLabel = new JLabel(((char)0x2193) + " Enter your key here " + ((char)0x2193));
        keyLabel.setOpaque(true);
        f.add(keyLabel, gdc);
        gdc.gridx += 2;

        JLabel textLabel = new JLabel(((char)0x2193) + " Enter your text here " + ((char)0x2193));
        textLabel.setOpaque(true);
        f.add(textLabel, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextArea inputKey = new JTextArea(1, 20);
        inputKey.setEditable(true);
        inputKey.setBackground(Color.LIGHT_GRAY);
        f.add(inputKey, gdc);
        gdc.gridx += 2;

        JTextArea inputText = new JTextArea(1, 20);
        inputText.setEditable(true);
        inputText.setBackground(Color.LIGHT_GRAY);
        f.add(inputText, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextPane exampleKey = new JTextPane();
        exampleKey.setText("Example: \"4\"");
        exampleKey.setEditable(false);
        f.add(exampleKey, gdc);
        gdc.gridx += 2;

        JTextPane exampleText = new JTextPane();
        exampleText.setText("Example: \"Це шифр палиця\"");
        exampleText.setEditable(false);
        f.add(exampleText, gdc);
        gdc.gridx--;
        gdc.gridy++;

        //symbols limits
        inputText.addKeyListener(new TextConsumer(inputText, 50));
        inputKey.addKeyListener(new TextConsumer(inputKey, 2));
        //symbols limits

        JButton button = new JButton(chosen);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int key = Integer.parseInt(inputKey.getText());
                    String str = switch (chosen) {
                        case ("Code") -> SpartaCipher.code(inputText.getText(), key);
                        case ("Decode") -> SpartaCipher.decode(inputText.getText(), key);
                        default -> throw new IllegalStateException("Unexpected value: " + chosen);
                    };
                    SpartaScreens.thirdScreen(f, str);
                } catch (Exception exception) {
                    SpartaCipher.makeMatrixNull();
                    SpartaScreens.thirdScreen(f, "ERROR: Key is not number");
                }
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void thirdScreen(JFrame f, String output) {
        GridBagConstraints gdc = InterfaceInitializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JPanel panelForButtons = new JPanel(new GridLayout(0, 1));
        //panel struct
        JTextArea textArea = new JTextArea(1, 20);
        textArea.setText(output);
        textArea.setEditable(false);
        panelForButtons.add(textArea, gdc);

        JButton button = new JButton("Go to main screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpartaScreens.mainScreen(f);
            }
        });
        panelForButtons.add(button, gdc);
        //panel struct
        gdc.weighty = 1.0;
        f.add(panelForButtons, gdc);

        gdc.gridy++;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.PAGE_END;

        JButton matrixButton = new JButton("Show matrix");
        matrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpartaScreens.additionalScreen(f, output);
            }
        });
        f.add(matrixButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void additionalScreen(JFrame f, String output) {
        GridBagConstraints gdc = InterfaceInitializer.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel();
        label.setText(SpartaCipher.showMatrix());
        label.setOpaque(true);
        f.add(label, gdc);
        gdc.gridy++;

        JButton button = new JButton("Back to previous screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpartaScreens.thirdScreen(f, output);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }
}
