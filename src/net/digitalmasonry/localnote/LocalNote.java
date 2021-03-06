package net.digitalmasonry.localnote;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class LocalNote extends ListActivity {
	
	static final int CREATE_NOTE_DATA = 1;
	private ArrayAdapter<String> adapter;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Note.setContext(this);
        
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Note> notes = Note.notes;
        for (Note note : notes) {
        	list.add(note.content.toString());
        }
        adapter = new ArrayAdapter<String>(this, R.layout.note_list_item, R.id.text1, list); 
        setListAdapter(adapter);
    }
    
    public void newNote(View view) {
    	Intent intent = new Intent(this, net.digitalmasonry.localnote.CreateNoteActivity.class);
    	startActivityForResult(intent, CREATE_NOTE_DATA);
    }
    
    public void showMap(View view) {
    	Intent intent = new Intent(this, net.digitalmasonry.localnote.MapNoteActivity.class);
    	startActivity(intent);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == CREATE_NOTE_DATA) {
    		if (resultCode == RESULT_OK) {
    			Note note = Note.getNewestNote();
    			adapter.insert(note.content.toString(), 0);
    		}
    	}
    }
}
