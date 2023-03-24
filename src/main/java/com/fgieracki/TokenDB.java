package com.fgieracki;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Getter
@Setter
public class TokenDB {
    private final Set<Token> tokenSet = new HashSet<>();

    public void addToken(Token token) {
        tokenSet.add(token);
    }

    public Set<Token> getTokens() {
        return tokenSet;
    }

    public TokenDB(){
        //loop keywords
        addToken(new Token(Pattern.compile("(for|while|do)"), "LOOP", "color: blue"));
        //if keyword
        addToken(new Token(Pattern.compile("(if|else)"), "IF", "color: orange"));
        //type keywords
        addToken(new Token(Pattern.compile("(int|double|float|char|boolean|string)"), "TYPE", "color: green"));
        //white space
        addToken(new Token(Pattern.compile("\\s+"), "WHITE_SPACE", ""));
        //brackets
        addToken(new Token(Pattern.compile("[\\(\\)\\{\\}]"), "BRACKET", "color: red"));
        //#include keyword
        addToken(new Token(Pattern.compile("#include"), "#INCLUDE", "color: orange"));
        //semicolon
        addToken(new Token(Pattern.compile(";"), "SEMICOLON", "color: NavyBlue; font-weight: bold;"));
        //single line comment
        addToken(new Token(Pattern.compile("//"), "COMMENT_LINE", "color: grey; font-style: italic;"));

    }
}
