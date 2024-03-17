package com.kartha.cssp.model;

import com.kartha.cssp.data.TemperatureData;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Document(collection = "temperature")
public class Temperature {

    @Id
    private String id;

    private List<TemperatureData> temperatureList;

    public List<TemperatureData> getTemperatureDataList() {
        return temperatureList;
    }

    public void setTemperatureDataList(List<TemperatureData> temperatureDataList) {
        this.temperatureList = temperatureDataList;
    }


}
