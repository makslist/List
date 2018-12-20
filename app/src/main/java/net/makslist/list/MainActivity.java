package net.makslist.list;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import net.makslist.list.databinding.ActivityMainBinding;
import net.makslist.list.db.ListsDBContract;
import net.makslist.list.db.ListsSQLiteHelper;

public class MainActivity extends Activity implements ListsAdapter.ItemLongClickListener {

  private ListsAdapter adapter;
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Lists");

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    RecyclerView recyclerView = binding.listRecyclerView;
    recyclerView.setHasFixedSize(true);
    LinearLayoutManager layout = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layout);
    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layout.getOrientation());
    recyclerView.addItemDecoration(dividerItemDecoration);

    reload();

    binding.addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivityForResult(new Intent(MainActivity.this, net.makslist.list.ListActivity.class), 1);
      }
    });
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1)
      reload();
  }

  @Override
  public void onItemLongClick(View view, int position, String name) {
    adapter.notifyItemRemoved(position);

    deleteItemFromDB(name);
    reload();

    Toast.makeText(this, "Item " + name + " on position " + position + " deleted", Toast.LENGTH_SHORT).show();
  }

  private void reload() {
    ListsSQLiteHelper helper = new ListsSQLiteHelper(this);
    Cursor cursor = helper.readAllKistsFromDB();
    adapter = new ListsAdapter(this, cursor);
    adapter.setLongClickListener(this);
    binding.listRecyclerView.setAdapter(adapter);
  }

  private int deleteItemFromDB(String name) {
    SQLiteDatabase database = new ListsSQLiteHelper(this).getWritableDatabase();

    String whereClause = ListsDBContract.TableList.COLUMN_NAME + " = ?";
    String[] whereArgs = {name};

    return database.delete(ListsDBContract.TableList.TABLE_NAME, whereClause, whereArgs);
  }

}