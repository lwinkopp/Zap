package com.smartplayer.Modele;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Musique {
	
	private String titre;
	private String artiste;
	private String album;
	private String chemin;
	private String duree ;
	
	public Musique(Context context, Cursor musicCursor) {
		int index;
		
		index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE);
		this.titre = musicCursor.getString(index);
		index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
		this.artiste = musicCursor.getString(index);
		index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
		this.album = musicCursor.getString(index);
		index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
		this.chemin = musicCursor.getString(index);
		index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
		this.duree = musicCursor.getString(index);
	}
	
	public String getTitre() {
		return titre;
	}
	public String getArtiste() {
		return artiste;
	}
	public String getAlbum() {
		return album;
	}
	public String getChemin() {
		return chemin;
	}
	public String getDuree() {
		return duree;
	}
	
	public String toString() {
		return this.titre + "\n" + this.artiste + " - " + this.album;
	}
	
}
