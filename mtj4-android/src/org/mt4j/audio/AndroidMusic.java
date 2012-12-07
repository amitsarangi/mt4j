package org.mt4j.audio;

import java.io.IOException;

import org.mt4j.audio.Music;

import android.media.MediaPlayer;

public class AndroidMusic implements Music {
	private final AndroidAudio audio;
	private MediaPlayer player;
	private boolean isPrepared = true;
	protected boolean wasPlaying = false;

	AndroidMusic (AndroidAudio audio, MediaPlayer player) {
		this.audio = audio;
		this.player = player;
	}

	@Override
	public void dispose () {
		if (player == null) return;
		try {
			if (player.isPlaying()) player.stop();
			player.release();
		} catch (Throwable t) {
			System.err.println("error while disposing AndroidMusic instance, non-fatal");
		} finally {
			player = null;
			synchronized (audio.musics) {
				audio.musics.remove(this);
			}
		}
	}

	@Override
	public boolean isLooping () {
		return player.isLooping();
	}

	@Override
	public boolean isPlaying () {
		return player.isPlaying();
	}

	@Override
	public void pause () {
		if (player.isPlaying()) player.pause();
	}

	@Override
	public void play () {
		if (player.isPlaying()) return;

		try {
			if (!isPrepared) {
				player.prepare();
				isPrepared = true;
			}
			player.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setLooping (boolean isLooping) {
		player.setLooping(isLooping);
	}

	@Override
	public void setVolume (float volume) {
		player.setVolume(volume, volume);
	}

	@Override
	public void stop () {
		if (isPrepared) {
			player.seekTo(0);
		}
		player.stop();
		isPrepared = false;
	}

	public float getPosition () {
		return player.getCurrentPosition() / 1000f;
	}
}
