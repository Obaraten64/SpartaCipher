import add.InterfaceInitializer;

import javax.swing.*;

import java.awt.*;

public class SpartaCipherMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Sparta cipher");
                //set icon img
                ImageIcon img = new ImageIcon(getClass().getResource("/resources/Coffe.jpg"));
                f.setIconImage(img.getImage());
                //set icon img
                f.setTitle("Sparta cipher");
                f.setLocation(650, 200);
                f.setSize(new Dimension(750, 750));
                f.setLayout(new GridBagLayout());
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                //create bg
                JLabel bgLabel = InterfaceInitializer.createBG(f, img);
                f.setContentPane(bgLabel);
                //create bg
                SpartaScreens.mainScreen(f);
            }
        });
    }
}
