package com.example.to_do_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {
    static ArrayAdapter<String> aa;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText champs = (EditText) findViewById(R.id.champ_saisie);
        Button button = (Button) findViewById(R.id.button);
        ListView liste1 = (ListView) findViewById(R.id.liste);
        final ArrayList<String> todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        liste1.setAdapter(aa);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                todoItems.add(0, champs.getText().toString()); // 1
                aa.notifyDataSetChanged(); // 2
                champs.setText(" ");// 3
            }
        });
        champs.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
                        todoItems.add(0, champs.getText().toString()); // 1
                        aa.notifyDataSetChanged(); // 2
                        champs.setText(" ");// 3
                    champs.setText(""); // 3 - remise à vide de l'EditText
                        return true;               }
                return false;
            }
        }) ;
        liste1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int  position, long id) {
                todoItems.remove(position);
                aa.notifyDataSetChanged(); // 2
            }
    });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menu
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    aa.clear();
                }
            }).setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setMessage(R.string.test);
            builder.setTitle(R.string.confirmation);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
