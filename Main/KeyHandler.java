package Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

/**
 * KeyboardHandler
 */
public class KeyHandler implements KeyListener 
{
    private boolean upPressed;
    private boolean downPressed;  
    private boolean leftPressed;  
    private boolean rightPressed;  
    private boolean spacePressed;  
    private boolean shootKeyPressed;  

    public boolean getUpPressed()
    {
        return upPressed; 
    }

    public void setUpPressed()
    {
        upPressed = !upPressed; 
    }    
   
    public boolean getDownPressed()
    {
        return downPressed; 
    }

    public void setDownPressed()
    {
        downPressed = !downPressed; 
    }  

    public boolean getLeftPressed()
    {
        return leftPressed; 
    }

    public void setLeftPressed()
    {
        leftPressed = !leftPressed; 
    }
  
    public boolean getRightPressed()
    {
        return rightPressed; 
    }

    public void setRightPressed()
    {
        rightPressed = !rightPressed; 
    }

    public boolean getSpacePressed()
    {
        return spacePressed; 
    }

    public void setSpacePressed()
    {
        spacePressed = !spacePressed; 
    }

    public boolean getShootKeyPressed()
    {
        return shootKeyPressed; 
    }

    public void setShootKeyPressed()
    {
        shootKeyPressed = !shootKeyPressed; 
    }  

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    { 
        int code = e.getKeyCode();  

        if (code == KeyEvent.VK_F)
        {
            shootKeyPressed = true; 
        }
        if (code == KeyEvent.VK_SPACE)
        {
            spacePressed = true;            
        }
        if (code == KeyEvent.VK_UP) 
        {
            upPressed = true; 
        }
        if (code == KeyEvent.VK_DOWN) 
        {
            downPressed = true; 
        }
        if (code == KeyEvent.VK_LEFT) 
        {
            leftPressed = true; 
        }
        if (code == KeyEvent.VK_RIGHT) 
        {
            rightPressed = true; 
        }   
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {  
        int code = e.getKeyCode(); 

        if (code == KeyEvent.VK_SPACE)
        {
            spacePressed = false;        
        }
        if (code == KeyEvent.VK_F)
        {
            shootKeyPressed = false; 
        }
        if (code == KeyEvent.VK_UP) 
        {
            upPressed = false; 
        }
        if (code == KeyEvent.VK_DOWN) 
        {
            downPressed = false; 
        }
        if (code == KeyEvent.VK_RIGHT) 
        {
            rightPressed = false; 
        }
        if (code == KeyEvent.VK_LEFT) 
        {
            leftPressed = false; 
        }
    }    
}