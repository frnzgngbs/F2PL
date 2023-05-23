package com.example.f2pl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<User> user;

    public Adapter(List<User> user) {
        this.user = user;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboarrds_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String index = user.get(position).getPosition1();
        String email = user.get(position).getEmail();
        String score = user.get(position).getDailyScore1();
        holder.setData(index,email, score);

    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView index, email, score;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.counter);
            email = itemView.findViewById(R.id.email);
            score = itemView.findViewById(R.id.score);

            
        }

        public void setData(String position, String email, String score) {
            this.index.setText(position);
            this.email.setText(email);
            this.score.setText(score);
        }
    }
}
