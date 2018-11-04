package com.example.czhan.safebears;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.czhan.safebears.models.Crime;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

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
        setupFragmentVariables(view);

        mCrimes = new ArrayList<>();
        crimeAdapter = new CrimeAdapter(mCrimes);
        rvCrimes.setLayoutManager(new LinearLayoutManager(context));
        rvCrimes.setAdapter(crimeAdapter);

        final ParseQuery<Crime> crimesQuery = new Crime.Query();
        crimesQuery.findInBackground(new FindCallback<Crime>() {
            @Override
            public void done(List<Crime> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); ++i) {
                        mCrimes.add(objects.get(i));
                        crimeAdapter.notifyItemInserted(mCrimes.size() - 1);
                        Log.e("test", objects.get(i).getLocation());
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        // Placeholder, to be inserted when clicking is introduced
        void onFragmentInteraction(Uri uri);
    }



    private void setupFragmentVariables(View view) {
        context = getActivity();
        rvCrimes = view.findViewById(R.id.rvCrimes);


    }


}
