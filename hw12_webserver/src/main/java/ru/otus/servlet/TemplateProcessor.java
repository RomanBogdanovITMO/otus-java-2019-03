package ru.otus.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.logging.Logger;

public class TemplateProcessor {

    static Logger logger = Logger.getLogger(TemplateProcessor.class.getName());
    private static final TemplateProcessor instance = new TemplateProcessor();
    private final Configuration configuration;

    private TemplateProcessor() {
        configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/tml/");
    }

    static TemplateProcessor instance() {
        logger.info("execute method instance: ");

        return instance;
    }

    String getPage(final String page, final Map<String, Object> pageVariables) throws IOException {
        logger.info("execute method getPage: ");

        try (Writer writer = new StringWriter()) {
            final Template template = configuration.getTemplate(page);
            template.process(pageVariables, writer);
            return writer.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

    String getPageUser(final String page, final Object object) throws IOException {
        logger.info("execute method getPageUser: ");

        try (Writer writer = new StringWriter()) {
            final Template template = configuration.getTemplate(page);
            template.process(object, writer);
            return writer.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }
}
