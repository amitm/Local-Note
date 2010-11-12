package net.digitalmasonry.localnote;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "local_note";
	private static final int DATABASE_VERSION = 2;
	private static final String NOTE_TABLE_NAME = "notes";
	private static final String NOTE_CONTENT = "content";
	private static final String NOTE_TABLE_CREATE = "CREATE TABLE "
			+ NOTE_TABLE_NAME + " (" + NOTE_CONTENT + " TEXT" + ");";

	NoteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NOTE_TABLE_CREATE);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + NOTE_TABLE_NAME);
		db.execSQL(NOTE_TABLE_CREATE);
	}

	public ArrayList<Note> getNotes() {
		String sql = "SELECT " + NOTE_CONTENT + " FROM " + NOTE_TABLE_NAME;
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.rawQuery(sql, null);
		ArrayList<Note> notes = new ArrayList<Note>();

		if (c.moveToFirst()) {
			do {
				int contentColumn = c.getColumnIndex(NOTE_CONTENT);
				String content = c.getString(contentColumn);
				if (content != null) {
					notes.add(0, new Note(content));
				}
			} while (c.moveToNext());
		}
		return notes;
	}

	public void save(Note note) {
		String content = note.content.toString();
		String sql = "INSERT INTO " + NOTE_TABLE_NAME + " (content) VALUES (?)";
		SQLiteDatabase db = getWritableDatabase();
		db.rawQuery(sql, new String[] { content });
	}
}
