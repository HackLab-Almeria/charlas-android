package com.indalware.knigthrider;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Esta actividad permite por medio de unos botones mandar información al SmartPhone
 */
public class controlKIT extends Activity implements GoogleApiClient.ConnectionCallbacks, MessageApi.MessageListener{

    private TextView mTextView;
    /**
     * Cliente de Google Cloud Messages Services
     */
    private GoogleApiClient apiclient;
    private buttonListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_kit);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        this.listener= new buttonListener();
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                Button btnluces = (Button)stub.findViewById(R.id.btnluces);
                btnluces.setOnClickListener(controlKIT.this.listener);
            }
        });
    this.createClient();
    }

    //Conectar y Mandar mensaje a Reloj
    public void createClient(){
        //Inicializacion del cliente para usar la API para Wearables.
        this.apiclient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build();
        this.apiclient.connect();//Conexion
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("WEAR KIT", "CONECTADO");//SI se ha conectado, se muestra un mensaje
        Toast.makeText(this,"Conectado a Movil",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

    }

    private class buttonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id = v.getId();
            ComunicateAtsk atsk = new ComunicateAtsk(controlKIT.this,apiclient);
            switch (id){
                case R.id.btnluces:{
                    String path = "5";
                    atsk.execute(path);//AL pulsar el boton se envía un mensaje al SmartPhone.
                    Toast.makeText(controlKIT.this, "Encendiendo Luces...", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
}
