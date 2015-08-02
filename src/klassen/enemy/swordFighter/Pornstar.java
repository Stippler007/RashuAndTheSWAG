/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy.swordFighter;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import klassen.ImageFactory;
import klassen.enemy.Enemy;
import klassen.player.Player;

/**
 *
 * @author Stippler
 */
public class Pornstar extends SwordFigher
{

  public Pornstar(int x, int y, Player player, LinkedList<Enemy> enemys, int speed)
  {
    super(x, y, player, enemys, speed);
    setLook(ImageFactory.getImageFactory().getLooks("pornstar"));
    damage=50;
  }
  
}
