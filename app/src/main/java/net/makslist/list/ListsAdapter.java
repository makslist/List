package net.makslist.list;

import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.makslist.list.databinding.ListItemLayoutBinding;
import net.makslist.list.db.ListsDBContract;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ListsViewHolder> {

  private Context context;
  private Cursor cursor;
  private ItemLongClickListener longClickListener;

  ListsAdapter(Context context, Cursor cursor) {
    this.context = context;
    this.cursor = cursor;
  }

  @Override
  public ListsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
    return new ListsViewHolder(v);
  }

  @Override
  public void onBindViewHolder(final ListsViewHolder holder, final int position) {
    cursor.moveToPosition(position);
    holder.bindCursor(cursor);
  }

  @Override
  public int getItemCount() {
    return cursor.getCount();
  }

  class ListsViewHolder extends RecyclerView.ViewHolder {

    private ListItemLayoutBinding binding;

    ListsViewHolder(View v) {
      super(v);
      binding = DataBindingUtil.bind(v);

      v.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          if (longClickListener != null)
            longClickListener.onItemLongClick(v, getAdapterPosition(), binding.name.getText().toString());
          return true;
        }
      });
    }

    void bindCursor(Cursor cursor) {
      binding.name.setText(cursor.getString(
          cursor.getColumnIndexOrThrow(ListsDBContract.TableList.COLUMN_NAME)
      ));
      binding.description.setText(cursor.getString(
          cursor.getColumnIndexOrThrow(ListsDBContract.TableList.COLUMN_DESCRIPTION)
      ));
    }

  }

  public void setLongClickListener(ItemLongClickListener longClckListener) {
    this.longClickListener = longClckListener;
  }

  public interface ItemLongClickListener {
    void onItemLongClick(View view, int position, String name);
  }

}