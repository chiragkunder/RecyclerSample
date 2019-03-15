package com.ckunder.recyclersample2.genericAdapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link ListAdapter} for {@link DisplayableItem}.
 * Calculates DiffUtils on a background thread
 */
public class RecyclerViewAdapter extends ListAdapter<DisplayableItem, RecyclerView.ViewHolder> {

    @NonNull
    private final List<DisplayableItem> modelItems = new ArrayList<>();

    @NonNull
    private final Map<Integer, ViewHolderFactory> factoryMap;

    @NonNull
    private final Map<Integer, ViewHolderBinder> binderMap;

    public RecyclerViewAdapter(@NonNull final ItemComparator comparator,
                               @NonNull final Map<Integer, ViewHolderFactory> factoryMap,
                               @NonNull final Map<Integer, ViewHolderBinder> binderMap) {
        super(new RecyclerDiffItemCallback(comparator));
        this.factoryMap = factoryMap;
        this.binderMap = binderMap;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return factoryMap.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final DisplayableItem item = getItem(position);
        binderMap.get(item.getType()).bind(holder, item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        final DisplayableItem item = getItem(position);
        binderMap.get(item.getType()).bind(holder, item, payloads);
    }

    @Override
    public int getItemViewType(final int position) {
        return getItem(position).getType();
    }

    @Override
    public long getItemId(final int position) {
        return getItem(position).getId();
    }

    /**
     * @return the actual model items in the adapter
     */
    @NonNull
    public List<DisplayableItem> getModelItems() {
        return modelItems;
    }

    /**
     * Updates modelItems currently stored in adapter with the new modelItems.
     *
     * @param items collection to update the previous values
     */
    public void update(@NonNull final List<DisplayableItem> items) {
        submitList(items);
    }

    @Override
    public void submitList(final List<DisplayableItem> list) {
        updateItemsInModel(list);
        super.submitList(list);
    }

    private void updateItemsInModel(@NonNull final List<DisplayableItem> items) {
        modelItems.clear();
        modelItems.addAll(items);
    }

    private static final class RecyclerDiffItemCallback extends DiffUtil.ItemCallback<DisplayableItem> {

        private final ItemComparator comparator;

        private RecyclerDiffItemCallback(final ItemComparator comparator) {
            this.comparator = comparator;
        }

        @Override
        public boolean areItemsTheSame(final DisplayableItem oldItem, final DisplayableItem newItem) {
            return comparator.areItemsTheSame(oldItem, newItem);
        }

        @Override
        public boolean areContentsTheSame(final DisplayableItem oldItem, final DisplayableItem newItem) {
            return comparator.areContentsTheSame(oldItem, newItem);
        }

        @Nullable
        @Override
        public Object getChangePayload(final DisplayableItem oldItem, final DisplayableItem newItem) {
            return comparator.getChangePayload(oldItem, newItem);
        }
    }
}
