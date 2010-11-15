package net.digitalmasonry.localnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateNoteActivity extends Activity implements LocationListener {
	Location bestLocation = null;
	LocationManager locationManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
        Note.setContext(this);
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
        
        this.locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }
	
    public void saveNote(View view) {
    	Intent resultIntent = new Intent();
    	TextView noteTextView = (TextView)findViewById(R.id.note_text);
    	Note note = new Note(noteTextView.getText());
    	if (this.bestLocation != null) {
	    	note.latitude = this.bestLocation.getLatitude();
	    	note.longitude = this.bestLocation.getLongitude();
    	}
    	Note.save(note);
    	setResult(Activity.RESULT_OK, resultIntent);
    	finish();
    }

	public void onLocationChanged(Location location) {
		this.bestLocation = location;
		this.locationManager.removeUpdates(this);
		Log.i("Location", "removed updates");
	}
	
	public void onProviderDisabled(String provider) {}
	public void onProviderEnabled(String provider) {}
    public void onStatusChanged(String provider, int status, Bundle extras) {}   
}
