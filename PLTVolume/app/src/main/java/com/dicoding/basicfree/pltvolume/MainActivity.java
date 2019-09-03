package com.dicoding.basicfree.pltvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener
{
    private EditText edtWidth;
    private EditText edtHeight;
    private EditText edtLength;
    private Button btnCalculate;
    private EditText calResult;
    private static final String STATE_FINAL = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHeight = findViewById(R.id.edt_height);
        edtWidth = findViewById(R.id.edt_width);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        calResult = findViewById(R.id.cal_result);

        btnCalculate.setOnClickListener(this);

        if(savedInstanceState!=null)
        {
            String result = savedInstanceState.getString(STATE_FINAL);
            calResult.setText(result);
        }
    }

    private Double toDouble(String str)
    {
        try
        {
            return Double.valueOf(str);
        } catch (NumberFormatException e)
        {
            return null;
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.btn_calculate)
        {
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyField = false;
            boolean isInvalidDouble = false;

            if(TextUtils.isEmpty(inputLength))
            {
                isEmptyField = true;
                edtLength.setError("Field Tidak Boleh Kosong");
            }

            if(TextUtils.isEmpty(inputHeight))
            {
                isEmptyField = true;
                edtHeight.setError("Field Tidak Boleh Kosong");
            }

            if(TextUtils.isEmpty(inputWidth))
            {
                isEmptyField = true;
                edtWidth.setError(("Field Tidak Boleh Kosong"));
            }

            Double length = toDouble(inputLength);
            Double height = toDouble(inputHeight);
            Double width = toDouble(inputWidth);

            if(length ==  null)
            {
                isInvalidDouble = true;
                edtLength.setError("Field Hanya Berupa Angka!!");
            }

            if (height == null)
            {
                isInvalidDouble = true;
                edtHeight.setError("Field Hanya Berupa Angka!!");
            }

            if(width == null)
            {
                isInvalidDouble = true;
                edtWidth.setError("Field Hanya Berupa Angka");
            }

            if(!isEmptyField && !isInvalidDouble)
            {
                Double volume = length * width * height;
                calResult.setText(String.valueOf(volume));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_FINAL, calResult.getText().toString());
    }
}
