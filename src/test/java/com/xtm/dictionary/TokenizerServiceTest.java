package com.xtm.dictionary;

import java.util.List;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenizerServiceTest {

    @InjectMocks
    private TokenizerService tokenizerService;

    @Test
    void shouldGetTokensFromSentenceWithSpecialCharacters(){
        // given
        String sentence = "(Ala) ma kot@. Kot ma AIDS?!";
        // when
        List<String> tokens = tokenizerService.createSimpleTokensList(sentence);

        // then
        BDDSoftAssertions bddSoftAssertions = new BDDSoftAssertions();
        bddSoftAssertions.then(tokens.size()).isEqualTo(6);
        bddSoftAssertions.then(tokens.contains("AIDS")).isEqualTo(true);
        bddSoftAssertions.then(tokens.contains("kot@.")).isEqualTo(false);
        bddSoftAssertions.assertAll();
    }

    @Test
    void shouldGetTokensFromSentenceWithLongWhiteSpaces(){
        // given
        String sentence = "  Ala    ma     kota";

        // when
        List<String> tokens = tokenizerService.createSimpleTokensList(sentence);

        // then
        BDDSoftAssertions bddSoftAssertions = new BDDSoftAssertions();
        bddSoftAssertions.then(tokens.size()).isEqualTo(3);
        bddSoftAssertions.then(tokens.contains("Ala")).isEqualTo(true);
        bddSoftAssertions.assertAll();
    }
}