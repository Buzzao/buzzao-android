package com.igormatos.buzzao;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.canelmas.let.AskPermission;
import com.canelmas.let.DeniedPermission;
import com.canelmas.let.Let;
import com.canelmas.let.RuntimePermissionListener;
import com.canelmas.let.RuntimePermissionRequest;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MonitoramentoActivity extends ActionBarActivity implements View.OnClickListener, RuntimePermissionListener {
	Context context = this;
	public static final String EXTRA_LINHA = "EXTRA_LINHA";
	public static final String EXTRA_VEICULO = "EXTRA_VEICULO";
	public static final String ACTION_MONITORAR = "MONITORAR";
	public static final String CATEGORY_MONITORAR = "PASSAGEM";
	Button fino;
	Button sendViaEmail;

	TextView speed;

	String extraLinha;
	String extraVeiculo;

	String numeroLinha;
	String nomeLinha;

	TextView velocidade;
	TextView veiculo;
	TextView linha;

	Location locationAtm;
	ParseObject linhaObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoramento);

		fino = (Button) findViewById(R.id.finociclista);
		fino.setOnClickListener(this);

		sendViaEmail = (Button) findViewById(R.id.send_via_email);
		sendViaEmail.setOnClickListener(this);

		linha = (TextView) findViewById(R.id.showLinha);
		veiculo = (TextView) findViewById(R.id.showVeiculo);
		speed = (TextView) findViewById(R.id.showSpeed);
		velocidade = (TextView) findViewById(R.id.showSpeed);

		Intent intent = getIntent();

		if (intent.hasExtra(EXTRA_LINHA)) {
			extraLinha = intent.getStringExtra(EXTRA_LINHA);
			extraVeiculo = intent.getStringExtra(EXTRA_VEICULO);

			linha.setText("Linha: " + extraLinha);
			veiculo.setText("Veiculo: " + extraVeiculo);

		} else {
			Toast.makeText(context, "Informe a linha e o veiculo.", Toast.LENGTH_SHORT).show();
			onBackPressed();
		}

// Procurar pelo objeto da linha atual, se não existir, salvar a linha.
		final String[] linha = extraLinha.split("-");
		numeroLinha = linha[0];
		numeroLinha = numeroLinha.substring(0, numeroLinha.length()-1);

		nomeLinha = linha[1];
		nomeLinha = nomeLinha.substring(1, nomeLinha.length()-1);

		ParseQuery<ParseObject> query = ParseQuery.getQuery("linha");
		query.whereEqualTo("numeroLinha", numeroLinha);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> list, ParseException e) {
				if (e == null) {
					if(list.size() > 0) {
						linhaObject = list.get(0);
						Log.d("BUZZAO-DEBUG", "Pegou a linha." + numeroLinha + " nome: " + nomeLinha);
					} else {
						linhaObject = new ParseObject("linha");
						linhaObject.put("numeroLinha", numeroLinha);
						linhaObject.put("nomeLinha", nomeLinha);
						linhaObject.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException e) {
								Log.d("BUZZAO-DEBUG", "Salvou a linha" + numeroLinha + " nome: " + nomeLinha);
							}
						});

					}

				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});


		setupLocationManager();

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

		switch(id) {
			case R.id.acao_config:

				return true;
			case R.id.acao_sobre:

				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@AskPermission({Manifest.permission.ACCESS_FINE_LOCATION,
		Manifest.permission.ACCESS_COARSE_LOCATION})
	protected void setupLocationManager() {
		// Acquire a reference to the system Location Manager
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {

		boolean isInfringing = false;

			public void onLocationChanged(Location location) {
				locationAtm = location;


//				TODO: pegar a velocidade máxima da location atual
				// boolean pra checar se já salvou a infracao atual
				//se sim e passou a velocidade caiu abaixo do limite, salva como fim.

				if ((location.getSpeed() * 3.6) > 60) { //Parse se a velocidade exceder
					if (!isInfringing) {
						isInfringing = true;
						ParseObject object = new ParseObject("Velocidade");
						object.put("velocidade", location.getSpeed() * 3.6);
						//					TODO: salvar o ponteiro para a linha
						object.put("linha", linhaObject);
						object.put("nVeiculo", extraVeiculo);

						ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
						object.put("ponto_inicio", point);

						object.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException e) {
								Log.d("BUZZAO-DEBUG", "SALVOU A OCORRENCIA DE VELOCIDADE");
							}
						});

						Log.i("BUZZAO-DEBUG", location.toString());
						Log.i("BUZZAO-DEBUG", point.toString());
					}
				} else {
					if (isInfringing == true) {
						ParseObject object = new ParseObject("Velocidade");
						object.put("velocidade", location.getSpeed() * 3.6);
						object.put("linha", linhaObject);
						object.put("nVeiculo", extraVeiculo);

						ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
						object.put("ponto_fim", point);

						object.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException e) {
								Log.d("BUZZAO-DEBUG", "SALVOU A ULTIMA OCORRENCIA DE VELOCIDADE");

							}
						});
					}

					isInfringing = false;

				}

				speed.setText(("" + (location.getSpeed() * 3.6)).substring(0, 2));

				//Toast.makeText(context, "Current speed:" + location.getSpeed()*3.6, Toast.LENGTH_SHORT).show();

			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}

		};

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
			case R.id.finociclista:
				saveFino();

				break;
			case R.id.send_via_email:
				sendViaEmail();
				break;

		}

		if (v.getId() == fino.getId()) { //se o bot�o clicado foi o do start


		}

	}
	protected void sendViaEmail() {
		// reverse geocode - pegar endereço
		// intent enviando email para o CTTU
		//

	}

	protected void saveFino() {
		ParseObject fino = new ParseObject("ocorrencia");
		fino.put("velocidade", velocidade.getText());
		ParseGeoPoint point = new ParseGeoPoint(locationAtm.getLatitude(),locationAtm.getLongitude());
		fino.put("location", point);
		fino.put("linha", linhaObject);
		fino.put("veiculo", extraVeiculo);
		fino.put("foiFino", true);
		fino.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				Toast.makeText(context, "Salvamos o fino! obrigado", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onShowPermissionRationale(List<String> permissionList, RuntimePermissionRequest permissionRequest) {

	}

	@Override
	public void onPermissionDenied(List<DeniedPermission> deniedPermissionList) {
		Toast.makeText(this, "Precisamos dessa permissão para pegarmos a velocidade.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		Let.handle(this, requestCode, permissions, grantResults);
	}
}



