/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import klassen.player.PlayerSpritzer;
import klassen.player.Player;
import klassen.enemy.Enemy;
import klassen.enemy.EnemySpritzer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import klassen.item.Item;

/**
 *
 * @author Stippler
 */
public class GUI extends JFrame
{
  private Player player;
  private LinkedList<PlayerSpritzer> playerSpritzers;
  private LinkedList<Enemy> enemys;
  private LinkedList<EnemySpritzer> enemySpritzers;
  private Background bg;
  private JPanel c=new JPanel();
  private BufferStrategy strat;
  private LinkedList<Item> items;
  
  public GUI(Player player, LinkedList<PlayerSpritzer> playerSpritzers, 
           LinkedList<Enemy> enemys,LinkedList<EnemySpritzer> enemySpritzers, Background bg,LinkedList<Item> items)
  {
    this.player = player;
    this.playerSpritzers = playerSpritzers;
    this.enemys = enemys;
    this.enemySpritzers = enemySpritzers;
    this.bg = bg;
    this.items=items;
    addKeyListener(new KL());
    this.add(c);
  }
  public void makeStrat()
  {
    createBufferStrategy(2);
    strat = getBufferStrategy();
  }
  public void repaintScreen(){
      Graphics2D g=(Graphics2D)strat.getDrawGraphics();
      draw(g);
      g.dispose();
      strat.show();
  }
  float dick=0;
  private void draw(Graphics2D g)
  {
    g.drawImage(ImageFactory.getImageFactory().getLooks("background1"),0,0,null);

    g.setColor(Color.red);
    g.fillRect(10, 30, 300, 20);
    g.setColor(Color.green);
    g.fillRect(10, 30, (int)(300*(player.getHealth()/player.getMaxHelath())), 20);

    g.drawImage(player.getLook(),player.getBounding().x,player.getBounding().y,null);
    for (PlayerSpritzer playerSpritzer : playerSpritzers)
    {
      g.drawImage(playerSpritzer.getLook() , playerSpritzer.getBounding().x, playerSpritzer.getBounding().y,null);
    }
    for (Enemy enemy : enemys)
    {
      g.setColor(Color.red);
      g.fillRect(enemy.getBounding().x, enemy.getBounding().y-5, enemy.getBounding().width, 3);
      g.setColor(Color.green);
      g.fillRect(enemy.getBounding().x, enemy.getBounding().y-5, (int)(enemy.getBounding().width*(enemy.getHealth()/enemy.getMaxHealth())), 3);
      g.drawImage(enemy.getLook() , enemy.getBounding().x, enemy.getBounding().y,null);
    }
    for (Item item : items)
    {
      g.drawImage(item.getLook() , item.getBounding().x, item.getBounding().y,null);
    }
    dick+=0.02;
    g.rotate(dick,400,400);
    g.setColor(Color.white);
    g.setFont(new Font("Arial",0,32));
    g.drawString("#SWAGCOUNTER: "+Player.swagcounter, 400, 400);
  }
  public void drawGameOverScreen()
  {
    Graphics2D g=(Graphics2D)strat.getDrawGraphics();
    
    g.rotate(dick, getWidth()/2, getHeight()/2);
    g.drawImage(ImageFactory.getImageFactory().getLooks("gameover"), 0, 0, null);
    dick+=0.01;
    g.rotate(dick*3,200,390);
    g.setColor(Color.red);
    g.setColor(Color.white);
    g.setFont(new Font("Arial",0,74));
    g.setColor(Color.yellow);
    g.drawString("#SWAG", 200, 190);
    g.drawString("#U GOT NOSCOPED", 500, 290);
    g.drawString("BITCH", 200, 390);
    g.rotate(-dick*2);
    g.drawString("OHHH BABY A TRIBLE", 500, 490);
    g.drawString("DICK", 200, 590);
    g.drawString("NOSCOPED 360", 500, 690);
    g.rotate(-dick*2);
    
    g.dispose();
    strat.show();
  }
}
