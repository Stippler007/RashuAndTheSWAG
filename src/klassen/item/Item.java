/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.item;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.ImageFactory;
import klassen.player.PlayerSpritzer;

/**
 *
 * @author Stippler
 */
public class Item
{
  private static BufferedImage look;
  private Rectangle bounding;
  
  
  static
  {
    look=ImageFactory.getImageFactory().getLooks("illuminatitriangle");
  }
  public Item(int x,int y)
  {
    bounding=new Rectangle(x, y, look.getWidth(), look.getHeight());
  }

  public Rectangle getBounding()
  {
    return bounding;
  }

  public static BufferedImage getLook()
  {
    return look;
  }
  
}
