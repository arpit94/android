package com.example2.arpitaggarwal.musicplayerpro;

import android.content.Context;
import android.widget.MediaController;

/**
 * Created by Arpit Aggarwal on 7/15/2015.
 */
public class MusicController extends MediaController {
    public MusicController(Context context) {
        super(context);
    }
    public void hide(){//overrided the hide method so as to prevent it from automatically hiding after 3 seconds
     }
}
