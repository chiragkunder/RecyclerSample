package com.ckunder.recyclersample2.genericAdapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

/**
 * Instantiates a {@link RecyclerView.ViewHolder} based on the type.
 */
public abstract class SimpleViewHolderFactory implements ViewHolderFactory {

    @NonNull
    protected final Context context;

    protected SimpleViewHolderFactory(@NonNull final Context context) {
        this.context = context;
    }

    public RecyclerView.ViewHolder createViewHolder(ViewGroup viewGroup, RecyclerView.RecycledViewPool pool) {
        return createViewHolder(viewGroup);
    }

    /**
     * Creates a {@link RecyclerView.ViewHolder}
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @return the newly created {@link RecyclerView.ViewHolder}
     */
    @NonNull
    public abstract RecyclerView.ViewHolder createViewHolder(@NonNull final ViewGroup parent);
}

