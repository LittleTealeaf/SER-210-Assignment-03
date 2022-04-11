package edu.quinnipiac.ser210.harrypottercharacters.activities;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private int backgroundColor = Color.WHITE;

    private Category category;
    private HarryPotterCharacter character;

    private ActivityResultLauncher<Intent> colorLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryListFragment categoryListFragment = (CategoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_category);
        assert categoryListFragment != null;
        categoryListFragment.setListener(this);

        if(savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(Keys.COLOR);
            int index = savedInstanceState.getInt(Keys.CATEGORY);
            if(index != -1) {
                this.category = Category.values()[index];
            }
            this.character = savedInstanceState.getParcelable(Keys.CHARACTER);
            if(findViewById(R.id.frame_layout_character_list) != null && index != -1) {
                onCategorySelected(category);
            } else {
                if(index != -1) {
                    onCategorySelected(category);
                }
                onCharacterSelected(character);
            }


            findViewById(android.R.id.content).getRootView().setBackgroundColor(backgroundColor);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        colorLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if(result.getResultCode() == Activity.RESULT_OK) {
               assert result.getData() != null;
               backgroundColor = result.getData().getIntExtra(Keys.COLOR,Color.WHITE);
               findViewById(android.R.id.content).getRootView().setBackgroundColor(backgroundColor);
           }
        });
    }

    @Override
    public void onCategorySelected(Category category) {
        if(category != null) {
            FrameLayout frameLayout = findViewById(R.id.frame_layout_character_list);
            if(frameLayout == null) {
                Intent intent = new Intent(this,CharacterListActivity.class);
                intent.putExtra(Keys.CATEGORY,category.ordinal());
                intent.putExtra(Keys.COLOR,backgroundColor);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        menu.findItem(R.id.menu_change_colors).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_change_colors) {
            Intent intent = new Intent(this,ColorPickerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(Keys.COLOR,backgroundColor);
            intent.putExtras(bundle);
            colorLauncher.launch(intent);
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Keys.CATEGORY,category == null ? -1 : category.ordinal());
        outState.putParcelable(Keys.CHARACTER,character);
        outState.putInt(Keys.COLOR,backgroundColor);
    }
}