package br.com.template;

import br.com.template.configuration.AbstractStoryConfiguration;
import br.com.template.configuration.ProjectConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AllStories extends AbstractStoryConfiguration {

    @Override
    public ApplicationContext getAnnotatedApplicationContext() {
        return new AnnotationConfigApplicationContext(ProjectConfiguration.class);
    }

    @Override
    public List<String> storyPaths() {
//        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/stories/*.story", "");
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/stories/*validation*.story", "");
    }
}