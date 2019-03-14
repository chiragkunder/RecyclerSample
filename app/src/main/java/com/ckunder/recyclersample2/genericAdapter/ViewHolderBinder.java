package com.ckunder.recyclersample2.genericAdapter;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

import java.util.List;

/**
 * Populates a {@link RecyclerView.ViewHolder} with the model details.
 */
public abstract class ViewHolderBinder {

    /**
     * Populates the passed {@link RecyclerView.ViewHolder} with the details of the passed {@link DisplayableItem}.
     */
    public abstract void bind(@NonNull final RecyclerView.ViewHolder viewHolder, @NonNull final DisplayableItem item);

    public void bind(@NonNull RecyclerView.ViewHolder holder, @NonNull final DisplayableItem item, @NonNull List<Object> payloads) {
        bind(holder, item);
    }
}
