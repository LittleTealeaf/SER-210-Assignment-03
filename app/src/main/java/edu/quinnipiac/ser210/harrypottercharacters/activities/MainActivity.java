package edu.quinnipiac.ser210.harrypottercharacters.activities;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.async.FetchCharactersTask;
import edu.quinnipiac.ser210.harrypottercharacters.data.Category;
import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CategoryListFragment;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CharacterDetailsFragment;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CharacterListFragment;

public class MainActivity extends AppCompatActivity implements CategoryListFragment.CategoryListListener,
                                                               CharacterListFragment.CharacterClickedListener {

    private Category category;
    private HarryPotterCharacter character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryListFragment categoryListFragment = (CategoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_category);
        assert categoryListFragment != null;
        categoryListFragment.setListener(this);

        if(savedInstanceState != null) {
            this.category = Category.values()[savedInstanceState.getInt(Keys.CATEGORY)];
            this.character = savedInstanceState.getParcelable(Keys.CHARACTER);
            if(findViewById(R.id.frame_layout_character_list) != null) {
                onCategorySelected(category);
            } else {
                onCategorySelected(category);
                onCharacterSelected(character);
            }
        }

    }

    @Override
    public void onCategorySelected(Category category) {
        if(category != null) {
            FrameLayout frameLayout = findViewById(R.id.frame_layout_character_list);
            if(frameLayout == null) {
                Intent intent = new Intent(this,CharacterListActivity.class);
                intent.putExtra(Keys.CATEGORY,category.ordinal());
                if(character != null) {
                    intent.putExtra(Keys.CHARACTER,character);
                }
                startActivity(intent);
            } else {
                CharacterListFragment fragment = new CharacterListFragment();
                fragment.setListener(this);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout_character_list,fragment);
                ft.setTransition(TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
                new FetchCharactersTask(fragment).execute(category.getEndpoint());
                this.category = category;
            }
        }
    }


    @Override
    public void onCharacterSelected(HarryPotterCharacter character) {
        if(character != null) {
            FrameLayout frameLayout = findViewById(R.id.frame_layout_character_details);
            if(frameLayout == null) {
                System.out.println("HOW THE HECK DID WE GET HERE");
            } else {
                CharacterDetailsFragment fragment = new CharacterDetailsFragment();
                fragment.displayCharacter(character);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout_character_details,fragment);
                ft.setTransition(TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
                this.character = character;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Keys.CATEGORY,category == null ? null : category.ordinal());
        outState.putParcelable(Keys.CHARACTER,character);
    }
}