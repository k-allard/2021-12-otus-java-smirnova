package ru.otus.dataprocessor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeasurementMixin {

    @JsonCreator
    public MeasurementMixin(
            @JsonProperty("name") String name,
            @JsonProperty("value") double value
    ) {}
}
