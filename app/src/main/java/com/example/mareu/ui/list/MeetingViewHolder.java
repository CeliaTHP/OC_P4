package com.example.mareu.ui.list;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.ItemLayoutBinding;
import com.example.mareu.model.Meeting;

public class MeetingViewHolder {
    /**
     * ViewHolder used for a Meeting list
     *
     * @see Meeting
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ItemLayoutBinding itemLayoutBinding;

        public ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.itemLayoutBinding = itemLayoutBinding;
        }
    }

}
