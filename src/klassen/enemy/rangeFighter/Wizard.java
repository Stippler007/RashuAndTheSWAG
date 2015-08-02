/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.rangeFighter;

import com.sun.media.sound.RealTimeSequencerProvider;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.ImageFactory;
import klassen.enemy.Enemy;
import klassen.enemy.EnemySpritzer;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public class Wizard extends Enemy
{
  private LinkedList<EnemySpritzer> enemySpritzers;
  
  private float reloadTime=0f;
  private final float maxReloadTime=0.3f;
  private int moveTo[][]=new int[4][2];
  private int moveToPointer=0;
  
  public Wizard(int x, int y, Player player, LinkedList<Enemy> enemys,
          LinkedList<EnemySpritzer> enemySpritzers, int speed)
  {
    super(x,y, player, enemys, speed);
    this.enemySpritzers=enemySpritzers;
    
    setLook(ImageFactory.getImageFactory().getLooks("Zargon"));
    
    setDefaultMoveTo();
  }
  public void setDefaultMoveTo()
  {
    moveTo[0][0]=50;
    moveTo[0][1]=50;
    
    moveTo[1][0]=700;
    moveTo[1][1]=50;
    
    moveTo[2][0]=700;
    moveTo[2][1]=550;
    
    moveTo[3][0]=50;
    moveTo[3][1]=700;
  }
  @Override
  public void update(float tslf)
  {
    moveTarget(moveTo[moveToPointer][0],
            moveTo[moveToPointer][1], tslf);
    if(pointIntersectsBounding())
    {
      if(moveToPointer>3)moveToPointer=0;
      else moveToPointer++;
    }
    if(maxReloadTime<reloadTime)
    {
      enemySpritzers.add(new TwoHitSpritzer(bounding.x, bounding.y, 300, 0));
      reloadTime-=maxReloadTime;
    }
    else
    {
      reloadTime+=tslf;
    }
    
    super.update(tslf);
  }
  protected boolean pointIntersectsBounding()
  {
    if(bounding.x<moveTo[moveToPointer][0]&&bounding.x+bounding.width>moveTo[moveToPointer][1]&&
       bounding.y<moveTo[moveToPointer][0]&&bounding.y+bounding.width>moveTo[moveToPointer][1])
    {
      return true;
    }
    return false;
  }
}
