package com.igormatos.buzzao;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;


public class MonitoramentoActivity extends ActionBarActivity implements View.OnClickListener {
    Context context = this;
    public static final String EXTRA_LINHA = "EXTRA_LINHA";
    public static final String EXTRA_VEICULO = "EXTRA_VEICULO";
    public static final String ACTION_MONITORAR = "MONITORAR";
    public static final String CATEGORY_MONITORAR = "PASSAGEM";
    Button finoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoramento);

        finoButton = (Button) findViewById(R.id.finociclista);
        finoButton.setOnClickListener(this);

        TextView linha = (TextView) findViewById(R.id.showLinha);
        TextView veiculo = (TextView) findViewById(R.id.showVeiculo);
        final TextView speed = (TextView) findViewById(R.id.showSpeed);

        Intent intent = getIntent();

        final String extraLinha = intent.getStringExtra(EXTRA_LINHA);
        final String extraVeiculo = intent.getStringExtra(EXTRA_VEICULO);

        if(intent.hasExtra(EXTRA_LINHA)){
            linha.setText("Linha: " + extraLinha);
            veiculo.setText("Veiculo: " + extraVeiculo);

        }else{
            Toast.makeText(context, "Informe a linha e o veiculo", Toast.LENGTH_SHORT).show();
        }

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {


            public void onLocationChanged(Location location) {
                speed.setText("ola");

                if((location.getSpeed()*3.6)>60){ //Parse se a velocidade exceder
                    ParseObject testObject = new ParseObject("Velocidade");
                    testObject.put("Speed", location.getSpeed()*3.6);
                    testObject.put("geoLongitude", location.getLongitude());
                    testObject.put("geoLatitude", location.getLatitude());
                    ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(),location.getLongitude());
                    testObject.put("location", point);
                    testObject.put("Linha", extraLinha );
                    testObject.put("Veiculo", extraVeiculo);
                    testObject.saveInBackground();
                }

                speed.setText(("" + (location.getSpeed()*3.6)).substring(0,2));



                //Toast.makeText(context, "Current speed:" + location.getSpeed()*3.6, Toast.LENGTH_SHORT).show();

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {
            }
            public void onProviderDisabled(String provider) {
            }

        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action basubr will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acao_config) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






    @Override
    public void onClick(View v) {

        if(v.getId()==finoButton.getId()){ //se o bot�o clicado foi o do start
            TextView velocidade = (TextView) findViewById(R.id.showSpeed);
            TextView linha = (TextView) findViewById(R.id.showLinha);
            TextView veiculo = (TextView) findViewById(R.id.showVeiculo);

            Toast.makeText(context, "Salvamos o fino! obrigado", Toast.LENGTH_SHORT).show();

            ParseObject testObject = new ParseObject("ocorrencia");
            testObject.put("velocidade", velocidade.getText());
            //testObject.put("longitude", location.getLongitude());
            //testObject.put("latitude", location.getLatitude());
            //ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(),location.getLongitude());
            //testObject.put("location", point);
            testObject.put("linha", linha.getText().subSequence(7,10) );
            testObject.put("veiculo", veiculo.getText().subSequence(9,12));
            testObject.put("foiFino", true);
            testObject.saveInBackground();

        }

    }





}



