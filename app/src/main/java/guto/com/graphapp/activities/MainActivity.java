package guto.com.graphapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import guto.com.graphapp.R;
import guto.com.graphapp.texts.TextsEN;

public class MainActivity extends AppCompatActivity {

    CheckBox cbDirected, cbRandom;
    Button bStart, bHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbDirected = (CheckBox)findViewById(R.id.cbDirected);
        cbRandom = (CheckBox)findViewById(R.id.cbRandom);

        bHelp = (Button)findViewById(R.id.bHelp);
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, TextsEN.getHelpByPosition(0), Toast.LENGTH_LONG).show();
            }
        });

        bStart = (Button)findViewById(R.id.bStart);
        bStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                i.putExtra("previous",0);
                i.putExtra("directed",cbDirected.isChecked());
                i.putExtra("random", cbRandom.isChecked());
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_LONG).show();
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
}
