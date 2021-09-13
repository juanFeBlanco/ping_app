package co.domi.ping_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private EditText num1T;
    private EditText num2T;
    private EditText num3T;
    private EditText num4T;
    private Button pingB;
    private Button buscarB;
    private TextView ipT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1T = findViewById(R.id.num1);
        num2T = findViewById(R.id.num2);
        num3T = findViewById(R.id.num3);
        num4T = findViewById(R.id.num4);
        pingB = findViewById(R.id.pingBtn);
        buscarB = findViewById(R.id.buscarBtn);
        ipT = findViewById(R.id.ipText);

        pingB.setOnClickListener(
                (view) -> {
                    adjustInput();
                    Intent pingIntent = new Intent(this, PingActivity.class);
                    pingIntent.putExtra("theIp", getIp());
                    startActivity(pingIntent);
                }
        );

        buscarB.setOnClickListener(
                (view)-> {
                        Intent SearchH = new Intent(this, SearchHostActivity.class);
                        SearchH.putExtra("theIp", getIp());
                        startActivity(SearchH);
                }
        );

        //Get host ip
        new Thread(() ->{
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                runOnUiThread(() ->{
                    ipT.setText(inetAddress.getHostAddress());
                });
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void adjustInput() {
        if(num1T.getText().toString().equals("") || Double.parseDouble(num1T.getText().toString()) < 0){
            num1T.setText("0");
        }else if(Double.parseDouble(num1T.getText().toString()) > 254){
            num1T.setText("254");
        }

        if(num2T.getText().toString().equals("") || Double.parseDouble(num2T.getText().toString()) < 0){
            num2T.setText("0");
        }else if(Double.parseDouble(num2T.getText().toString()) > 254){
            num2T.setText("254");
        }

        if(num3T.getText().toString().equals("") || Double.parseDouble(num3T.getText().toString()) < 0){
            num3T.setText("0");
        }else if(Double.parseDouble(num3T.getText().toString()) > 254){
            num3T.setText("254");
        }

        if(num4T.getText().toString().equals("") || Double.parseDouble(num4T.getText().toString()) < 0){
            num4T.setText("0");
        }else if(Double.parseDouble(num4T.getText().toString()) > 254){
            num4T.setText("254");
        }
    }

    private String getIp(){
        return num1T.getText().toString() + "." +
                num2T.getText().toString() + "." +
                num3T.getText().toString() + "." +
                num4T.getText().toString();
    }


}