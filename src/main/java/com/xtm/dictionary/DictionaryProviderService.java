package com.xtm.dictionary;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
class DictionaryProviderService {

    @SneakyThrows
    protected Map<String, String> getDictionaryFromFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(DictionaryVersion.POLISH_TO_ENGLISH.getPath())).getFile());
        String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return convertJsonToMap(data);
    }

    private Map<String, String> convertJsonToMap(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson((jsonString), new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}
