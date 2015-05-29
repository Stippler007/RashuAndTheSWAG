/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.swordFigher;

import java.awt.Color;
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
  protected double swordSpeed=Math.PI/1.5;
  
  protected boolean striking=true;
  
  public SwordFigher(int x,int y,Player player, LinkedList<Enemy> enemys, int speed)
  {
    super(new Rectangle(x,y,50,50), player, enemys, speed);
    swordLook=ImageFactory.getImageFactory().getLooks("dildo");
    swordBounding=new Line2D.Double(bounding.x+bounding.width/2, bounding.y+bounding.height/2,
            swordLook.getWidth(), bounding.y+bounding.height/2);
    dangerous=false;
  }
  
  @Override
  public void update(float tslf)
  {
    deltaSwordAngle+=swordSpeed*tslf;
    updateSwordBounding();
    collisionSwordPlayer(tslf);
    
    moveTarget(player.getBounding().x, player.getBounding().y, tslf);
    
    super.update(tslf);
    striking=true;
  }
  protected void collisionSwordPlayer(float tslf)
  {
    if(swordBounding.intersects(player.getBounding()))
    {
      player.setHealth(player.getHealth()-getDamage());
      swordKnockBack();
    }
  }
  protected void swordKnockBack()
  {
    float vectorX = bounding.x - (player.getBounding().x+player.getBounding().width/2);
    float vectorY = bounding.y - (player.getBounding().y+player.getBounding().height/2);
    
    float help = (float)Math.sqrt(vectorX*vectorX+vectorY*vectorY);
    
    vectorX/=help;
    vectorY/=help;
    
    vectorX*=speed;
    vectorY*=speed;
    
    player.knockBackX=-vectorX;
    player.knockBackY=-vectorY;
  }
  private void updateSwordBounding()
  {
    if(swordAngle<distanceSwordAngle)
    {
      swordAngle=0;
    }
    
    int x1=bounding.x+(int)bounding.getWidth()/2;
    int y1=bounding.y+(int)bounding.getHeight()/2;
    int x2=(int)(x1+(Math.cos(swordAngle+deltaSwordAngle)*swordLook.getHeight()));
    int y2=(int)(y1+(Math.sin(swordAngle+deltaSwordAngle)*swordLook.getHeight()));
    
    swordBounding.setLine(x1, y1, x2, y2);
  }
  private double getAngle()
  {
    double a=(player.getBounding().x+player.getBounding().width/2)-(bounding.x+bounding.width/2);
    double b=(player.getBounding().y+player.getBounding().height/2)-(bounding.y+bounding.height/2);
    
    double turn=Math.atan(b/a);
    if(a<0)
    {
      turn+=Math.PI;
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
      turn+=Math.PI;
    }
    return turn+Math.PI/2; 
  }

  public BufferedImage getSwordLook()
  {
    return swordLook;
  }
  
  @Override
  public void draw(Graphics2D g)
  {
    g.rotate(getSwordAngle(),swordBounding.getX1(),swordBounding.getY1());
    if(striking)
    {
      g.drawImage(getSwordLook(), (int)swordBounding.getX1()-swordLook.getWidth()/2,
      (int)swordBounding.getY1()-swordLook.getWidth()/2, null);
      g.rotate(-getSwordAngle(),swordBounding.getX1(),swordBounding.getY1());
    }
    super.draw(g);
  }
  
}
