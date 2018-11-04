package com.example.czhan.safebears;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.czhan.safebears.models.Crime;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;

public class CrimeListFragment extends Fragment {

    private ArrayList<Crime> mCrimes;
    private CrimeAdapter crimeAdapter;
    RecyclerView rvCrimes;
    Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.crime_fragment, container, false);
        return view;
    }

    public interface OnFragmentInteractionListener {
        // Placeholder, to be inserted when clicking is introduced
        void onFragmentInteraction(Uri uri);
    }

    private void setupFragmentVariables(View view) {
        context = getActivity();

    }


}
