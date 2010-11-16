package net.digitalmasonry.localnote;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Overlay;

public class MapNoteActivity extends MapActivity {
	List<Overlay> mapOverlays;
	Drawable drawable;
	NoteItemizedOverlay itemizedOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_note);
        Note.setContext(this);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new NoteItemizedOverlay(drawable);
        
        ArrayList<Note> notes = Note.notes;
        for (Note note : notes) {
	        GeoPoint point = new GeoPoint((int)(note.latitude * 1E6), (int)(note.longitude * 1E6));
	        OverlayItem overlayitem = new OverlayItem(point, "", "");
	        itemizedOverlay.addOverlay(overlayitem);
        }
        
        mapOverlays.add(itemizedOverlay);
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
