package co.domi.ping_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingActivity extends AppCompatActivity {

    private Button backB;
    private TextView textTT;
    private String pingInfo, theIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        backB = findViewById(R.id.backBtn);
        textTT = findViewById(R.id.textoT);

        theIp = getIntent().getStringExtra("theIp");
        pingInfo="";

        new Thread(() ->{
            //multiple pings
            for (int i=0; i<7; i++) {
                try {
                    InetAddress pingInetAddress = InetAddress.getByName(theIp);

                    //verify f it is reachable
                    if (pingInetAddress.isReachable(150)) {
                        pingInfo += "Recibido\n";
                    } else {
                        pingInfo += "Perdido\n";
                    }

                    //Edit UI
                    runOnUiThread(() -> {
                        textTT.setText(pingInfo);
                    });
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        backB.setOnClickListener(
                (view) -> {
                    finish();
                }
        );
    }
}