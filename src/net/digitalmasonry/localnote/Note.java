package net.digitalmasonry.localnote;

import java.util.ArrayList;

import android.content.Context;

public class Note {
	// Current data storage for notes
	public static ArrayList<Note> notes;
	public CharSequence content;
	public double latitude;
	public double longitude;
	private static NoteOpenHelper noteOpenHelper;
	
	
	public static Note getNewestNote() {
		if (notes.size() > 0) {
			return notes.get(0);
		}
		return null;
	}
	
	public static void setContext(Context context) {
		noteOpenHelper = new NoteOpenHelper(context);
		notes = noteOpenHelper.getNotes();
	}
	
	public static void save(Note note) {
		notes.add(0, note);
		noteOpenHelper.save(note);
	}
	
	public static ArrayList<Note> getAlNotes() {
		return notes;
	}
	
	public Note(CharSequence content) {
		this.content = content;
	}
}
