package com.xtm.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
class TranslationService {

    private final TokenizerService tokenizerService;

    TranslationService(TokenizerService tokenizerService) {
        this.tokenizerService = tokenizerService;
    }


    String getTranslation(String sentence, Map<String, String> dictionary) {
        return translation(sentence, dictionary)
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    String getTranslationWithQuotes(String sentence, Map<String, String> dictionary) {
        return translation(sentence, dictionary)
                .stream()
                .map(Object::toString)
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(" "));
    }

    private List<String> translation(String sentence, Map<String, String> dictionary) {
        List<String> tokensList = tokenizerService.createSimpleTokensList(sentence);
        List<String> translation = new ArrayList<>();
        for (String word : tokensList) {
            if (dictionary.get(word) != null) {
                word = dictionary.get(word);
            }
            translation.add(word);
        }
        return translation;
    }
}
