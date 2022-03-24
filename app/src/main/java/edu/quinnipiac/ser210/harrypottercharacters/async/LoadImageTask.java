package edu.quinnipiac.ser210.harrypottercharacters.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String,Void, Bitmap> {

    private final LoadImageListener listener;

    public LoadImageTask(LoadImageListener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bitmap = null;
        try {
            InputStream in = new URL(strings[0]).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(listener != null) {
            listener.onLoadImage(bitmap);
        }
        super.onPostExecute(bitmap);
    }

    public interface LoadImageListener {
        void onLoadImage(Bitmap bitmap);
    }
}
