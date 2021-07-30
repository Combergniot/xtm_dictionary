package com.xtm.dictionary;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DictionaryController {

    private final DictionaryUsageRankingService dictionaryUsageRankingService;
    private final TranslationService translationService;
    private final DictionaryProviderService dictionaryProviderService;

    DictionaryController(DictionaryUsageRankingService dictionaryUsageRankingService,
                         TranslationService translationService,
                         DictionaryProviderService dictionaryProviderService) {
        this.dictionaryUsageRankingService = dictionaryUsageRankingService;
        this.translationService = translationService;
        this.dictionaryProviderService = dictionaryProviderService;
    }

    @GetMapping(value = "/translate")
    String translate(@RequestBody String sentence) {
        return translationService
                .getTranslation(sentence, dictionaryProviderService.getDictionaryFromFile());
    }

    @GetMapping(value = "/translateWithQuotes")
    String translateWithQuotes(@RequestBody String sentence) {
        return translationService
                .getTranslationWithQuotes(sentence, dictionaryProviderService.getDictionaryFromFile());
    }

    @GetMapping(value = "/estimate")
    List<DictionaryUsageRankingService.DictionaryUsage> ranking(@RequestBody String sentence) {
        return dictionaryUsageRankingService
                .getDictionaryUsageRanking(sentence, dictionaryProviderService.getDictionaryFromFile());
    }
}
