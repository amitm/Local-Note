package net.digitalmasonry.localnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateNoteActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
    }
	
    public void saveNote(View view) {
    	Intent resultIntent = new Intent();
    	resultIntent.putExtra(Intent.EXTRA_TEXT, "test");
    	setResult(Activity.RESULT_OK, resultIntent);
    	finish();
    }
}
