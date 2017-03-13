package com.photon.codechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etRows,etColmns;
    Button btSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRows    = (EditText) findViewById(R.id.editText);
        etColmns = (EditText) findViewById(R.id.editText2);

        etRows.requestFocus();
        btSubmit = (Button) findViewById(R.id.button);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmptyFields())
                {
                    Toast.makeText(MainActivity.this,"Please enter No. of rows and Columns",Toast.LENGTH_SHORT).show();
                }else
                        navigateToGridView();
            }
        });

    }

    private void navigateToGridView() {
        Intent intent = new Intent(MainActivity.this,GridSelectionActivity.class);
        intent.putExtra("numRows",Integer.parseInt(etRows.getText().toString()));
        intent.putExtra("numCols",Integer.parseInt(etColmns.getText().toString()));
        startActivity(intent);
        finish();
    }

    private boolean validateEmptyFields() {

        if(etColmns.length()==0 || etRows.length()==0)
            return true;
        return false;
    }
}
