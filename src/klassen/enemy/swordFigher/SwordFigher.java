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
  
  
  public SwordFigher(int x,int y,Player player, LinkedList<Enemy> enemys, int speed)
  {
    super(new Rectangle(x,y,50,50), player, enemys, speed);
    
  }
  
  @Override
  public void update(float tslf)
  {
    super.update(tslf);
  }
  
  
  
}
