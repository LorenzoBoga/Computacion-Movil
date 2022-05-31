package com.example.cm_passwords.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cm_passwords.BottomNavigation;
import com.example.cm_passwords.EditPassword;
import com.example.cm_passwords.R;
import com.example.cm_passwords.entities.Password;

import java.util.ArrayList;
import java.util.Locale;

public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordViewHolder>{

    ArrayList<Password> passwords;

    public PasswordListAdapter(ArrayList<Password> passwords){
        this.passwords = passwords;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_password, null,
                false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {
        holder.viewSite.setText(passwords.get(position).getSite());
        holder.viewUser.setText(passwords.get(position).getUser());
        holder.viewPassword.setText(passwords.get(position).hidePassword());
        holder.viewLetter.setText(passwords.get(position).getSite().substring(0,1).toUpperCase(Locale.ROOT));
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {

        TextView viewSite, viewUser, viewPassword, viewLetter;

        public PasswordViewHolder(@NonNull View itemView) {
            super(itemView);

            viewSite = itemView.findViewById(R.id.siteView);
            viewUser = itemView.findViewById(R.id.userView);
            viewPassword = itemView.findViewById(R.id.passwordView);
            viewLetter = itemView.findViewById(R.id.letter_input);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent switchActivityIntent = new Intent(context, EditPassword.class);

                    switchActivityIntent.putExtra("id", passwords.get(getAdapterPosition()).getId());
                    switchActivityIntent.putExtra("site", passwords.get(getAdapterPosition()).getSite());
                    switchActivityIntent.putExtra("user", passwords.get(getAdapterPosition()).getUser());
                    switchActivityIntent.putExtra("password", passwords.get(getAdapterPosition()).getPassword());
                    context.startActivity(switchActivityIntent);
                }
            });
        }
    }
}
