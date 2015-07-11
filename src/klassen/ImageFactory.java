/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class ImageFactory
{
  private static ImageFactory imageFactory;

  private HashMap<String,BufferedImage> looks=new HashMap<String, BufferedImage>();
  private ImageFactory()
  {
    try
    {
      looks.put("player",ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player/player.jpg")));
      looks.put("background1", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/background/illuminatihitler.jpg")));
      looks.put("spritzer", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/player/spritzer.png")));
      looks.put("enemynotfound", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/enemynotfound.png")));
      looks.put("angrybaguette", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/baguette/angrybaguette.jpg")));
      looks.put("gameover", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/gameOverScreen/gameover.jpg")));
      looks.put("illuminatitriangle", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/item/illuminatitriangle.jpg")));
      looks.put("dildo", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/sword/pornstar/dildo.jpg")));
      looks.put("pornstar", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/sword/pornstar/pornstar.png")));
      looks.put("saber", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/sword/pirate/saber.png")));
      looks.put("teegirl", ImageIO.read(getClass().getClassLoader().getResourceAsStream("gfx/enemy/sword/pirate/teegirl2.JPG")));
    } catch (IOException ex)
    {
      Logger.getLogger(ImageFactory.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static ImageFactory getImageFactory()
  {
    if(imageFactory==null)
    {
      imageFactory=new ImageFactory();
    }
    return imageFactory;
  }

  public BufferedImage getLooks(String str)
  {
    return looks.get(str);
  }
  
}
