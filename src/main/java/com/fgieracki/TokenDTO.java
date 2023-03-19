package com.fgieracki;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

@Data
@AllArgsConstructor
@ToString
public class TokenDTO {
    String name;
    String style;
    String value;

    public TokenDTO(String value) {
        this.value = value;
        this.style = "";
        this.name = "";
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String toHTML(){
        if(Pattern.compile(" ").matcher(value).matches())
            return "<span style=\"" + style + "\">&nbsp</span>";
        else if(!Pattern.compile("\\r|\\r\\n|\\n").matcher(value).matches())
            return "<span style=\"" + style + "\">" + escapeHtml4(value) + "</span>";
        else
            return "<br>";
    }
}
