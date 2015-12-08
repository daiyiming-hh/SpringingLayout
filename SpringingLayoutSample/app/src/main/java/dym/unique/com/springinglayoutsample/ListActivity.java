package dym.unique.com.springinglayoutsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import dym.unique.com.springinglayoutlibrary.view.SpringingListView;

public class ListActivity extends AppCompatActivity {

    private SpringingListView slv_list = null;
    private ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        slv_list = (SpringingListView) findViewById(R.id.slv_list);
        slv_list.setOnSpringingListViewItemClickListener(new SpringingListView.OnSpringingListViewItemClickListener() {
            @Override
            public void OnSpringingListViewItemClicked(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1);
        slv_list.setAdapter(adapter);

        for (int i = 0; i < 12; i ++) {
            adapter.add(String.valueOf(i));
        }
        adapter.notifyDataSetInvalidated();
    }
}

