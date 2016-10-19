package com.example.fragments;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

/**
 * Created by Полина on 19.10.2016.
 */

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;
    public Context context;
    public OnItemClick click;
    public boolean isRemoved;

    public ContactsRecyclerAdapter(Context context, ArrayList<Contact> contacts, OnItemClick listener, boolean isRemoved){
        this.contacts = contacts;
        this.context = context;
        click = listener;
        this.isRemoved = isRemoved;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.number.setText(contacts.get(position).getNumber());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.contact));
        if(!isRemoved) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onItemClick(contacts.get(position));
                }
            });
            holder.view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new MaterialDialog.Builder(context)
                            .title("Удалить контакт?")
                            .positiveColor(Color.BLUE)
                            .negativeColor(Color.RED)
                            .positiveText("Удалить")
                            .negativeText("Отмена")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    click.deleteContact(contacts.get(position));
                                    contacts.remove(position);
                                    notifyDataSetChanged();
                                }
                            })
                            .build().show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView number;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            this.imageView = (ImageView) itemView.findViewById(R.id.image);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.number = (TextView) itemView.findViewById(R.id.number);
        }
    }

}
