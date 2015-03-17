package com.indalware.knigthrider;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.internal.co;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.HttpClient;

import java.io.IOException;

/**
 * Controla la llamada a la API de la YUN
 * @author  Victor Suarez
 * @version  1.0
 */
public class APIController {
    /**
     * URL por defecto
     */
    public String defaultURL="http://192.168.240.1/arduino/control/";


    private Context context;

    public APIController(Context context){
        this.context=context;
    }

    /**
     * Realiza una llamada por http al arduino YUN.
     * @param command
     */
    public void ControlKIT(String command){

        String url = defaultURL+command;

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        try {
            HttpResponse response=client.execute(get);
            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode()!=200)
                throw new Exception("Error al procesar comando");
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }


    }
}
