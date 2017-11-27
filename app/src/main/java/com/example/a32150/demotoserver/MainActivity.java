package com.example.a32150.demotoserver;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name, phone, addr, age, move, cake, sport, drink;
    RadioButton sex;
    Button submit;
    private static final String HTTP_URL="http://192.168.58.3/default.aspx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    void findViews() {
        name=(EditText)findViewById(R.id.ed_name);
        phone=(EditText)findViewById(R.id.ed_phone);
        age=(EditText)findViewById(R.id.ed_age);
        addr=(EditText)findViewById(R.id.ed_addr);
        move=(EditText)findViewById(R.id.ed_move);
        cake=(EditText)findViewById(R.id.ed_cake);
        sport=(EditText)findViewById(R.id.ed_sport);
        drink=(EditText)findViewById(R.id.ed_drink);
        submit=(Button)findViewById(R.id.btn_submit);
        sex=(RadioButton)findViewById(R.id.rb_male);
    }

    public void onSubmit(View v) {
        String jsonstr= createJsonData();
        System.out.println(jsonstr);
        new MyAsyncTask().execute(jsonstr);

    }

   public String createJsonData() {
        String _name= name.getText().toString();
        Person person;
        Data data;
        if(!_name.equals("")) {
            String address = addr.getText().toString();
            int _age = Integer.parseInt(age.getText().toString());
            String _phone = phone.getText().toString();
            boolean isMale = sex.isChecked();
            ArrayList<String> favorite = new ArrayList<>();
            favorite.add(move.getText().toString());
            favorite.add(cake.getText().toString());
            favorite.add(sport.getText().toString());
            favorite.add(drink.getText().toString());
            data = new Data(address, _phone);
            person = new Person(_name, _age, isMale, data, favorite);
        }   else    {

            person = new Person();
        }
        return new Gson().toJson(person);
    }

    String uploadData(String jsonString) {
        HttpURLConnection conn = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            URL url = new URL(HTTP_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("Submit", "Submit")
                    .appendQueryParameter("JSON", jsonString);
            String query = builder.build().getEncodedQuery();
            out = new BufferedOutputStream(conn.getOutputStream());
            out.write(query.getBytes());
            out.flush();

            in = new BufferedInputStream(conn.getInputStream());
            byte[] buf = new byte[1024];
            in.read(buf);
            String result = new String(buf);

            System.out.println("xxxxxx = "+result.toString());

            return result.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "Send Failed";
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (conn != null)
                conn.disconnect();
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String >   {

        @Override
        protected String doInBackground(String... strings) {
            return uploadData(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("result = "+s);
        }
    }

}
