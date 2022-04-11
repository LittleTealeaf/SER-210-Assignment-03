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

    private HarryPotterCharacter character;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            character = savedInstanceState.getParcelable(Keys.CHARACTER);
        }
        return inflater.inflate(R.layout.fragment_character_details,container,false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Keys.CHARACTER,character);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (character != null) {
            displayCharacter(character);
        }
    }

    public void displayCharacter(HarryPotterCharacter character) {
        this.character = character;
        if(getView() != null) {


            setTextWithLabel(R.id.cd_name,"",character.getName());

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

            setTextWithLabel(R.id.cd_actor, getString(R.string.label_actor) ,character.getActor());

            setTextWithLabel(R.id.cd_birthdate,getString(R.string.label_birthday) + ": ",character.getDateOfBirth());
            setTextWithLabel(R.id.cd_ancestry,getString(R.string.label_ancestry) + ": ",character.getAncestry());
            setTextWithLabel(R.id.cd_patronus,"Patronus: ",character.getPatronus());
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTextWithLabel(int id, String label, String value) {
        if(value != null && !value.equals("")) {
            ((TextView) getView().findViewById(id)).setText(label + (label.equals("") ? "" : " ") + value);
        } else {
            hideElement(id);
        }
    }

    private void hideElement(int id) {
        View element = getView().findViewById(id);
        if(element instanceof TextView) {
            ((TextView) element).setTextSize(0);
        }
    }

    @Override
    public void onLoadImage(Bitmap bitmap) {
        ((ImageView) getView().findViewById(R.id.cd_image)).setImageBitmap(bitmap);
    }
}