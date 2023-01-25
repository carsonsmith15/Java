package Main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Main
 */
public class Main 
{
    public static void main(String[] args) 
    {
        // new branch
        JFrame window = new JFrame();

        // lets us close the window properly
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // so we cannot resize the window
        window.setResizable(false);

        // sets the name of the window title
        window.setTitle("2D Adventure");        

        // Sets the title icon image 
        window.setIconImage(getIconImage());

        // Game Panel class (subclass)
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // causes the window to be sized to fit the preferred size
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }

    public static BufferedImage getIconImage()
    {
        BufferedImage image = null; 

        try {
            image = ImageIO.read(Main.class.getResourceAsStream("/Res/player/boy_down_1.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image; 
    }
}