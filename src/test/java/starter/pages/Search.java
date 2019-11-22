package starter.pages;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class Search extends UIInteractionSteps {

    static By SEARCH_RESULT = By.cssSelector("a[href*='serenity-core']");
    static By SEARCH_BUTTON = By.className("button");
    static By SEARCH_FIELD = By.id("query");

    @Step("Search for library {0}")
    public void library(String term) {
        $(SEARCH_FIELD).waitUntilVisible();
        $(SEARCH_FIELD).clear();
        $(SEARCH_FIELD).type(term);
        $(SEARCH_BUTTON).click();
    }

    public boolean libraryNameIsDisplayedInResult(String searchResult) {
        return $(SEARCH_RESULT).isDisabled();
    }
}
