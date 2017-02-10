package guto.com.graphapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import guto.com.graphapp.R;
import guto.com.graphapp.texts.TextsEN;

public class VertexActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvStart;
    Button bHelp, bNext;
    EditText etVertex;
    LinearLayout llWeight, llVertex2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        llWeight = (LinearLayout)findViewById(R.id.llWeight);
        llWeight.setVisibility(View.GONE);
        llVertex2 = (LinearLayout)findViewById(R.id.llVertex2);
        llVertex2.setVisibility(View.GONE);

        tvStart = (TextView)findViewById(R.id.tvStart);
        tvStart.setText(TextsEN.getDescriptionByPosition(12));

        bHelp = (Button)findViewById(R.id.bHelp);
        bNext = (Button)findViewById(R.id.bNext);
        bNext.setText(TextsEN.getDescriptionByPosition(11));

        etVertex = (EditText)findViewById(R.id.etStart);

        bHelp.setOnClickListener(this);
        bNext.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vertex, menu);
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
        String vertex = etVertex.getText().toString();

        switch (v.getId()){
            case R.id.bHelp:
                Toast.makeText(VertexActivity.this, TextsEN.getHelpByPosition(2), Toast.LENGTH_LONG).show();
                break;
            case R.id.bNext:
                if (vertex.equals("")){
                    Toast.makeText(VertexActivity.this, TextsEN.getHelpByPosition(2), Toast.LENGTH_LONG).show();
                }else {
                    Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                    i.putExtra("previous", 1);
                    i.putExtra("vertex", vertex);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
