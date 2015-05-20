/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.List;
import klassen.enemy.AngryBaguette;
import klassen.player.PlayerSpritzer;
import klassen.player.Player;
import klassen.enemy.Enemy;
import klassen.enemy.EnemySpritzer;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import klassen.item.Item;

/**
 *
 * @author Stippler
 */
public class Main
{
  public static void main(String[] args)
  {
    int width=1024;
    int height=768;
    
    LinkedList<PlayerSpritzer> playerSpritzers=new LinkedList<PlayerSpritzer>();
    
    LinkedList<Enemy> enemys=new LinkedList<Enemy>();
    LinkedList<EnemySpritzer> enemySpritzers=new LinkedList<EnemySpritzer>();
    
    LinkedList<Item> items=new LinkedList<Item>();
    
    Background bg=new Background(0,0);
    
    Player player=new Player(width/2,height/2,200,playerSpritzers);
    
    GUI f=new GUI(player,playerSpritzers,enemys,enemySpritzers,bg,items);
    
//    f.setUndecorated(true);
    f.setVisible(true);
    f.setSize(width,height);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    f.setTitle("Rashu and the #SWAG");
    f.makeStrat();
    
    enemys.add(new AngryBaguette(100, 100,player,200,enemys));
    enemys.add(new AngryBaguette(100, 100,player,200,enemys));
    items.add(new Item(300, 300));
    
//    JFileChooser fc=new JFileChooser();
//    fc.showSaveDialog(fc);
//    try
//    {
//    Music.getMusic().setPath(fc.getSelectedFile().getAbsolutePath());
//    }
//    catch(Exception ex)
//    {
//      JOptionPane.showMessageDialog(null, "ERROR", "No music selected", JOptionPane.ERROR_MESSAGE);
//    }
    
    long lastFrame=System.currentTimeMillis();
    while(true)
    {
      if(player.getHealth()>0)
      {
        long thisFrame=System.currentTimeMillis();
        float tslf=(float)(thisFrame-lastFrame)/1000;
        lastFrame=thisFrame;


        player.update(tslf);
        for (PlayerSpritzer ps : playerSpritzers)
        {
          ps.update(tslf);
        }
        for (Enemy enemy : enemys)
        {
          enemy.update(tslf);
        }
        
        // playerSpritzer enemy collision
        int i=0;
        while(i<enemys.size())
        {
          int j=0;
          while(j<playerSpritzers.size())
          {
            if(enemys.get(i).getBounding().intersects(playerSpritzers.get(j).getBounding()))
            {
              enemys.get(i).touchedSpritzer(playerSpritzers.get(j).getBounding(), playerSpritzers.get(j));
              playerSpritzers.remove(j);
            }
            else
            {
              j++;
            }
          }
          if(enemys.get(i).getHealth()<0)
          {
            enemys.remove(i);
          }
          else
          {
            i++;
          }
        }
        
        // Collision Triangle
        i=0;
        while(i<items.size())
        {
          if(items.get(i).getBounding().intersects(player.getBounding()))
          {
            Player.swagcounter+=1000;
            newRound(enemys, player);
            items.remove(i);
            try
            {
              Music.getMusic().getSounds().get("illuminati").setFramePosition(0);
              Music.getMusic().getSounds().get("illuminati").start();
            }
            catch(Exception ex)
            {
              
            }
          }
          else
          {
            i++;
          }
        }
        
        f.repaintScreen();
        if(KL.keys[KeyEvent.VK_ESCAPE])System.exit(0);
        
        
        if(enemys.isEmpty()&&items.isEmpty())
        {
          newTriangle(items);
        }
      }
      else
      {
        f.drawGameOverScreen();
      }
      try {Thread.sleep(15);} catch (InterruptedException ex) {}
    }
  }
  private static void newRound(LinkedList<Enemy> enemys,Player player)
  {
    int zahl=new Random().nextInt(1);
    switch(zahl)
    {
      case 0:
      {
        enemys.add(new AngryBaguette(-50, 375, player, 200, enemys));
        enemys.add(new AngryBaguette(1024, 375, player, 200, enemys));
        enemys.add(new AngryBaguette(500, -50, player, 200, enemys));
        enemys.add(new AngryBaguette(500, 750, player, 200, enemys));
      }
      break;
    }
  }
  private static void newTriangle(LinkedList<Item> items)
  {
    Random r=new Random();
    items.add(new Item(r.nextInt(1000), r.nextInt(700)));
  }
}
