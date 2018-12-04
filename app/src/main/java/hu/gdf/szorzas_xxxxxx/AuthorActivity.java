package hu.gdf.szorzas_xxxxxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AuthorActivity extends AppCompatActivity {

    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        resultView = findViewById(R.id.result2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("result");
        resultView.setText(message);
    }
}
