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

public class CharacterListFragment extends Fragment implements FetchCharactersTask.FetchCharactersListener {

    private final ArrayList<HarryPotterCharacter> harryPotterCharacters = new ArrayList<>();
    private CharacterAdapter mAdapter;

    private CharacterClickedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void setListener(CharacterClickedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_list,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        RecyclerView recyclerView = getView().findViewById(R.id.characters_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new CharacterAdapter(getContext(),harryPotterCharacters);
        mAdapter.setListener((character) -> {
            if(CharacterListFragment.this.listener != null) {
                CharacterListFragment.this.listener.onCharacterSelected(character);
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onFetchCharacters(ArrayList<HarryPotterCharacter> harryPotterCharacters) {
        this.harryPotterCharacters.clear();
        this.harryPotterCharacters.addAll(harryPotterCharacters);
        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface CharacterClickedListener {
        void onCharacterSelected(HarryPotterCharacter character);
    }

}