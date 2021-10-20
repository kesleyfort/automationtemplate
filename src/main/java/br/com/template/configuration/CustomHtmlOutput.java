package br.com.template.configuration;

import java.io.PrintStream;
import java.util.Properties;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.WebDriverHtmlOutput;

public class CustomHtmlOutput extends WebDriverHtmlOutput {
    public static final WebDriverHtmlFormat WEB_DRIVER_HTML = new CustomHtmlOutput.WebDriverHtmlFormat();

    public CustomHtmlOutput(PrintStream output) {
        super(output);
        this.changeALine();
    }

    public CustomHtmlOutput(PrintStream output, Properties outputPatterns) {
        super(output, outputPatterns);
        this.changeALine();
    }

    public CustomHtmlOutput(PrintStream output, Keywords keywords) {
        super(output, keywords);
        this.changeALine();
    }

    public CustomHtmlOutput(PrintStream output, Properties outputPatterns, Keywords keywords) {
        super(output, outputPatterns, keywords);
        this.changeALine();
    }

    public CustomHtmlOutput(PrintStream output, Properties outputPatterns, Keywords keywords, boolean reportFailureTrace) {
        super(output, outputPatterns, keywords, reportFailureTrace);
        this.changeALine();
    }
    private void changeALine() {
        super.overwritePattern("failed", "<div class=\"step failed\">{0} <span class=\"keyword failed\">({1})</span><br/><span class=\"message failed\">{2}</span><br/><a color=\"black\" target=\"jb_scn_shot\" href=\"screenshots/failed-scenario-{3}.png\"><img src=\"images/failing_screenshot.png\" alt=\"failing screenshot\"/></a></div>\n");
    }

    private static class WebDriverHtmlFormat extends org.jbehave.core.reporters.Format
    {
        public WebDriverHtmlFormat() {
            super("HTML");
        }

        public StoryReporter createStoryReporter(FilePrintStreamFactory factory, StoryReporterBuilder storyReporterBuilder) {
            factory.useConfiguration(storyReporterBuilder.fileConfiguration("html"));
            return (new CustomHtmlOutput(factory.createPrintStream(), storyReporterBuilder.keywords())).doReportFailureTrace(storyReporterBuilder.reportFailureTrace()).doCompressFailureTrace(storyReporterBuilder.compressFailureTrace());
        }
    }
}

