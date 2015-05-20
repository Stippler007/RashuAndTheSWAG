/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Stippler
 */
public class KL implements KeyListener
{
  
  public static boolean[] keys=new boolean[1024];
  
  @Override
  public void keyTyped(KeyEvent e)
  {
    
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    keys[e.getKeyCode()]=true;
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    keys[e.getKeyCode()]=false;
  }
  
}
