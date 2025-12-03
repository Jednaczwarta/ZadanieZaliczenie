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

import java.util.Random;

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


        StringBuilder allowedCharacters = new StringBuilder();
        allowedCharacters.append("abcdefghijklmnopqrstuwvxyz");

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        Random rn = new Random();

        StringBuilder password = new StringBuilder();

        generatePass.setOnClickListener(v -> {
            int quantityInt = Integer.parseInt(quantity.getText().toString());
            if (quantityInt < 4) {
                builder.setMessage("Hasło jest za krótkie").setTitle("Błąd");
                AlertDialog error = builder.create();
                error.show();
            }else {
                if (smallAndCapital.isChecked()) {
                    allowedCharacters.append("ABCDEFGHIJKLMNOPQRSTUWVXYZ");
                }
                if (numbers.isChecked()) {
                    allowedCharacters.append("0123456789");
                }
                if (specialLetters.isChecked()) {
                    allowedCharacters.append("!@#$%^&*");
                }


                for (int i = 0; i < quantityInt; i++) {
                    password.append(allowedCharacters.charAt(rn.nextInt(allowedCharacters.length())));
                }
                builder.setMessage(password).setTitle("Wygenerowane hasło");
                AlertDialog newPass = builder.create();
                newPass.show();
            }
        });

        commit.setOnClickListener(v -> {
            String nameStr = name.getText().toString();
            String surnameStr = surname.getText().toString();
            String work = spinner.getSelectedItem().toString();

            String message = "Imię: " + nameStr + "\n" + "Nazwisko: " + surnameStr + "\n" + "Stanowisko: " + work + "\n" + "Hasło: " + password + "\n";

            builder.setMessage(message).setTitle("Dane pracownika");
            AlertDialog workman = builder.create();
            workman.show();
        });
    }
}