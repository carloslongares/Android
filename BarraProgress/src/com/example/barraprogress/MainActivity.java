package com.example.barraprogress;

import android.app.Activity;
import android.os.AsyncTask;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    ProgressBar barra= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnCancelar = (Button)findViewById(R.id.btnCancelar);
        final Button btnCargar = (Button)findViewById(R.id.btnCargar);
        barra = (ProgressBar)findViewById(R.id.progressBar);
        final  HiloAsin hiloAsin = new HiloAsin();
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               hiloAsin.execute();
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            hiloAsin.cancel(true);
            }
        });
    }

    private void lanzaTarea () {
        try{
        Thread.sleep(1000);
        }catch (InterruptedException e){}
       }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private class HiloAsin extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i = 1 ; i<=10;i++){
                lanzaTarea();
                publishProgress(i*10);

                if(isCancelled())
                    break;
            }
            return true;
        }

        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            barra.setProgress(progreso);
        }

        protected void onPreExecute() {
           barra.setMax(100);
           barra.setProgress(0);
        }

        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}