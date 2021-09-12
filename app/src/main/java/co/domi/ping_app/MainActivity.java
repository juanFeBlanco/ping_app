package co.domi.ping_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        num3T = findViewById(R.id.num1);
        num4T = findViewById(R.id.num4);
        pingB = findViewById(R.id.pingBtn);
        buscarB = findViewById(R.id.buscarBtn);
        ipT = findViewById(R.id.ipText);



        buscarB.setOnClickListener(
                (view)-> {
                    searchHosts();
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

    private void searchHosts() {
        new Thread(() ->{

        });
    }


}