package jm.piedras;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//import java.io.FileInputStream;
//import java.io.InputStream;
//
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
 
 
public class Sonido {
	 
	//private String sjugar;
	 
	private Sequencer musica;
	private Clip clipclick,clipmover;
	private AudioInputStream audioInclick,audioinmover;
	private URL urlclick,urlmover;
	 
	private Ventana ventana;
	
	private static final String mmover="/Sonido/Menu_Mover.wav",mclick="/Sonido/Menu_Click.wav",mjugar="/Sonido/Musica.mid";
	
	public Sonido(Ventana v)
	{
		ventana = v;
		
		urlclick = this.getClass().getResource(mclick);
		urlmover = this.getClass().getResource(mmover);
		try {
			musica = MidiSystem.getSequencer();
			musica.open();
			InputStream is = new BufferedInputStream(getClass().getResourceAsStream(mjugar));
			musica.setSequence(is);
			musica.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		}
		catch (IOException e)
		{
			ventana.errorMusica(e);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ventana.errorMusica(e);
		}

	 }
	 
	 public void Play_Menu_Mover()
	 {
		 try
	    	{
		        audioinmover = AudioSystem.getAudioInputStream(urlmover);
		        // Get a sound clip resource.
		        clipmover = AudioSystem.getClip();

		        // Open audio clip and load samples from the audio input stream.
		        clipmover.open(audioinmover);
				FloatControl volume = (FloatControl) clipmover.getControl(FloatControl.Type.MASTER_GAIN);
				
				//if (volume != null)
				volume.setValue((float)-20);
				//else  System.out.println("null");
		        clipmover.start();
		    } catch (UnsupportedAudioFileException e) {
		        e.printStackTrace();
		     } catch (IOException e) {
		        e.printStackTrace();
		     } catch (LineUnavailableException e) {
		        e.printStackTrace();
		     }
		 
	 }
	 public void Play_Menu_Click()
	 {
		 try
	    	{
		        audioInclick = AudioSystem.getAudioInputStream(urlclick);
		        // Get a sound clip resource.
		        clipclick = AudioSystem.getClip();
		        // Open audio clip and load samples from the audio input stream.
		        clipclick.open(audioInclick);
				FloatControl volume = (FloatControl) clipclick.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue((float)-20);
//				volume.setValue((float)0.5);
		        clipclick.start();
		    } catch (UnsupportedAudioFileException e) {
		        e.printStackTrace();
		     } catch (IOException e) {
		        e.printStackTrace();
		     } catch (LineUnavailableException e) {
		        e.printStackTrace();
		     }
	 }
	 
	 public void play_jugar()
	 {
		 musica.start();
	 }
	 
	 public void stop_jugar()
	 {
		 musica.stop();
		 musica.setTickPosition(0); //rebobinar
	 }
	 
	 public boolean musica_sonando()
	 {
		 return musica.isRunning();
	 }
}


