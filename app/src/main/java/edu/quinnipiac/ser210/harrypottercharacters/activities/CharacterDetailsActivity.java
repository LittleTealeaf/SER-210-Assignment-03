package edu.quinnipiac.ser210.harrypottercharacters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CharacterDetailsFragment;

public class CharacterDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);

        CharacterDetailsFragment details = (CharacterDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_character_details);

        assert details != null;
        details.displayCharacter(getIntent().getParcelableExtra(Keys.CHARACTER));
    }
}