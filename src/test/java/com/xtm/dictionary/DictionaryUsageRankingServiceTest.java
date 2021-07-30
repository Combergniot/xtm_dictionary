package com.xtm.dictionary;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DictionaryUsageRankingServiceTest {

    @InjectMocks
    private DictionaryUsageRankingService dictionaryUsageRankingService;

    @Mock
    private TokenizerService tokenizerService;


    private final Map<String, String> dictionary = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp(){
        dictionary.put("Ala", "Alice");
        dictionary.put("ma", "has");
        dictionary.put("kota", "a cat");
    }

    @Test
    void shouldReturnsTheMostUsedWordsWithCounter(){
        // given
        String sentence = "Ojej Ala, Ala, Ala kota ma.";
        // when

        when(tokenizerService.createSimpleTokensList(sentence)).thenCallRealMethod();
        List<DictionaryUsageRankingService.DictionaryUsage> dictionaryUsages =
                dictionaryUsageRankingService.getDictionaryUsageRanking(sentence, dictionary);

        // then

        BDDSoftAssertions bddSoftAssertions = new BDDSoftAssertions();
        bddSoftAssertions.then(dictionaryUsages.get(0).usage()).isEqualTo(3);
        bddSoftAssertions.then(dictionaryUsages.get(0).word()).isEqualTo("Ala");
        bddSoftAssertions.then(dictionaryUsages.get(dictionaryUsages.size()-1).usage()).isEqualTo(0);
        bddSoftAssertions.then(dictionaryUsages.get(dictionaryUsages.size()-1).word()).isEqualTo("Ojej");
        bddSoftAssertions.assertAll();

    }
}