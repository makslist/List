package net.makslist.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import net.makslist.list.databinding.ActivityAddListBinding;
import net.makslist.list.db.ListsSQLiteHelper;

public class ListActivity extends AppCompatActivity {

  private static final String TAG = "EmployerActivity";
  private ActivityAddListBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_add_list);

    binding.saveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ListsSQLiteHelper sqlHelper = new ListsSQLiteHelper(ListActivity.this);
        String name = binding.nameEditText.getText().toString();
        String description = binding.descEditText.getText().toString();
        String type = "ToDo";
        long newRowId = sqlHelper.saveListToDB(name, description, type);

        Toast.makeText(ListActivity.this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
        finish();
      }
    });

  }

}