package com.example.czhan.safebears;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.czhan.safebears.models.Crime;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.ViewHolder> {
    List<Crime> mCrimes;
    Context context;

    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @NonNull
    @Override
    public CrimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        Log.e("test", "creating a thing");

        View conversationView = inflater.inflate(R.layout.item_crime, parent, false);
        CrimeAdapter.ViewHolder viewHolder = new CrimeAdapter.ViewHolder(conversationView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.ViewHolder holder, int position) {

        Log.e("test", "creating a thing");
        final Crime crime = mCrimes.get(position);
        final CrimeAdapter.ViewHolder h = holder;
        h.location.setText(crime.getString("location"));

    }

    @Override
    public int getItemCount() { return mCrimes.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView gender;
        public TextView hair;
        public TextView time;
        public TextView date;
        public TextView eye;
        public TextView location;
        public TextView description;
        public TextView age;
        public TextView height;
        public TextView weight;


        public ViewHolder(final View itemView) {
            super(itemView);

            // do all them findViewByIds
            /*
            gender = itemView.findViewById(R.id.gender);
            hair = itemView.findViewById(R.id.hair);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
            eye = itemView.findViewById(R.id.eye);*/
            location = itemView.findViewById(R.id.location);
            /*
            description = itemView.findViewById(R.id.description);
            age = itemView.findViewById(R.id.age);
            height = itemView.findViewById(R.id.height);
            weight = itemView.findViewById(R.id.weight);
*/
            // set onclick listener for each conversation
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Crime crime = mCrimes.get(pos);
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        }
    }
}
