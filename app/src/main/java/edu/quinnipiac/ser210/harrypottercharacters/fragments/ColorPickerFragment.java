package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.graphics.Color;
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

public class ColorPickerFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private int color;

    private ColorListener listener;

    private View view;

    public void setListener(ColorListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        ((Button) view.findViewById(R.id.color_confirm)).setOnClickListener(this::confirm);



        SeekBar bar_r = ((SeekBar) view.findViewById(R.id.color_bar_r));
        SeekBar bar_b = ((SeekBar) view.findViewById(R.id.color_bar_b));
        SeekBar bar_g = ((SeekBar) view.findViewById(R.id.color_bar_g));

        //Sets the bar's change listener to this
        bar_r.setOnSeekBarChangeListener(this);
        bar_g.setOnSeekBarChangeListener(this);
        bar_b.setOnSeekBarChangeListener(this);

        Color color = Color.valueOf(this.color);

        //Sets the values of each bar
        bar_r.setProgress((int) (color.red() * 100));
        bar_g.setProgress((int) (color.green() * 100));
        bar_b.setProgress((int) (color.blue() * 100));
    }


    protected void confirm(View view) {
        if(listener != null) {
            listener.colorSelected(color);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Color tmp = Color.valueOf(color);
        if(seekBar.getId() == R.id.color_bar_r) {
            this.color = Color.valueOf((float) (i / 100.0),tmp.green(),tmp.blue()).toArgb();
        } else if(seekBar.getId() == R.id.color_bar_g) {
            this.color = Color.valueOf(tmp.red(),(float) (i / 100.0),tmp.blue()).toArgb();
        } else if(seekBar.getId() == R.id.color_bar_b) {
            this.color = Color.valueOf(tmp.red(),tmp.green(),(float) (i / 100.0)).toArgb();
        }
        updateColorView();
    }

    public void setColor(int color) {
        System.out.println("hello");
        this.color = color;
        Color c = Color.valueOf(color);
        ((SeekBar) view.findViewById(R.id.color_bar_r)).setProgress((int) (c.red() * 100));
        ((SeekBar) view.findViewById(R.id.color_bar_g)).setProgress((int) (c.green() * 100));
        ((SeekBar) view.findViewById(R.id.color_bar_b)).setProgress((int) (c.blue() * 100));
        updateColorView();
    }

    protected void updateColorView() {
        view.setBackgroundColor(color);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Required stub
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Required stub
    }

    public interface ColorListener {
        void colorSelected(int color);
    }
}