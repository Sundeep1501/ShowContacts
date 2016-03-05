package com.contacts.sundeep.showcontacts;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sundeep on 3/6/16.
 */
public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactHolder> {

    private final List<Contact> contactList;

    public ContactRecyclerAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return new ContactHolder((TextView) v);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.view.setText((position + 1) + "- " + contact.toString());
        if (position % 2 == 0) {
            holder.view.setBackgroundColor(Color.WHITE);
        } else {
            holder.view.setBackgroundColor(Color.argb(255, 238, 238, 238));
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactHolder extends RecyclerView.ViewHolder {
        TextView view;

        public ContactHolder(TextView itemView) {
            super(itemView);
            view = itemView;
        }
    }

}
