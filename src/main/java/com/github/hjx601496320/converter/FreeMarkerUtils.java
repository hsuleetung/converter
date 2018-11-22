package com.github.hjx601496320.converter;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

public class FreeMarkerUtils {

    public static String format(Object subjectParams, FreeMarkerFtl ftl) throws Exception {
        StringTemplateLoader stl = new StringTemplateLoader();
        String name = ftl.getTemplateName();
        String templateText = ftl.getTemplateText();
        stl.putTemplate(name, templateText);
        //第一步：实例化Freemarker的配置类
        Configuration conf = new Configuration();
        conf.setObjectWrapper(new DefaultObjectWrapper());
        conf.setLocale(Locale.CHINA);
        conf.setDefaultEncoding("utf-8");
        conf.setTemplateLoader(stl);
        //处理空值为空字符串
        conf.setClassicCompatible(true);
        Template template = conf.getTemplate(name);
        Writer out = new StringWriter(templateText.length());
        template.process(subjectParams, out);
        String xml = out.toString();
        return xml;
    }
}
