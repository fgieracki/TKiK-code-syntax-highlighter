package com.fgieracki;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

@Getter
@NoArgsConstructor
public class Scanner {
    private String current_string = "";
    private TokenDB tokenDB = new TokenDB();
    private List<TokenDTO> result = new ArrayList<>();

    public Scanner(TokenDB tokenDB) {
        this.tokenDB = tokenDB;
    }


    public void nextChar(char character){
        if (isWhiteSpace(character)){
            if (!current_string.isEmpty()){
                addResultToken(makeEmptyTokenDTO(current_string));
                current_string = "";
            }
            addResultToken(getMatchingTokenDTO(String.valueOf(character)));
        }
        else {
            if(getMatchingTokenDTO(String.valueOf(character)) != null){
                addResultToken(makeEmptyTokenDTO(current_string));
                addResultToken(getMatchingTokenDTO(String.valueOf(character)));
                current_string = "";
            }
            else{
                current_string += character;
                if(getMatchingTokenDTO(current_string) != null){
                    addResultToken(getMatchingTokenDTO(current_string));
                    current_string = "";
                }
            }
        }
    }

    public void printTokens(){
        for (TokenDTO tokenDTO : result) {
            System.out.println(tokenDTO);
        }
    }

    private TokenDTO getMatchingTokenDTO(String value){
        for (Token token : tokenDB.getTokens()) {
            if (token.getPattern().matcher(value).matches()){
                return new TokenDTO(token.getName(), token.getStyle(), value);
            }
        }
        return null;
    }

    private TokenDTO makeEmptyTokenDTO(String value){
        return new TokenDTO(value);
    }
    private void addResultToken(TokenDTO tokenDTO){
        result.add(tokenDTO);
    }

    private boolean isWhiteSpace(char character){
        return Pattern.compile("\\s+").matcher(String.valueOf(character)).matches();
    }
}