package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
/**
 * Denne klasse er delvis afskrift af en sound klasse-vejledning fundet på nettet.
 * Det er en klasse fra nettet fordi jeg ville have en klasse med sikker exceptionhandling.
 * kan ikke finde kilden. 
 * Metoderne playSoundThread og nedefter er skrevet af mig
 * @author Justin
 *
 */
public class Sound {

	private final int BUFFER_SIZE = 128000;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	/**
	 * @param filename the name of the file that is going to be played
	 */
	public void playSound(String filename){

		String strFilename = filename;

		try {
			soundFile = new File(strFilename);
		} catch (Exception e) {
			e.printStackTrace();
			//            System.exit(1);
		}

		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e){
			e.printStackTrace();
			//            System.exit(1);
		}

		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			//            System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			//            System.exit(1);
		}

		sourceLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}

		sourceLine.drain();
		sourceLine.close();
	}
	
	public void playSoundThread(String filename){
		new Thread(
				new Runnable() {
					public void run() {
						try {
							playSound(filename);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
	}

	public void playJailSoundThread (){
		playSoundThread("ressources/jail.wav");
	}
	public void playPaySoundThread (){
		playSoundThread("ressources/coin.wav");
	}
	public void playGameOverSound (){
		playSoundThread("ressources/game_over.wav");
	}

}