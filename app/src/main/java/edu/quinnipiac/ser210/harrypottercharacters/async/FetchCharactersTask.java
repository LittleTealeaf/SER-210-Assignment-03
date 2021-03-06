package edu.quinnipiac.ser210.harrypottercharacters.async;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;

public class FetchCharactersTask extends AsyncTask<String,Void, ArrayList<HarryPotterCharacter>> {

    private static String buildURL(String... endpoints) {
        return "https://hp-api.herokuapp.com/api/" + String.join("/",endpoints);
    }

    private FetchCharactersListener listener;

    public FetchCharactersTask(FetchCharactersListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<HarryPotterCharacter> doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        StringBuffer string = new StringBuffer();

        try {
            URL url = new URL(buildURL(strings));
            urlConnection = (HttpURLConnection)  url.openConnection();
            urlConnection.setRequestMethod("GET");

            InputStream stream = urlConnection.getInputStream();

            if(stream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(stream));

            String line;

            while((line = reader.readLine()) != null) {
                string.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return convertJson(string.toString());
    }

    @Override
    protected void onPostExecute(ArrayList<HarryPotterCharacter> harryPotterCharacters) {
        if(listener != null) {
            listener.onFetchCharacters(harryPotterCharacters);
        }
        super.onPostExecute(harryPotterCharacters);
    }

    private ArrayList<HarryPotterCharacter> convertJson(String json) {
        try {
            ArrayList<HarryPotterCharacter> harryPotterCharacters = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                harryPotterCharacters.add(new HarryPotterCharacter(object));
            }
            return harryPotterCharacters;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface FetchCharactersListener {
        void onFetchCharacters(ArrayList<HarryPotterCharacter> harryPotterCharacters);
    }
}
