package co.domi.ping_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
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
        myIp = getIntent().getStringExtra("theIp");
        String[] nums = myIp.split(".");

        new Thread(() ->{
            for (int i=0; i<254; i++) {
                try {
                    InetAddress pingInetAddress = InetAddress.getByName(nums[0]+"."+nums[1]+"."+nums[2]+"."+i);

                    //verify f it is reachable
                    if (pingInetAddress.isReachable(100)) {
                        textHosts += nums[0]+"."+nums[1]+"."+nums[2]+"."+i+"\n";
                    }

                    //Edit UI
                    runOnUiThread(() -> {
                        ipsT.setText(textHosts);
                    });
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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