/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import klassen.ImageFactory;
import klassen.KL;
import com.sun.glass.events.KeyEvent;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Stippler
 */
public class PlayerSpritzer
{
  private static BufferedImage look;
  private Rectangle bounding;
  private float speedX;
  private float speedY;
  private float damage=20;
  private float knockBack=50;
  
  static
  {
    look=ImageFactory.getImageFactory().getLooks("spritzer");
  }
  public PlayerSpritzer(int x,int y,float speedX,float speedY)
  {
    bounding=new Rectangle(x, y, look.getWidth(), look.getHeight());
    this.speedX=speedX;
    this.speedY=speedY;
  }
  public void update(float tslf)
  {
    bounding.x+=speedX*tslf;
    bounding.y+=speedY*tslf;
  }

  public float getKnockBack()
  {
    return knockBack;
  }
  
  public Rectangle getBounding()
  {
    return bounding;
  }

  public BufferedImage getLook()
  {
    return look;
  }

  public float getDamage()
  {
    return damage;
  }
  
}
