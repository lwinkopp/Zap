package com.smartplayer;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.smartplayer.Controleur.Lecteur;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main extends Activity {
	
	private MediaPlayer mp;
	private Cursor musicCursor;
	private int musicCount;
	private Lecteur lecteur;
	private ImageButton boutonPlay;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.boutonPlay = (ImageButton) findViewById(R.id.play);
		this.boutonPlay.setBackgroundResource(R.drawable.gris);
		this.boutonPlay.setEnabled(false);
		
		this.lecteur = new Lecteur(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 
	public void zap(View view) {
		this.activerBoutonPlay();
		this.lecteur.lireMusiqueAleatoire();
	}
	
	public void play(View view) {
		if(this.lecteur.isEnLecture()) {
			this.lecteur.pause();
			this.boutonPlay.setBackgroundResource(R.drawable.play);
		} else {
			this.lecteur.reprise();
			this.boutonPlay.setBackgroundResource(R.drawable.pause);
		}
	}
	
	public void activerBoutonPlay() {
		this.boutonPlay.setEnabled(true);
		this.boutonPlay.setBackgroundResource(R.drawable.pause);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.mp.stop();
	}
	
	public Context getContext() {
		return this;
	}
}
