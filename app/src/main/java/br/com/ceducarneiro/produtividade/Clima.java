package br.com.ceducarneiro.produtividade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clima extends SugarRecord<Clima> {

    private float temp;

    @JsonProperty(value = "temp_min")
    private float tempMin;

    @JsonProperty(value = "temp_max")
    private float tempMax;

    private float pressure;

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }
}
