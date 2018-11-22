package com.github.hjx601496320.converter;

import java.io.Serializable;

/**
 * freeMarker模板包装类
 */
public class FreeMarkerFtl implements Serializable {

    public FreeMarkerFtl() {
    }

    public FreeMarkerFtl(String templateText, String templateName) {
        this.templateText = templateText;
        this.templateName = templateName;
    }

    private String templateText;

    private String templateName;

    public String getTemplateText() {
        return templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"templateText\"=\"").append(templateText).append('\"');
        sb.append(",             \"templateName\"=\"").append(templateName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
