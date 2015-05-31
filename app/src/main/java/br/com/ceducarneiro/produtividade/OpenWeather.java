package br.com.ceducarneiro.produtividade;

import android.os.AsyncTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeather {

    private Clima main;

    public Clima getMain() {

        return main;
    }

    public void setMain(Clima main) {
        this.main = main;
    }
}
