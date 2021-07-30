package com.xtm.dictionary;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
class TokenizerService {

    private final static String SPECIAL_CHARACTERS = "[$&+,:;=?@#|'<>.^*()%!-]";
    private final static String WHITESPACE_CHARACTER = "\\s+";

    List<String> createSimpleTokensList(String sentence) {
        return Arrays.asList(sentence
                .replaceAll(SPECIAL_CHARACTERS, "")
                .trim()
                .split(WHITESPACE_CHARACTER));
    }
}
