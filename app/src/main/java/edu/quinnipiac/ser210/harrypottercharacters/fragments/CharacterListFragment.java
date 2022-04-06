package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.adapter.CharacterAdapter;
import edu.quinnipiac.ser210.harrypottercharacters.async.FetchCharactersTask;
import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;

public class CharacterListFragment extends Fragment {

    private final ArrayList<HarryPotterCharacter> harryPotterCharacters = new ArrayList<>();
    private CharacterAdapter mAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setListener(CharacterClickedListener listener) {
        mAdapter.setListener(listener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.characters_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new CharacterAdapter(getContext(),harryPotterCharacters);

        recyclerView.setAdapter(mAdapter);

    }

    public void setHarryPotterCharacters(Collection<HarryPotterCharacter> characters) {
        harryPotterCharacters.clear();
        harryPotterCharacters.addAll(characters);
        mAdapter.notifyDataSetChanged();
    }

    public interface CharacterClickedListener {
        void onCharacterSelected(HarryPotterCharacter character);
    }

}