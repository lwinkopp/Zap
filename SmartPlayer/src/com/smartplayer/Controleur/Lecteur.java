package com.smartplayer.Controleur;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smartplayer.R;
import com.smartplayer.Modele.Musique;

public class Lecteur implements OnCompletionListener, Runnable{
	
	private MediaPlayer mp;
	private Activity activity;
	private boolean musiqueDansLecteur;
	private boolean enLecture;
    private ProgressBar progressBar;
    private int progressStatus;

	public Lecteur(Activity activity) {
		this.mp = new MediaPlayer();
		this.activity = activity;
		this.enLecture = false;
		this.progressStatus = 0;
        this.progressBar = (ProgressBar) this.activity.findViewById(R.id.progressBar);
	}

	public void lireMusiqueAleatoire() {
		
		Random random = new Random();
		Cursor musicCursor;
		String[] projection = null;
		Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		musicCursor = this.activity.getContentResolver().query(contentUri, projection, null, null, null);
		musicCursor.moveToPosition(random.nextInt(musicCursor.getCount()));
		Musique musique = new Musique(this.activity, musicCursor);
		
		if (this.enLecture) {
			this.mp.stop();
		}
		this.mp = this.mp.create(this.activity, Uri.parse("file://"+musique.getChemin()));
		this.mp.start();
		this.mp.setOnCompletionListener(this);
		this.progressBar.setMax(this.mp.getDuration());

		this.enLecture = true;
		Toast.makeText(this.activity, musique.toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public void run() {
		int currentPosition = 0;
		int total = this.mp.getDuration();
		while(currentPosition < total) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentPosition = this.mp.getCurrentPosition();
			this.progressBar.setProgress(currentPosition);
		}
	}
	
	public void pause() {
		this.mp.pause();
		this.enLecture = false;
	}
	
	public void reprise() {
		this.mp.start(); // A TESTER !!
		this.enLecture = true;
	}
	
	@Override
    public void onCompletion(MediaPlayer mp) {
		this.lireMusiqueAleatoire();
    }

	public boolean isEnLecture() {
		return enLecture;
	}

	public boolean isMusiqueDansLecteur() {
		return musiqueDansLecteur;
	}
	
	public MediaPlayer getMp() {
		return mp;
	}

	public Activity getActivity() {
		return activity;
	}
}
