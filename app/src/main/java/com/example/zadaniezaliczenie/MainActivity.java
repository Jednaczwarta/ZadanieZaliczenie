package com.example.zadaniezaliczenie;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Spinner spinner = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.zawody,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText name = findViewById(R.id.nameET);
        EditText surname = findViewById(R.id.surnameET);
        EditText quantity = findViewById(R.id.quantityET);
        CheckBox smallAndCapital = findViewById(R.id.smallAndCapitalCB);
        CheckBox numbers = findViewById(R.id.numbersCB);
        CheckBox specialLetters = findViewById(R.id.specialLettersCB);
        Button generatePass = findViewById(R.id.generatePassBTN);
        Button commit = findViewById(R.id.commitBTN);

        int quantityInt = Integer.parseInt(quantity.getText().toString());

        StringBuilder allowedCharacters = new StringBuilder();
        allowedCharacters.append("abcdefghijklmnopqrstuwvxyz");

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        generatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityInt < 4) {
                    builder.setMessage("Hasło jest za krótkie").setTitle("Błąd");
                    AlertDialog error = builder.create();
                }else {

                }
            }
        });
    }
}