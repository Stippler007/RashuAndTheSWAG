/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.swordFigher;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
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
  protected double angle;
  protected double deltaAngle;
  protected double distanceAngle=Math.PI/2;
  
  public SwordFigher(int x,int y,Player player, LinkedList<Enemy> enemys, int speed)
  {
    super(new Rectangle(x,y,50,50), player, enemys, speed);
    swordBounding=new Line2D.Double(bounding.x+bounding.width/2, bounding.y+bounding.height/2,
            swordLook.getWidth(), bounding.y+bounding.height/2);
  }
  
  @Override
  public void update(float tslf)
  {
    if(deltaAngle<)
    {
      
    }
      
    super.update(tslf);
  }
  
  public void startStrike()
  {
    angle=
  }
}
