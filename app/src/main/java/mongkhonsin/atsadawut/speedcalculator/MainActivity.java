package mongkhonsin.atsadawut.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText distanceText, timeText;
    private TextView answerText;
    private Button clearButton, calbutton;
    private String distenceStr = "", timeStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distanceText = findViewById(R.id.distence_text);
        timeText = findViewById(R.id.time_text);
        clearButton = findViewById(R.id.clear_button);
        calbutton = findViewById(R.id.calculate_button);
        answerText = findViewById(R.id.answer_text);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distanceText.setText(null);
                timeText.setText(null);
                answerText.setText(null);
            }
        });

        calbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                distenceStr = distanceText.getText().toString();
                timeStr = timeText.getText().toString();
                if(distenceStr.equals("") || timeStr.equals("")){
                    Toast t = Toast.makeText(MainActivity.this, R.string.toastWarnningInfoIsNull, Toast.LENGTH_LONG);
                    t.show();
                }
                else if(timeStr.equals("0")){
                    Toast t = Toast.makeText(MainActivity.this, R.string.toastWarnningInfoTimeIsZero, Toast.LENGTH_LONG);
                    t.show();
                }
                else{
                    double distenceMeter = Double.parseDouble(distenceStr);
                    double timeSec = Double.parseDouble(timeStr);

                    double distenceKilometer = distenceMeter/1000;
                    double timeHour = (timeSec/60)/60;
                    double answer = distenceKilometer/timeHour;

                    String str = String.format(Locale.getDefault(), "%.2f", answer);
                    answerText.setText(str);
                    if(answer > 80){
                        AlertDialog.Builder speedLimit = new  AlertDialog.Builder(MainActivity.this);
                        speedLimit.setTitle("SPEED CALCUTOR");
                        speedLimit.setMessage(R.string.analogWarnniSpeed);
                        speedLimit.setPositiveButton("OK", null);
                        speedLimit.show();
                    }
                }
            }
        });
    }
}