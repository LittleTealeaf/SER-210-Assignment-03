package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import edu.quinnipiac.ser210.harrypottercharacters.R;

public class ColorPickerFragment extends Fragment implements SeekBar.OnSeekBarChangeListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((Button) view.findViewById(R.id.color_confirm)).setOnClickListener(this::confirm);
    }

    protected void confirm(View view) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Required stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Required stub
    }
}