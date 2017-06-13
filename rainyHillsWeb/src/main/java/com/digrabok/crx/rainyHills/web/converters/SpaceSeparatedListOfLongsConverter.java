package com.digrabok.crx.rainyHills.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesConverter("spaceSeparatedListOfLongsConverter")
public class SpaceSeparatedListOfLongsConverter implements Converter {
    private Pattern pattern = Pattern.compile("\\d+");

    @Override
    public List<Long> getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Matcher matcher = pattern.matcher(s);

        List<Long> list = new LinkedList<>();
        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group()));
        }

        return list;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        List list = (List) o;
        StringBuilder builder = new StringBuilder(list.size());
        for(Object item : list) {
            if (builder.length() != 0) {
                builder.append(" ");
            }
            builder.append(item);
        }
        return builder.toString();
    }
}
