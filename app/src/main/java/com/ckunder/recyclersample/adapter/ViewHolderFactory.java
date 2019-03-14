package com.ckunder.recyclersample.adapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

/**
 * Instantiates a {@link RecyclerView.ViewHolder} based on the type.
 */
public interface ViewHolderFactory {

    RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent, RecyclerView.RecycledViewPool viewPool);
}
