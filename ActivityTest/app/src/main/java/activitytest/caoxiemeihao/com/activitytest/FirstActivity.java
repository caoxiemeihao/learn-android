package activitytest.caoxiemeihao.com.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();

                // finish();

                // Intent intent = new Intent(FirstActivity.this, SecondActivity.class);

                // Intent intent = new Intent("com.atom.ACTION_START");
                // intent.addCategory("com.atom.MY_CATEGORY");

                // Intent intent = new Intent(Intent.ACTION_VIEW);
                // Intent intent = new Intent("android.intent.action.VIEW");
                // intent.setData(Uri.parse("https://www.baidu.com"));

                // Intent intent = new Intent(Intent.ACTION_DIAL);
                // intent.setData(Uri.parse("tel:10086"));

                // String data = "Hellow SecondActivity.";
                // Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                // intent.putExtra("extra_name", data);
                //startActivity(intent);

                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);

                startActivityForResult(intent, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String resultData = data.getStringExtra("data_return");
                    Toast.makeText(FirstActivity.this, resultData, Toast.LENGTH_SHORT);
                    Log.d("FirstActivity", resultData);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "You clicked Rmove", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
