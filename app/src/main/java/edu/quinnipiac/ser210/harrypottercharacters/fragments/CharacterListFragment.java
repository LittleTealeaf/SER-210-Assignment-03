package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.data.Character;

public class CharacterListFragment extends ListFragment {

    private static final String KEY_CHARACTERS = "Character";

    private ArrayList<Character> characters;
    private CharacterListListener listener;

    public CharacterListFragment() {

    }

    public void setListener(CharacterListListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CharacterListListener) {
            listener = (CharacterListListener) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            characters = savedInstanceState.getParcelableArrayList(KEY_CHARACTERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_list, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener != null) {
            listener.onCharacterClicked(characters.get(position));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_CHARACTERS,characters);
    }

    public interface CharacterListListener {
        void onCharacterClicked(Character character);
    }
}