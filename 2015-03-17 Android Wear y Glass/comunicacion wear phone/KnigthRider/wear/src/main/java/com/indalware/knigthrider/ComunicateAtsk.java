package com.indalware.knigthrider;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Envia un mensaje al Smartphone.
 * @author  victor suarez
 * @version  1.0
 *
 */
public class ComunicateAtsk extends AsyncTask<String,Void,Void> {

    private Context context;
    /**
     * CLiente para Google Cloud Messages Services
     */
    private GoogleApiClient apiClient;

    public ComunicateAtsk(Context context, GoogleApiClient apiClient){
        this.context=context;
        this.apiClient=apiClient;
    }

    @Override
    protected Void doInBackground(String... params) {
       String path = params[0];
        //Se obtienen los nodos conectados
        NodeApi.GetConnectedNodesResult rawNodes =
                Wearable.NodeApi.getConnectedNodes(apiClient).await();
        //NOTA: En este caso se usa el primero por que presuponemos que solo hay un dispositivo conectado.
        // En una aplicacion real, se tendría que obtener el nodo correcto.
        Wearable.MessageApi.sendMessage(apiClient,rawNodes.getNodes().get(0).getId(),path,null);
        return null;
    }
}
