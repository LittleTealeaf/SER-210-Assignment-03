package edu.quinnipiac.ser210.harrypottercharacters.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.quinnipiac.ser210.harrypottercharacters.data.Category;

public class CategoryListFragment extends ListFragment {

    private CategoryListListener listener;

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener != null) {
            listener.onCategorySelected(Category.values()[position]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String[] categories = new String[Category.values().length];
        for(int i = 0; i < categories.length; i++){
            categories[i] = Category.values()[i].toString();
        }

        setListAdapter(new ArrayAdapter<>(inflater.getContext(),android.R.layout.simple_list_item_1,categories));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setListener(CategoryListListener listener) {
        this.listener = listener;
    }

    public interface CategoryListListener {
        void onCategorySelected(Category category);
    }

}