package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.async.LoadImageTask;
import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;

public class CharacterDetailsFragment extends Fragment implements LoadImageTask.LoadImageListener{

    private View view;
    private HarryPotterCharacter character;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_details,container,false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Keys.CHARACTER,character);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        if(savedInstanceState != null) {
            displayCharacter(savedInstanceState.getParcelable(Keys.CHARACTER));
        }
    }

    public void displayCharacter(HarryPotterCharacter character) {
        if(this.character != null) {
            return;
        }
        this.character = character;
        if(!character.getImage().equals("")) {
            new LoadImageTask(this).execute(character.getImage());
        }

        if (character.getAlternateNames() != null && character.getAlternateNames().length > 0) {
            StringBuilder builder = new StringBuilder();
            for (String name : character.getAlternateNames()) {
                builder.append(", ").append(name);
            }

            setTextWithLabel(R.id.cd_name_alternates,"", builder.substring(2));
        } else {
            hideElement(R.id.cd_name_alternates);
        }

        setTextWithLabel(R.id.cd_house,"",character.getHouse());

        setTextWithLabel(R.id.cd_actor, "Played by: " ,character.getActor());

        setTextWithLabel(R.id.cd_birthdate,"Birthday: ",character.getDateOfBirth());
        setTextWithLabel(R.id.cd_ancestry,"Ancestry: ",character.getAncestry());
        setTextWithLabel(R.id.cd_patronus,"Patronus: ",character.getPatronus());
    }

    @SuppressLint("SetTextI18n")
    private void setTextWithLabel(int id, String label, String value) {
        if(value != null && !value.equals("")) {
            ((TextView) view.findViewById(id)).setText(label + (label.equals("") ? "" : " ") + value);
        } else {
            hideElement(id);
        }
    }

    private void hideElement(int id) {
        View element = view.findViewById(id);
        if(element instanceof TextView) {
            ((TextView) element).setTextSize(0);
        }
    }

    @Override
    public void onLoadImage(Bitmap bitmap) {
        ((ImageView) view.findViewById(R.id.cd_image)).setImageBitmap(bitmap);
    }
}