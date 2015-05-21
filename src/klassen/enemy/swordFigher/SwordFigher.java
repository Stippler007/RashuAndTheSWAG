/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.swordFigher;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.ImageFactory;
import klassen.enemy.Enemy;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public abstract class SwordFigher extends Enemy
{
  protected BufferedImage swordLook;
  protected Line2D swordBounding;
  
  // the angle to the player + x radiant
  protected double swordAngle;
  protected double deltaSwordAngle;
  protected double distanceSwordAngle=Math.PI/2;
  protected double swordSpeed=Math.PI;
  
  
  public SwordFigher(int x,int y,Player player, LinkedList<Enemy> enemys, int speed)
  {
    super(new Rectangle(x,y,50,50), player, enemys, speed);
    swordLook=ImageFactory.getImageFactory().getLooks("dildo");
    swordBounding=new Line2D.Double(bounding.x+bounding.width/2, bounding.y+bounding.height/2,
            swordLook.getWidth(), bounding.y+bounding.height/2);
    startStrike();
  }
  
  @Override
  public void update(float tslf)
  {
    deltaSwordAngle+=swordSpeed*tslf;
    updateSwordBounding();
    collisionSwordPlayer(tslf);
    
    super.update(tslf);
  }
  public void collisionSwordPlayer(float tslf)
  {
    if(swordBounding.intersects(player.getBounding()))
    {
      player.setHealth(player.getHealth()-getDamage()*tslf);
    }
  }
  public void startStrike()
  {
    swordAngle=getAngle();
    deltaSwordAngle=swordAngle-distanceSwordAngle/2;
  }
  private void updateSwordBounding()
  {
    int x1=bounding.x+(int)bounding.getWidth()/2;
    int y1=bounding.y+(int)bounding.getHeight()/2;
    int x2=(int)(swordBounding.getX1()+(Math.cos(swordAngle+deltaSwordAngle)*swordLook.getWidth()));
    int y2=(int)(swordBounding.getY1()+(Math.sin(swordAngle+deltaSwordAngle)*swordLook.getWidth()));
    
    swordBounding.setLine(x1, y1, x2, y2);
  }
  private double getAngle()
  {
    double a=(player.getBounding().x+player.getBounding().width/2)-(bounding.x+bounding.width/2);
    double b=(player.getBounding().y+player.getBounding().height/2)-(bounding.y+bounding.height/2);
    
    double turn=Math.atan(b/a);
    if(a<0)
    {
      turn+=2.3561944901923;
    }
     return turn; 
  }
  
  public double getSwordAngle()
  {
    double a=(swordBounding.getX1()-swordBounding.getX2());
    double b=(swordBounding.getY1()-swordBounding.getY2());
    
    double turn=Math.atan(b/a);
    if(a<0)
    {
      turn+=2.3561944901923;
    }
    return turn; 
  }

  public BufferedImage getSwordLook()
  {
    return swordLook;
  }
  
  @Override
  public void draw(Graphics2D g)
  {
    g.rotate(getSwordAngle(),swordBounding.getX1(),swordBounding.getY1());
    g.drawImage(getSwordLook(), (int)swordBounding.getX1()-swordLook.getWidth()/2,
            (int)swordBounding.getY1()-swordLook.getWidth()/2, null);
    g.rotate(-getSwordAngle(),swordBounding.getX1(),swordBounding.getY1());
    super.draw(g);
  }
  
}
