package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import util.Utils;

public class HttpClientClima {

    public String getWeatherData(String lugar) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        StringBuffer stringBuffer = new StringBuffer(); // hacemos espacio de memoria para StringBuffer

        try {
            connection = (HttpURLConnection) (new URL(Utils.INSTANCE.getBASE_URL() + lugar)).openConnection(); // aqui traemos el url y le pasamos la ciudad
            connection.setRequestMethod("GET"); // el metodo sera GET
            connection.setDoInput(true); // hacemos la coneccion
            connection.connect();

            // leemos todo el String que nos llega de la connexion

            inputStream = connection.getInputStream();  // lo pasamos en un imput Stream,
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //todo lo que guarda el input stream lo vamos guardando en el buffered reader
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }


            inputStream.close();  // cerramos el input
            connection.disconnect(); // nos desconectamos

        } catch (Exception e) {
            stringBuffer = new StringBuffer();
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

}
