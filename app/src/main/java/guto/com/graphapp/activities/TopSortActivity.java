package guto.com.graphapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import guto.com.graphapp.Controller;
import guto.com.graphapp.R;
import guto.com.graphapp.structures.Graph;
import guto.com.graphapp.structures.Vertex;
import guto.com.graphapp.texts.TextsEN;

import java.util.ArrayList;

public class TopSortActivity extends Activity implements View.OnClickListener {

    ListView lvTopSort;
    Button bDescription, bHelp;
    String title, description, complexity;
    Graph graph = Controller.getGraph();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Vertex> vertices = graph.topologicalSort();
        String[] topSort = new String[graph.getVertices().size()];

        if (vertices.get(0).getName().equals("Not directed")) {
            Toast.makeText(TopSortActivity.this, TextsEN.getErrorByPosition(4), Toast.LENGTH_LONG).show();
            finish();
        } else if (vertices.get(0).getName().equals("cycle")) {
            Toast.makeText(TopSortActivity.this, TextsEN.getErrorByPosition(5), Toast.LENGTH_LONG).show();
            finish();
        }else{
            int count = 1;
            for (Vertex v: vertices) {
                topSort[count-1] = count + ". " + v.getName();
                count++;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sort);

        Intent i1 = getIntent();
        title = i1.getStringExtra("title");
        description = i1.getStringExtra("description");
        complexity = i1.getStringExtra("complexity");

        lvTopSort = (ListView) findViewById(R.id.lvTopSort);
        bHelp = (Button)findViewById(R.id.bHelp);
        bDescription = (Button)findViewById(R.id.bDescription);

        bDescription.setOnClickListener(this);
        bHelp.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topSort);
        lvTopSort.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bDescription:
                Intent i2 = new Intent(getApplicationContext(), DescriptionActivity.class);
                i2.putExtra("title",title);
                i2.putExtra("previous",3);
                i2.putExtra("description",description);
                i2.putExtra("complexity", complexity);
                startActivity (i2);
                break;
            case R.id.bHelp:
                Toast.makeText(TopSortActivity.this, TextsEN.getHelpByPosition(4), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
