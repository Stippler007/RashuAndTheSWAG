/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.player;

import klassen.ImageFactory;
import klassen.KL;
import klassen.enemy.Enemy;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.event.KeyEvent;

/**
 *
 * @author Stippler
 */
public class Player
{
  public static int swagcounter=0;
  
  private static BufferedImage look;
  private Rectangle bounding;
  private float speed;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  
  private float reloadTime=0f;
  private final float maxReloadTime=0.3f;
  
  private float knockBackX;
  private float knockBackY;
  private float backKnockBack=0.5f;
  
  private float health=100;
  private float maxHelath=100;
  
  
  static
  {
    look=ImageFactory.getImageFactory().getLooks("player");
  }
  public Player(int x,int y,float speed,LinkedList<PlayerSpritzer> playerSpritzers)
  {
    bounding=new Rectangle(x, y, look.getWidth(), look.getHeight());
    this.speed=speed;
    this.playerSpritzers=playerSpritzers;
  }
  public void update(float tslf) //tslf time since last frame
  {
    if(KL.keys[KeyEvent.VK_W])
    {
      bounding.y-=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_S])
    {
      bounding.y+=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_A])
    {
      bounding.x-=speed*tslf;
    }
    if(KL.keys[KeyEvent.VK_D])
    {
      bounding.x+=speed*tslf;
    }
    
    float spritzerSpeed=speed*2;
    if(reloadTime>maxReloadTime)
    {
      if(KL.keys[KeyEvent.VK_UP])
      {
        playerSpritzers.add(new PlayerSpritzer(bounding.x+15, bounding.y+15, 0, -spritzerSpeed));
      }
      else if(KL.keys[KeyEvent.VK_DOWN])
      {
        playerSpritzers.add(new PlayerSpritzer(bounding.x+15, bounding.y+15, 0, spritzerSpeed));
      }
      else if(KL.keys[KeyEvent.VK_RIGHT])
      {
        playerSpritzers.add(new PlayerSpritzer(bounding.x+15, bounding.y+15, spritzerSpeed, 0));
      }
      else if(KL.keys[KeyEvent.VK_LEFT])
      {
        playerSpritzers.add(new PlayerSpritzer(bounding.x+15, bounding.y+15, -spritzerSpeed, 0));
      }
      reloadTime-=maxReloadTime;
    }
    else
    {
      reloadTime+=tslf;
    }
    
    if(knockBackX!=0||knockBackY!=0)
    {
      bounding.x+=knockBackX;
      bounding.y+=knockBackY;
      knockBackX*=backKnockBack;
      knockBackY*=backKnockBack;
    }
  }
  public void touchedEnemy(Rectangle rect,Enemy enemy)
  {  
    if(rect.intersects(bounding))
    {
      double vonlinks=rect.x+rect.width-bounding.x;
      double vonoben=rect.y+rect.height-bounding.y;
      double vonrechts=bounding.x+bounding.width-rect.x;
      double vonunten=bounding.y + bounding.height - rect.y;

      if(vonlinks<vonoben&&vonlinks<vonrechts&&vonlinks<vonunten)
      {
        bounding.x+=vonlinks;
        knockBackX=enemy.getKnockBack();
      }
      else if(vonoben<vonrechts&&vonoben<vonunten)
      {
        bounding.y+=vonoben;
        knockBackY=enemy.getKnockBack();
      }
      else if(vonrechts<vonunten)
      {
        bounding.x-=vonrechts;
        knockBackX=-enemy.getKnockBack();
      }
      else{
        bounding.y-=vonunten;
        knockBackY=-enemy.getKnockBack();
      }
      if(enemy.isDangerous())
      {
        health-=enemy.getDamage();
      }
    }
  }
  public Rectangle getBounding()
  {
    return bounding;
  }

  public BufferedImage getLook()
  {
    return look;
  }

  public float getHealth()
  {
    return health;
  }

  public float getMaxHelath()
  {
    return maxHelath;
  }
  
}

// Algorithmen for knockback
//    if(knockBackX!=0||knockBackY!=0)
//    {
//      System.out.println(knockBackX);
//      bounding.x+=knockBackX*tslf;
//      bounding.y+=knockBackY*tslf;
//      if(knockBackX>0)
//      {
//        knockBackX-=backKnockBack*tslf;
//      }
//      else if(knockBackX<0)
//      {
//        knockBackX+=backKnockBack*tslf;
//      }
//      
//      if(knockBackY>0)
//      {
//        knockBackY-=backKnockBack*tslf;
//      }
//      else if(knockBackY<0)
//      {
//        knockBackY+=backKnockBack*tslf;
//      }
//    }
//    else if(Math.abs(knockBackY)<5)
//    {
//      knockBackY=0;
//    }
//    else if(Math.abs(knockBackX)<5)
//    {
//      knockBackX=0;
//    }
