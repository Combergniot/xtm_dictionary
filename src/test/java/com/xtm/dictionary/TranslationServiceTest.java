package com.xtm.dictionary;

import static org.assertj.core.api.Assertions.registerCustomDateFormat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TranslationServiceTest {

    @InjectMocks
    private TranslationService translationService;
    @Mock
    private TokenizerService tokenizerService;

    private final Map<String, String> dictionary = new ConcurrentHashMap<>();
    private final String sentence = "Ala ma kota.";

    @BeforeEach
    void setUp(){
        dictionary.put("Ala", "Alice");
        dictionary.put("ma", "has");
        dictionary.put("kota", "a cat");
    }

    @Test
    void shouldGetTranslationBasedOnDictionary(){
        // given
        // when
        when(tokenizerService.createSimpleTokensList(sentence)).thenCallRealMethod();
        String translation = translationService.getTranslation(sentence, dictionary);

        // then
        then(translation).isEqualTo("Alice has a cat");
    }

    @Test
    void shouldGetTranslationWithQuotesBasedOnDictionary(){
        // given
        // when
        when(tokenizerService.createSimpleTokensList(sentence)).thenCallRealMethod();
        String translation = translationService.getTranslationWithQuotes(sentence, dictionary);

        // then
        then(translation).isEqualTo("\"Alice\" \"has\" \"a cat\"");
    }
}