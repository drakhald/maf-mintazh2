package hu.gdf.szorzas_xxxxxx;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private ProgressBar progressBar;

    private String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.number1);
        editText2 = findViewById(R.id.number2);
        textView = findViewById(R.id.result);
        progressBar = findViewById(R.id.progress_horizontal);

        editText1.addTextChangedListener(new MyTextWatcher(editText1));
        editText2.addTextChangedListener(new MyTextWatcher(editText2));
    }

    public void product(View view) {
        int num1 = Integer.parseInt(editText1.getText().toString());
        int num2 = Integer.parseInt(editText2.getText().toString());

        long result = (long) num1 * num2;

        message = num1 + "*" + num2 + "==" + result;

        textView.setText(message);

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void productWithProgress(View view) {
        int num1 = Integer.parseInt(editText1.getText().toString());
        int num2 = Integer.parseInt(editText2.getText().toString());

        message = num1 + "*" + num2 + "==";

        editText1.setEnabled(false);
        editText2.setEnabled(false);

        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute(num1, num2);
    }

    public static long multiplyNaturals(int First, int Second) {
        if (First < 0 || Second < 0) {
            throw new IllegalArgumentException();
        }
        return First * Second;
    }

    public void openAuthor(View view) {
        Intent intent = new Intent(this, AuthorActivity.class);
        intent.putExtra("result", message);
        startActivity(intent);
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            product(view);
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Long> {

        @Override
        protected void onPostExecute(Long aLong) {
            message += aLong;
            textView.setText(message);
            editText1.setEnabled(true);
            editText2.setEnabled(true);
            progressBar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Long doInBackground(Integer... params) {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    publishProgress((i + 1) * 10);
                }
                return multiplyNaturals(params[0], params[1]);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return 0L;
            }

        }
    }
}
