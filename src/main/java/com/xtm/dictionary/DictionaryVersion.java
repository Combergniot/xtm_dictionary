package com.xtm.dictionary;

enum DictionaryVersion {

    POLISH_TO_ENGLISH("json/dictionary.json");

    private final String path;

    DictionaryVersion(String path) {
        this.path = path;
    }

    String getPath() {
        return path;
    }
}
