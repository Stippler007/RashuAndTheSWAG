/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.rangeFighter;

import klassen.ImageFactory;
import klassen.enemy.EnemySpritzer;

/**
 *
 * @author Stippler
 */
public class TwoHitSpritzer extends EnemySpritzer
{

  public TwoHitSpritzer(int x, int y, float speedX, float speedY)
  {
    super(x, y, speedX, speedY);
    setLook(ImageFactory.getImageFactory().getLooks("TwoHit"));
  }
  
}
