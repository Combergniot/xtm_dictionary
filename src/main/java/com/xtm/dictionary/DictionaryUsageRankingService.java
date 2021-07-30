package com.xtm.dictionary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
class DictionaryUsageRankingService {

    private final TokenizerService tokenizerService;

    DictionaryUsageRankingService(TokenizerService tokenizerService) {
        this.tokenizerService = tokenizerService;
    }

    private Map<String, Integer> getWordCounterMap(String sentence, Map<String, String> dictionary) {
        List<String> tokensList = tokenizerService.createSimpleTokensList(sentence);
        Map<String, Integer> wordCounterMap = new HashMap<>();
        for (String token : tokensList) {
            if (dictionary.containsKey(token)) {
                Integer value = wordCounterMap.get(token);
                if (value == null) {
                    value = 0;
                }
                value++;
                wordCounterMap.put(token, value);
            } else {
                wordCounterMap.put(token, 0);
            }
        }
        return wordCounterMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    List<DictionaryUsage> getDictionaryUsageRanking(String sentence, Map<String, String> dictionary) {
        Map<String, Integer> wordCounterMap = getWordCounterMap(sentence, dictionary);
        List<DictionaryUsage> dictionaryUsages = new ArrayList<>();
        for (String key : wordCounterMap.keySet()) {
            var item = new DictionaryUsage(key, wordCounterMap.get(key));
            dictionaryUsages.add(item);
        }
        return dictionaryUsages;
    }

    protected record DictionaryUsage(String word, int usage) {

        @Override
        public String toString() {
            return "DictionaryUsage{" +
                    "word='" + word + '\'' +
                    ", usage=" + usage +
                    '}';
        }
    }
}
