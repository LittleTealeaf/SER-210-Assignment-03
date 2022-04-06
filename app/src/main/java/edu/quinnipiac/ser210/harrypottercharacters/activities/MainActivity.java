package edu.quinnipiac.ser210.harrypottercharacters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.data.Category;
import edu.quinnipiac.ser210.harrypottercharacters.data.HarryPotterCharacter;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CategoryListFragment;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.CharacterListFragment;

public class MainActivity extends AppCompatActivity implements CategoryListFragment.CategoryListListener, CharacterListFragment.CharacterClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryListFragment categoryListFragment = (CategoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_category);
        assert categoryListFragment != null;
        categoryListFragment.setListener(this);

    }

    @Override
    public void onCategorySelected(Category category) {
        Intent intent = new Intent(this,CharacterListActivity.class);
        intent.putExtra(Keys.CATEGORY,category.ordinal());
        startActivity(intent);
    }


    @Override
    public void onCharacterSelected(HarryPotterCharacter character) {

    }
}