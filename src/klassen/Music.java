/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klassen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Stippler
 */
public class Music
{
  private HashMap<String,Clip> sounds=new HashMap<String, Clip>();
  
  private static Music music;
  
  private String path;
  
  private Music()
  {
    addIlluminati();
  }

  public static Music getMusic()
  {
    if(music==null)
    {
      music=new Music();
    }
    return music;
  }

  public void setPath(String path)
  {
    this.path = path;
  }
  
  private void addIlluminati()
  {
    try
    {
      String path = System.getProperty("user.dir")+File.separator+"sounds"+File.separator+"illuminati.wav";
      AudioInputStream myInputStream=AudioSystem.getAudioInputStream(new File(path));
      AudioFormat myAudioFormat = myInputStream.getFormat();
      int groesse = (int)(myAudioFormat.getFrameSize()*myInputStream.getFrameLength());
      byte[] mySound=new byte[groesse];
      DataLine.Info myInfo = new DataLine.Info(Clip.class, myAudioFormat,groesse);
      myInputStream.read(mySound,0,groesse);
      
      Clip myClip = (Clip)AudioSystem.getLine(myInfo);
      myClip.open(myAudioFormat,mySound,0,groesse);
      sounds.put("illuminati", myClip);
    }
    catch(Exception ex)
    {
      System.out.println(ex.toString());
    }
  }

  public HashMap<String, Clip> getSounds()
  {
    return sounds;
  }
  
}
