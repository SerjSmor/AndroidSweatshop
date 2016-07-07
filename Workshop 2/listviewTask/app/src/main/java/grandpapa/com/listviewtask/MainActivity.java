package grandpapa.com.listviewtask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // consts
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PREFS_NAME = "PREFS";
    private static final String NOTE_NAME = "noteName";
    private static final String NOTE_NAME1 = "noteName";
    private static final String NOTE_DETAILS = "noteDetails";

    private static final int EDIT_QUEST_CODE = 1;

    private SharedPreferences pref;


    private EditText myText;
    private ArrayList<String> values = new ArrayList<>();
    private ArrayList<String> details = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        Button myButton = (Button)findViewById(R.id.button);

        myText = (EditText)findViewById(R.id.editText);

        for( String key : pref.getAll().keySet() ){
            String detailsString = pref.getString(key, "Error");
            details.add(detailsString);
            values.add(key);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                values);


        myList = (ListView)findViewById(R.id.Listtt);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add();

            }
        });
        myList.setAdapter((adapter));
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editActivityIntent = new Intent(MainActivity.this, EditNoteActivity.class);
                editActivityIntent.putExtra("noteName", (String)(values.get(position)));//array[x] is string, why (string)?
                editActivityIntent.putExtra("noteDetails", (String)(details.get(position)));
                editor.putString((String) (values.get(position)), (String) (details.get(position)));
                startActivityForResult(editActivityIntent, EDIT_QUEST_CODE);//what is edit quest code
;
//               String itemValue = (String) myList.getItemAtPosition(position);
//               Toast.makeText(getApplicationContext(), "Postion : " + position + "listItem" + itemValue, Toast.LENGTH_LONG).show();

            }
        });
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {//error if we click on it it calls normal click also
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                // set dialog message
                alertDialogBuilder
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this note?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String removeMe = values.get(position).toString();
                                values.remove(position);
                                editor.remove(removeMe);//not roking
                                editor.commit();
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return false;
            }
        });

//        Intent bob = new Intent(MainActivity.this, EditNoteActivity.class);
//        bob.putExtra("myVar", "Hey Yoav");
//        startActivityForResult(bob, EDIT_QUEST_CODE);
//        startActivity(bob);

    }

    private void add() {
        String string = myText.getText().toString();
        values.add(string);
        details.add("");
        adapter.notifyDataSetChanged();
        myText.setText("");
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(string, "");
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == EDIT_QUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                int i = values.indexOf(data.getStringExtra(NOTE_NAME));
                String text = data.getStringExtra(NOTE_NAME1);
                details.set(i, data.getStringExtra(NOTE_DETAILS));
            }
        }
    }



}

