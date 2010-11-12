package net.digitalmasonry.localnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateNoteActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
        TextView noteTextView = (TextView)findViewById(R.id.note_text);
        
        noteTextView.addTextChangedListener(new TextWatcher() {
        	 public void onTextChanged(CharSequence s, int start, int before, int count) {}
        	 public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        	 public void afterTextChanged(Editable s) {
 				Button button = (Button)findViewById(R.id.create_note_button);
				TextView noteTextView = (TextView)findViewById(R.id.note_text);
				button.setEnabled(noteTextView.getText().length() > 0);
        	 }
        });
        
        Button button = (Button)findViewById(R.id.create_note_button);
        button.setEnabled(false);
    }
	
    public void saveNote(View view) {
    	Intent resultIntent = new Intent();
    	TextView noteTextView = (TextView)findViewById(R.id.note_text);
    	resultIntent.putExtra(Intent.EXTRA_TEXT, noteTextView.getText());
    	setResult(Activity.RESULT_OK, resultIntent);
    	finish();
    }
}
