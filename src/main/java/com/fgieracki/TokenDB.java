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
        addToken(new Token(Pattern.compile("(for|while|do)"), "LOOP", "color: blue"));
        addToken(new Token(Pattern.compile("(if|else)"), "IF", "color: orange"));
        addToken(new Token(Pattern.compile("(int|double|float|char|bool|string)"), "TYPE", "color: green"));
        addToken(new Token(Pattern.compile("\\r|\\r\\n|\\n"), "NEW_LINE", ""));
        addToken(new Token(Pattern.compile("/[^\\S\\r\\n]/"), "WHITE_SPACE", ""));
        addToken(new Token(Pattern.compile("[\\(\\)\\{\\}]"), "BRACKET", "color: red"));
        addToken(new Token(Pattern.compile("#include"), "#INCLUDE", "color: orange"));
        addToken(new Token(Pattern.compile(";"), "SEMICOLON", "color: DarkMagenta; font-weight: bold;"));
        addToken(new Token(Pattern.compile("//"), "COMMENT_LINE", "color: grey; font-style: italic;"));
        addToken(new Token(Pattern.compile("/\\*"), "COMMENT_BLOCK_START", "color: grey; font-style: italic;"));
        addToken(new Token(Pattern.compile("\\*/"), "COMMENT_BLOCK_END", "color: grey; font-style: italic;"));
        addToken(new Token(Pattern.compile("return"), "RETURN", "color: purple"));
        addToken(new Token(Pattern.compile("using"), "USING", "color: lime; font-style: bold;"));


    }
}
