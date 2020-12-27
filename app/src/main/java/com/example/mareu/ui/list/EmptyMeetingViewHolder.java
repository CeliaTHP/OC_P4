package com.example.mareu.ui.list;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.databinding.EmptyItemLayoutBinding;

public class EmptyMeetingViewHolder {

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyItemLayoutBinding emptyItemLayoutBinding;

        public EmptyViewHolder(EmptyItemLayoutBinding emptyItemLayoutBinding) {
            super(emptyItemLayoutBinding.getRoot());
            this.emptyItemLayoutBinding = emptyItemLayoutBinding;
        }
    }
}