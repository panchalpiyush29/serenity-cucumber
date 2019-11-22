package starter.navigation;

import net.thucydides.core.annotations.Step;

public class NavigateTo {

    DuckDuckGoHomePage duckDuckGoHomePage;
    MavenRepositoryPage mavenRepositoryPage;


    @Step("Open the DuckDuckGo home page")
    public void theDuckDuckGoHomePage() {
        duckDuckGoHomePage.open();
    }

    @Step("Open the Maven Repository Page")
    public void theMavenRepositoryPage() {
        mavenRepositoryPage.open();
    }
}