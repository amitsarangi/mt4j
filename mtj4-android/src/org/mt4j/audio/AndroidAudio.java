package org.mt4j.audio;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mt4j.audio.Audio;
import org.mt4j.audio.AudioDevice;
import org.mt4j.audio.AudioRecorder;
import org.mt4j.audio.Music;
import org.mt4j.audio.Sound;
import org.mt4j.util.gdx.AndroidFileHandle;
import org.mt4j.util.gdx.FileHandle;
import org.mt4j.util.gdx.Files.FileType;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/** An implementation of the {@link Audio} interface for Android.
 * 
 * @author mzechner */
public final class AndroidAudio implements Audio {
	private final SoundPool soundPool;
	private final AudioManager manager;
	protected final List<AndroidMusic> musics = new ArrayList<AndroidMusic>();

//	public AndroidAudio (Context context, AndroidApplicationConfiguration config) {
//		soundPool = new SoundPool(config.maxSimultaneousSounds, AudioManager.STREAM_MUSIC, 100);
//		manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
//		if(context instanceof Activity) {
//			((Activity)context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
//		}
//	}
	
	public AndroidAudio (Context context, int maxSimultaneousSounds) {
		soundPool = new SoundPool(maxSimultaneousSounds, AudioManager.STREAM_MUSIC, 100);
		manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		if(context instanceof Activity) {
			((Activity)context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
		}
	}

	protected void pause () {
		synchronized (musics) {
			for (AndroidMusic music : musics) {
				if (music.isPlaying()) {
					music.wasPlaying = true;
					music.pause();

				} else
					music.wasPlaying = false;
			}
		}
	}

	protected void resume () {
		synchronized (musics) {
			for (int i = 0; i < musics.size(); i++) {
				if (musics.get(i).wasPlaying == true) musics.get(i).play();
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public AudioDevice newAudioDevice (int samplingRate, boolean isMono) {
		return new AndroidAudioDevice(samplingRate, isMono);
	}

	/** {@inheritDoc} */
	@Override
	public Music newMusic (FileHandle file) {
		AndroidFileHandle aHandle = (AndroidFileHandle)file;

		MediaPlayer mediaPlayer = new MediaPlayer();

		if (aHandle.type() == FileType.Internal) {
			try {
				AssetFileDescriptor descriptor = aHandle.assets.openFd(aHandle.path());
				mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
				descriptor.close();
				mediaPlayer.prepare();
				AndroidMusic music = new AndroidMusic(this, mediaPlayer);
				synchronized (musics) {
					musics.add(music);
				}
				return music;
			} catch (Exception ex) {
				throw new RuntimeException("Error loading audio file: " + file
					+ "\nNote: Internal audio files must be placed in the assets directory.", ex);
			}
		} else {
			try {
				mediaPlayer.setDataSource(aHandle.file().getPath());
				mediaPlayer.prepare();
				AndroidMusic music = new AndroidMusic(this, mediaPlayer);
				synchronized (musics) {
					musics.add(music);
				}
				return music;
			} catch (Exception ex) {
				throw new RuntimeException("Error loading audio file: " + file, ex);
			}
		}

	}

	/** {@inheritDoc} */
	@Override
	public Sound newSound (FileHandle file) {
		AndroidFileHandle aHandle = (AndroidFileHandle)file;
		if (aHandle.type() == FileType.Internal) {
			try {
				AssetFileDescriptor descriptor = aHandle.assets.openFd(aHandle.path());
				AndroidSound sound = new AndroidSound(soundPool, manager, soundPool.load(descriptor, 1));
				descriptor.close();
				return sound;
			} catch (IOException ex) {
				throw new RuntimeException("Error loading audio file: " + file
					+ "\nNote: Internal audio files must be placed in the assets directory.", ex);
			}
		} else {
			try {
				return new AndroidSound(soundPool, manager, soundPool.load(aHandle.file().getPath(), 1));
			} catch (Exception ex) {
				throw new RuntimeException("Error loading audio file: " + file, ex);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public AudioRecorder newAudioRecorder (int samplingRate, boolean isMono) {
		return new AndroidAudioRecorder(samplingRate, isMono);
	}

	/** Kills the soundpool and all other resources */
	public void dispose () {
		synchronized (musics) {
			// gah i hate myself.... music.dispose() removes the music from the list...
			ArrayList<AndroidMusic> musicsCopy = new ArrayList<AndroidMusic>(musics);
			for (AndroidMusic music : musicsCopy) {
				music.dispose();
			}
		}
		soundPool.release();
	}
}