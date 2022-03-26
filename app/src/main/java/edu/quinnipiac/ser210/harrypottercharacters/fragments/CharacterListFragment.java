package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.quinnipiac.ser210.harrypottercharacters.R;

public class CharacterListFragment extends Fragment {

    public CharacterListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_list, container, false);
    }

    public interface CharacterListListener {

    }
}