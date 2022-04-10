package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.model.Measurement;
import java.io.IOException;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private final ObjectMapper mapper = new ObjectMapper();

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        mapper.addMixIn(Measurement.class, MeasurementMixin.class);
        try {
            return mapper.readValue(
                    ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName),
                    new TypeReference<>() {}
            );
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
