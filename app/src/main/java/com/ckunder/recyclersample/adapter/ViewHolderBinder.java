package com.ckunder.recyclersample.adapter;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

/**
 * Populates a {@link RecyclerView.ViewHolder} with the model details.
 */
public interface ViewHolderBinder {

    /**
     * Populates the passed {@link RecyclerView.ViewHolder} with the details of the passed {@link DisplayableItem}.
     */
    void bind(@NonNull final RecyclerView.ViewHolder viewHolder, @NonNull final DisplayableItem item);
}
