package starter.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.navigation.NavigateTo;
import starter.pages.Search;

import static org.assertj.core.api.Assertions.assertThat;

public class MavenRepositorySearchSteps {

    @Steps
    NavigateTo navigateTo;

    @Steps
    Search search;


    @Given("^(?:.*) is on the maven repository page$")
    public void userIsOnTheMavenRepositoryPage() {
        navigateTo.theMavenRepositoryPage();
    }

    @When("^User enters to search for \"([^\"]*)\"$")
    public void userEntersToSearchFor(String library) {
        search.library(library);
    }

    @Then("^Search result displays \"([^\"]*)\" libraries$")
    public void searchResultDisplaysLibraries(String searchResult) {
        assertThat(search.libraryNameIsDisplayedInResult(searchResult));
    }
}
