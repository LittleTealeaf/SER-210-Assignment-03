package edu.quinnipiac.ser210.harrypottercharacters.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import edu.quinnipiac.ser210.harrypottercharacters.Keys;
import edu.quinnipiac.ser210.harrypottercharacters.R;
import edu.quinnipiac.ser210.harrypottercharacters.fragments.ColorPickerFragment;

public class ColorPickerActivity extends AppCompatActivity implements ColorPickerFragment.ColorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        ColorPickerFragment fragment = (ColorPickerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_color);
        assert fragment != null;
        fragment.setListener(this);

        fragment.setColor(getIntent().getExtras().getInt(Keys.COLOR));

    }

    @Override
    public void colorSelected(int color) {
        Intent intent = new Intent();
        intent.putExtra(Keys.COLOR,color);
        setResult(RESULT_OK,intent);
        finish();
    }
}