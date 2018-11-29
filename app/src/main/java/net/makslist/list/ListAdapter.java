package net.makslist.list;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<String> values;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter() {

        List<String> input = new ArrayList<>();

        input.clear();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }// define an adapter
        values = input;

    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.list_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        holder.txtList.setText(name);
        holder.txtList.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        int cntSubItems = (int) Math.floor((Math.random() * 9) + 1);
        String cntSub = ""+cntSubItems;
        holder.cntItems.setText(cntSub);
        holder.txtFooter.setText("Footer: " + name);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // SQLite import Test
        // each data item is just a string in this case
        public TextView txtList;;
        public TextView txtFooter;
        public TextView cntItems;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtList = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            cntItems = v.findViewById(R.id.countSubitems);
        }
    }

}