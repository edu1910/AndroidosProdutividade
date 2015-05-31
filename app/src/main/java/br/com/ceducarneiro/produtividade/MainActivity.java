package br.com.ceducarneiro.produtividade;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;

import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

public class MainActivity extends Activity {

    @InjectView(R.id.img1) protected ImageView img1;
    @InjectView(R.id.img2) protected ImageView img2;
    @InjectView(R.id.img3) protected ImageView img3;
    @InjectView(R.id.img4) protected ImageView img4;
    @InjectView(R.id.img5) protected ImageView img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

        loadImage(img1, "http://updload.wikimedia.org/wikipedia/commons/e/ee/Android_green_figure,_next_to_its_original_packaging.jpg");
        loadImage(img2, "http://fc06.deviantart.net/fs70/f/2011/173/7/e/3d_android_3_by_almahy-d3jn3qu.jpg");
        loadImage(img3, "http://itechthereforeiam.com/wp-content/uploads/2013/11/android-gone-packing.jpg");
        loadImage(img4, "http://minutoligado.com.br/wp-content/uploads/2012/07/jelly-bean-android.jpg");
        loadImage(img5, "http://www.a12.com/files/media/originals/android1.jpg");
    }

    private void loadImage(ImageView img, String url) {
        Picasso.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .resize(toPixels(100), toPixels(100))
                .into(img);
    }

    private int toPixels(int dps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, getResources().getDisplayMetrics());
    }

    @OnClick(R.id.img1)
    public void clicouNaImagem1() {
        new JsonAsyncTask().execute();
    }

    @OnClick(R.id.img2)
    public void clicouNaImagem2() {
        List<Clima> climas = Clima.listAll(Clima.class);

        if (!climas.isEmpty()) {
            for (Clima clima : climas) {
                Toast.makeText(MainActivity.this, String.format("[%d]: %f", clima.getId(), clima.getPressure()), Toast.LENGTH_LONG).show();
            }
        }
    }

    class JsonAsyncTask extends AsyncTask<Void, Void, OpenWeather> {

        @Override
        protected OpenWeather doInBackground(Void... params) {
            ObjectMapper mapper = new ObjectMapper();

            OpenWeather ow = null;

            try {
                ow = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=Jo%C3%A3o%20Pessoa,BR&unit=metric"), OpenWeather.class);
            } catch (IOException e) {
                Log.e("CRASH!", "OpenWeather", e);
            }

            return ow;
        }

        @Override
        protected void onPostExecute(OpenWeather ow) {
            if (ow != null) {
                ow.getMain().save();
                Toast.makeText(MainActivity.this, "Salvou no banco!", Toast.LENGTH_LONG).show();
                //Toast.makeText(MainActivity.this, "Temp: " + ow.getMain().getTemp(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Erro ao buscar o JSON!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
