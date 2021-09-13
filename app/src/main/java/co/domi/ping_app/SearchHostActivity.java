package co.domi.ping_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

public class SearchHostActivity extends AppCompatActivity {

    private TextView ipsT;
    private Button backHostB;
    private String myIp, textHosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_host);

        ipsT = findViewById(R.id.hostsT);
        backHostB = findViewById(R.id.backHostBtn);

        //myIp = getIntent().getStringExtra("myIp");

       new Thread(() ->{

            try {
                InetAddress hostInetAddress = InetAddress.getByName(myIp);
                int LocalIp = hostInetAddress.hashCode();
                textHosts = formatIpAddress(LocalIp);

                runOnUiThread(() -> {
                    ipsT.setText(textHosts);
                });

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }).start();


        backHostB.setOnClickListener(
                (view) -> {
                    finish();
                }
        );
    }

    public static String formatIpAddress(int ip){
        return String.format(Locale.US, "%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
    }
}