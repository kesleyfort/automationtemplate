package br.com.template.lifecycle;

import br.com.template.configuration.PageObjectBeanPostProcessor;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebDriverLifeCycleSteps {

    @Autowired
    PageObjectBeanPostProcessor pageObjectPostProcessor;

    @Autowired
    WebDriverProvider driverProvider;
        // Open browser:
    @BeforeStory
    public void beforeStory() {
        driverProvider.initialize();
        driverProvider.get().manage().window().maximize();

        // Initialize all Page Object classes:
        for (Object page : pageObjectPostProcessor.getPageObjects()) {
            PageFactory.initElements(driverProvider.get(), page);
        }
    }

    @AfterStory
    public void afterStory() throws Exception {
       driverProvider.end();
   }
}
