package edu.quinnipiac.ser210.harrypottercharacters.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.async.FetchCharactersTask;
import edu.quinnipiac.ser210.harrypottercharacters.data.Category;
import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CharacterListFragment;

public class CharacterListActivity extends AppCompatActivity
        implements FetchCharactersTask.FetchCharactersListener, CharacterListFragment.CharacterClickedListener {

    private ArrayList<HarryPotterCharacter> characters = new ArrayList<>();
    private CharacterListFragment characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        characterList = (CharacterListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_characters);
        assert characterList != null;
        characterList.setListener(this);


        if (savedInstanceState != null) {
            onFetchCharacters(savedInstanceState.getParcelableArrayList(Keys.CHARACTERS));
        } else {
            new FetchCharactersTask(this).execute(Category.values()[getIntent().getExtras().getInt(Keys.CATEGORY)].getEndpoint());
        }
    }

    @Override
    public void onFetchCharacters(ArrayList<HarryPotterCharacter> harryPotterCharacters) {
        characterList.setHarryPotterCharacters(characters = harryPotterCharacters);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Keys.CHARACTERS, characters);
    }

    @Override
    public void onCharacterSelected(HarryPotterCharacter character) {

    }
}