package com.example.ivan.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int base;
    String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText userInput = (EditText) findViewById(R.id.input_value);
        assert userInput !=null;


        /* Grabbing the user input and setting it to a string value
        EditText userInput = (EditText) findViewById(R.id.input_value);
        String input = userInput.getText().toString();*/
        final RadioGroup radioValueGroup = (RadioGroup)findViewById(R.id.radioInputs); //AN object to refer to any rado in this radio group
        final OnClickListener radioListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean checked = ((RadioButton) v).isChecked();
                MainActivity obj = new MainActivity();
                //Check which radio button was clicked
                int selectedRadio = radioValueGroup.getCheckedRadioButtonId();
                RadioButton RadioButtonInputButton = (RadioButton)findViewById(selectedRadio);
                assert RadioButtonInputButton!=null;
                if (RadioButtonInputButton.getText().equals("Binary")) {
                    Log.d("RadioDEBUG", "onClick: radioButtonInput was this when selected  "+RadioButtonInputButton.getText());
                    base = 2;
                    userInput.setInputType(InputType.TYPE_CLASS_NUMBER);

                }
                else if(RadioButtonInputButton.getText().equals("Octal")){
                    base = 8;

                }
                else if(RadioButtonInputButton.getText().equals("Hex")){
                    userInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    base = 16;

                }
                else if(RadioButtonInputButton.getText().equals("Decimal")){
                    base = 10;
                    Log.d("baseERROR", "onClick: BASE VALUE "+base);
                }
                else {
                    Log.d("RadioDEBUG", "onClick: radioButtonInput was this when selected " +RadioButtonInputButton.getText());
                }
            }
        };
        final  RadioButton binRadio = (RadioButton) findViewById(R.id.binaryRadio);
        assert binRadio !=null;
        binRadio.setOnClickListener(radioListener);
        final RadioButton octRadio = (RadioButton) findViewById(R.id.octalRadio);
        assert octRadio !=null;
        octRadio.setOnClickListener(radioListener);
        final RadioButton decRadio = (RadioButton)findViewById(R.id.decimalRadio);
        assert decRadio !=null;
        decRadio.setOnClickListener(radioListener);

        final RadioButton hexRadio = (RadioButton)findViewById(R.id.hexRadio);
        assert hexRadio !=null;
        hexRadio.setOnClickListener(radioListener);

        /* Deals with the conversion of the input to the other number representations
        *  It will be dependent on the base set by which radio button was selected*/
        final Button btnConvert = (Button)findViewById(R.id.buttonConvert);
        assert btnConvert !=null;
        btnConvert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value;
                TextView outputValue;
                input = userInput.getText().toString();
                Log.d("inputERROR", "onClick: input was" + input);
                try {
                    if(base == 0) Log.d("baseERROR", "onClick: base was 0 " );
                }finally {
                    ;
                }
                switch(base){
                    case 2:
                        userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        outputValue = (TextView)findViewById(R.id.binaryValue);
                        assert outputValue!=null;
                        try {
                            value = Integer.parseInt(input, base);
                            outputValue.setText(Integer.toBinaryString(value));
                            outputValue = (TextView)findViewById(R.id.hexValue);
                            outputValue.setText(Integer.toHexString(value));
                            outputValue = (TextView)findViewById(R.id.octalValue);
                            outputValue.setText(Integer.toOctalString(value));
                            outputValue = (TextView)findViewById(R.id.decimalValue);
                            outputValue.setText(Integer.toString(value));
                        } catch (Exception e) {
                            Log.d("catchERROR", "onClick: Expection e was raised with: "+e);
                            Toast.makeText(MainActivity.this, "Illegal number"+"("+input+")"+"for base  "+base, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 8:
                            userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                            outputValue = (TextView)findViewById(R.id.octalValue);
                            assert outputValue != null;
                        try {
                                value = Integer.parseInt(input, base);
                                outputValue.setText(Integer.toOctalString(value));
                                outputValue = (TextView)findViewById(R.id.hexValue);
                                outputValue.setText(Integer.toHexString(value));
                                outputValue = (TextView)findViewById(R.id.decimalValue);
                                outputValue.setText(Integer.toOctalString(value));
                                outputValue = (TextView)findViewById(R.id.decimalValue);
                                outputValue.setText(Integer.toString(value));
                            }catch (Exception e){
                                Toast.makeText(MainActivity.this, "Illegal number"+"("+input+")"+"for base  "+base, Toast.LENGTH_SHORT).show();
                            }
                        break;
                    case 10:
                        userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        outputValue = (TextView)findViewById(R.id.decimalValue);
                        assert outputValue!=null;
                        try {
                            value = Integer.parseInt(input, base);
                            //outputValue.setText(Integer.toString(value));
                            outputValue.setText(Integer.toString(value));
                            outputValue = (TextView)findViewById(R.id.hexValue);
                            outputValue.setText(Integer.toHexString(value));
                            outputValue = (TextView)findViewById(R.id.octalValue);
                            outputValue.setText(Integer.toOctalString(value));
                            outputValue = (TextView)findViewById(R.id.binaryValue);
                            outputValue.setText(Integer.toBinaryString(value));
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Illegal number"+"("+input+")"+"for base  "+base, Toast.LENGTH_SHORT).show();
                            Log.d("exceptionERROR", "onClick: "+e);
                        }
                        break;
                    case 16:
                        outputValue = (TextView)findViewById(R.id.hexValue);
                        assert outputValue!=null;
                        try {
                            value = Integer.parseInt(input, base);
                            Log.d("valueERROR", "onClick: value in decimal for base 16 is: "+value);
                            outputValue.setText(Integer.toHexString(Integer.parseInt(input, base)));
                            outputValue = (TextView)findViewById(R.id.binaryValue);
                            outputValue.setText(Integer.toBinaryString(value));
                            outputValue = (TextView)findViewById(R.id.octalValue);
                            outputValue.setText(Integer.toOctalString(value));
                            outputValue = (TextView)findViewById(R.id.decimalValue);
                            outputValue.setText(Integer.toString(value));
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Illegal number"+"("+input+")"+"for base  "+base, Toast.LENGTH_SHORT).show();
                            Log.d("exceptionERROR", "onClick:  "+e);
                        }
                        break;
                }
            }
        });

        /* Closes the app and cleans up*/
        final Button btnClose = (Button)findViewById(R.id.buttonClose);
        assert btnClose != null; //android studio kept warning to add this debug test case
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
}
