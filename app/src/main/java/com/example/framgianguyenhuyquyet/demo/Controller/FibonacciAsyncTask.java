package com.example.framgianguyenhuyquyet.demo.Controller;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgianguyenhuyquyet.demo.R;

import java.util.ArrayList;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 10/03/2016.
 */
public class FibonacciAsyncTask extends AsyncTask<Integer, Integer, ArrayList<Integer>> {
    Activity context;
    ListView lv;
    Button btn;
    TextView txt_tong, txt_tientrinh;
    ArrayList<Integer> arrListview = new ArrayList<Integer>();
    ArrayAdapter<Integer> adapterListview = null;

    public FibonacciAsyncTask(Activity ctx) {
        context = ctx;
        lv = (ListView) context.findViewById(R.id.lv_item);
        txt_tientrinh = (TextView) context.findViewById(R.id.textView_2);
        btn = (Button) context.findViewById(R.id.btn_nhap);
        adapterListview = new ArrayAdapter<Integer>(context, android.R.layout.simple_list_item_1, arrListview);
        lv.setAdapter(adapterListview);
    }

    public int fib(int n) {
        if (n <= 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "bat dau", Toast.LENGTH_LONG).show();
    }

    @Override
    protected ArrayList<Integer> doInBackground(Integer... params) {
        int check = params[0];
        ArrayList<Integer> arrFib = new ArrayList<Integer>();
        for (int i = 0; i < check; i++) {
            SystemClock.sleep(100);
            int f = fib(i);
            publishProgress(f, i + 1, check);
            arrFib.add(f);
        }
        return arrFib;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        arrListview.add(values[0]);
        adapterListview.notifyDataSetChanged();
        txt_tientrinh.setText(String.valueOf(values[1] + "/" + values[2]));
        btn.setEnabled(false);

    }

    @Override
    protected void onPostExecute(ArrayList<Integer> integers) {
        super.onPostExecute(integers);
        int tong = 0;
        for (int i = 0; i < integers.size(); i++) {
            tong += integers.get(i);
        }
        txt_tong = (TextView) context.findViewById(R.id.textView_Sum);
        txt_tong.setText(String.valueOf(tong));
        Toast.makeText(context, String.valueOf(tong), Toast.LENGTH_LONG).show();
    }
}
