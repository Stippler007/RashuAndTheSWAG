/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen.enemy;

import klassen.ImageFactory;
import klassen.player.Player;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class AngryBaguette extends Enemy
{
  
  
  public AngryBaguette(int x,int y,Player player,int speed,LinkedList<Enemy> enemys)
  {
    super(new Rectangle(x,y),player,enemys,speed);
    bounding=new Rectangle(x,y,super.getLook().getWidth(),super.getLook().getHeight());
    BufferedImage looks[]=new BufferedImage[1];
    looks[0]=ImageFactory.getImageFactory().getLooks("angrybaguette");
    setLook(looks);
  }

  @Override
  public void update(float tslf)
  {
//    bounding.x+=speedX;
//    bounding.y+=speedY;
    moveTarget(player.getBounding().x+player.getBounding().width/2,
             player.getBounding().y+player.getBounding().height/2,tslf);
    
    super.update(tslf);
  }
  
}
