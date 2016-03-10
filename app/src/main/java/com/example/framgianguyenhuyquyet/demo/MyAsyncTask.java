package com.example.framgianguyenhuyquyet.demo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 10/03/2016.
 */
public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity context;

    public MyAsyncTask(Activity ctx) {
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "onPreExecute!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 1; i <= 100; i++) {
            SystemClock.sleep(1);

            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        TextView txt = (TextView) context.findViewById(R.id.textView_1);
        ProgressBar bar = (ProgressBar) context.findViewById(R.id.progressBar_1);
        int i = values[0];
        bar.setProgress(i);
        txt.setText(String.valueOf(i + "%"));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context, "Update done!",
                Toast.LENGTH_LONG).show();
    }


}
