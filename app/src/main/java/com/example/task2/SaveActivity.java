package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {

    EditText namEdit , emailEdit;
    TextView nameTextView, emailTextView;
    Button btnSave;
    String nameText ,emailText ;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME_TEXT = "nameText";
    public static final String EMAIL_TEXT = "emailText";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        namEdit = findViewById(R.id.name_edit_Text);
        emailEdit = findViewById(R.id.email_edit_text);
        nameTextView = findViewById(R.id.name_text);
        emailTextView = findViewById(R.id.email_text);

        btnSave = findViewById(R.id.btn_log);
        btnSave.setOnClickListener(view -> {
            nameTextView.setText(namEdit.getText().toString());
            emailTextView.setText(emailEdit.getText().toString());
            saveData();
        });

        loadData();
        upDateView();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME_TEXT , nameTextView.getText().toString());
        editor.putString(EMAIL_TEXT , emailTextView.getText().toString());

        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        nameText = sharedPreferences.getString(NAME_TEXT,"Empty");
        emailText = sharedPreferences.getString(EMAIL_TEXT,"Empty");
    }

    private void upDateView(){
        nameTextView.setText(nameText);
        emailTextView.setText(emailText);
    }
}