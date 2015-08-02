/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import klassen.ImageFactory;

/**
 *
 * @author Stippler
 */
public abstract class EnemySpritzer
{
  private BufferedImage look;
  private Rectangle bounding;
  private float speedX;
  private float speedY;
  private float damage=20;
  private float knockBack=50;
  
  
  public EnemySpritzer(int x,int y,float speedX,float speedY)
  {
    bounding=new Rectangle(x, y, 0, 0);
    this.speedX=speedX;
    this.speedY=speedY;
    setLook(ImageFactory.getImageFactory().getLooks("spritzer"));
  }
  
  protected void setLook(BufferedImage look)
  {
    this.look = look;
    bounding=new Rectangle(bounding.x, bounding.y, look.getWidth(), look.getHeight());
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
