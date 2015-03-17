package com.indalware.knigthrider;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Servicio que recibe la comunicacion entre el Dispositivo Wear y el SmartPhone.
 *
 * @author Victor Suarez
 * @version  1.0
 */
public class KnigthRiderService extends WearableListenerService {

    /**
     * COntrolador de la API
     */
    public APIController controller;
    /**
     * CLiente de Google Cloud Messages
     */
    public GoogleApiClient gapiClient;

    public KnigthRiderService() {
        this.controller= new APIController(this);


    }


    //Cuando se reciba el mensaje hacer accion
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
       String path=messageEvent.getPath();
        Toast.makeText(this,"Mensaje Recibido",Toast.LENGTH_SHORT).show();
        this.controller.ControlKIT(path);

    }


}
