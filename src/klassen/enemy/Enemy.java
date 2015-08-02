/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy;

import java.awt.Color;
import java.awt.Graphics2D;
import klassen.ImageFactory;
import klassen.player.Player;
import klassen.player.PlayerSpritzer;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Stippler
 */
public abstract class Enemy
{
  private LinkedList<Enemy> enemys;
  
  protected Rectangle bounding;
  private BufferedImage look[]=new BufferedImage[1];
  protected Player player;
  
  private float x;
  private float y;
  
  private float knockBack=50;
  protected float speedX;
  protected float speedY;
  protected int speed;
  protected boolean dangerous=true;
  protected float damage=10;
  
  private float health=100;
  private float maxHealth=100;
  
  private float knockBackX=0;
  private float knockBackY=0;
  private float backKnockBack=0.5f;
  
  public Enemy(float x,float y,Player player,LinkedList<Enemy> enemys,int speed)
  {
    bounding=new Rectangle();
    look[0]=ImageFactory.getImageFactory().getLooks("enemynotfound");
    this.player=player;
    this.speed=speed;
    this.enemys=enemys;
    this.x=x;
    this.y=y;
  }
  
  public void setHealth(float health)
  {
    this.health = health;
  }
  
  public void setLook(BufferedImage... looks)
  {
    this.look = looks;
    bounding.width=looks[0].getWidth();
    bounding.height=looks[0].getHeight();
  }

  public void setKnockBackX(float knockBackX)
  {
    this.knockBackX = knockBackX;
  }

  public void setKnockBackY(float knockBackY)
  {
    this.knockBackY = knockBackY;
  }
  
  public void update(float tslf)
  {
    if(knockBackX!=0||knockBackY!=0)
    {
      x+=knockBackX;
      y+=knockBackY;
      knockBackX*=backKnockBack;
      knockBackY*=backKnockBack;
    }
    
    collisions(tslf);
    
    bounding.x=(int)x;
    bounding.y=(int)y;
    
  }
  protected void moveTarget(int x,int y,float tslf)
  {
    System.out.println("x:"+x+"    y:"+y);
    speedX = x - (bounding.x+bounding.width/2);
    speedY = y - (bounding.y+bounding.height/2);
    
    float help = (float)Math.sqrt(speedX*speedX+speedY*speedY);
    
    speedX/=help;
    speedY/=help;
    
    speedX*=speed;
    speedY*=speed;
    
    x+=(speedX*tslf);
    y+=(speedY*tslf);
  }
  public void collisions(float tlsf)
  {
    player.touchedEnemy(bounding,this);
    collisionEnemy();
  }
  protected void collisionEnemy()
  {
    int i=0;
    while(i<enemys.size())
    {
      Enemy e=enemys.get(i);
      if(e.getBounding().intersects(bounding))
      {
        rebound(e.getBounding());
      }
      i++;
    }
  }
  protected void rebound(Rectangle rect)
  {
    if(bounding.intersects(rect))
    {
      int nachrechts=(int)(rect.x+rect.width)-bounding.x;
      int nachlinks=(int)(bounding.x+bounding.width)-rect.x;
      int nachunten=(int)(rect.y+rect.height)-bounding.y;
      int nachoben=(int)(bounding.y+bounding.height)-rect.y;
      
      if(nachrechts<nachlinks&&nachrechts<nachoben&&nachrechts<nachunten)
      {
        x+=nachrechts;
      }
      else if(nachlinks<nachoben&&nachlinks<nachunten)
      {
        x-=nachlinks;
      }
      else if(nachoben<nachunten)
      {
        y-=nachoben;
      }
      else if(nachoben>nachunten)
      {
        y+=nachunten;
      }
    }
  }
  public void touchedSpritzer(Rectangle rect,PlayerSpritzer spritzer)
  {  
    if(rect.intersects(bounding))
    {
      double vonlinks=rect.x+rect.width-bounding.x;
      double vonoben=rect.y+rect.height-bounding.y;
      double vonrechts=bounding.x+bounding.width-rect.x;
      double vonunten=bounding.y + bounding.height - rect.y;

      if(vonlinks<vonoben&&vonlinks<vonrechts&&vonlinks<vonunten)
      {
        knockBackX=spritzer.getKnockBack();
      }
      else if(vonoben<vonrechts&&vonoben<vonunten)
      {
        knockBackY=spritzer.getKnockBack();
      }
      else if(vonrechts<vonunten)
      {
        knockBackX=-spritzer.getKnockBack();
      }
      else{
        knockBackY=-spritzer.getKnockBack();
      }
      health-=spritzer.getDamage();
    }
  }
  
  public Rectangle getBounding()
  {
    return bounding;
  }

  public float getKnockBack()
  {
    return knockBack;
  }

  public boolean isDangerous()
  {
    return dangerous;
  }

  public float getDamage()
  {
    return damage;
  }
  
  public BufferedImage getLook()
  {
    return look[0];
  }

  public float getHealth()
  {
    return health;
  }

  public float getMaxHealth()
  {
    return maxHealth;
  }
  
  public void draw(Graphics2D g)
  {
    g.drawImage(getLook(), bounding.x, bounding.y, null);
  }
}
