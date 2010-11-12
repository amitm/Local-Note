package net.digitalmasonry.localnote;

import java.util.ArrayList;

public class Note {
	// Current data storage for notes
	public static ArrayList<Note> notes;
	public String content;
	
	public static Note getNewestNote() {
		if (notes.size() > 0) {
			return notes.get(0);
		}
		return null;
	}
	
	public static void insert(Note note) {
		notes.add(0, note);
	}
	
	public static ArrayList<Note> getAlNotes() {
		return notes;
	}
}
