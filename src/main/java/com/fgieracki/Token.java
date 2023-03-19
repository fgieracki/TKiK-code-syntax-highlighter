package com.fgieracki;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class Token {
    Pattern pattern;
    String name;
    String style;
}
