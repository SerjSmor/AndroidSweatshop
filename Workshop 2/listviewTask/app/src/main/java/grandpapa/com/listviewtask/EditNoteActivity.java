package grandpapa.com.listviewtask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class EditNoteActivity extends AppCompatActivity {
    private ArrayList notes;//when does code here execute
    Intent returnIntent = new Intent();
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        notes = new ArrayList();

        final Intent intent = getIntent();
        final String noteName = intent.getStringExtra("noteName");
        final String noteDetails = intent.getStringExtra("noteDetails");
        final TextView noteNameView = (TextView) (findViewById(R.id.noteName));

        returnIntent.putExtra("noteName", noteName);
        pref = getSharedPreferences(MainActivity.ContactsPref, Context.MODE_PRIVATE);
        final EditText noteDetailsview = (EditText) (findViewById(R.id.noteDetails));

        noteDetailsview.setText(noteDetails);
        noteDetailsview.setClickable(false);
        noteDetailsview.setFocusableInTouchMode(false);
        noteDetailsview.setLongClickable(false);
        noteNameView.setText(noteName);
        //Toast.makeText(getApplicationContext(), "Shit", Toast.LENGTH_LONG).show();

        Button buttonStart = (Button) findViewById(R.id.buttonFinish);
        final Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setText("Edit");//????
        buttonStart.setOnClickListener(new View.OnClickListener() {//what is view.OnClickListener() method?
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String rr =  (noteDetailsview.getText());
                if(buttonSave.getText() == "Edit"){
                    noteDetailsview.setFocusableInTouchMode(true);
                    noteDetailsview.setClickable(true);
                    noteDetailsview.setLongClickable(true);
                    buttonSave.setText("Save");

                }
                else{
                    noteDetailsview.setFocusableInTouchMode(false);
                    noteDetailsview.setClickable(false);
                    noteDetailsview.setLongClickable(false);

                    String editedString = noteDetailsview.getText().toString();

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(noteName, editedString);
                    editor.commit();

                    buttonSave.setText("Edit");
                    returnIntent.putExtra("noteDetails", editedString);
                }

            }
        });
    }}
