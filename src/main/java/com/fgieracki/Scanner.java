package com.fgieracki;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings({"java:S106", "java:S108"})
@Getter
@NoArgsConstructor
public class Scanner {
    private String current_string = "";
    private TokenDB tokenDB = new TokenDB();
    private List<TokenDTO> result = new ArrayList<>();
    private boolean isCommentLine = false;
    private boolean isCommentBlock = false;

    private String commentStyle = "";

    public Scanner(TokenDB tokenDB) {
        this.tokenDB = tokenDB;
            var tmpToken =  tokenDB.getTokens().stream().filter(
                    x -> x.getName().startsWith("COMMENT")
            ).findFirst();
            commentStyle = tmpToken.isPresent()?
                    tmpToken.get().getStyle()
                    : "color: grey; font-style: italic;";
    }


    public void nextChar(char character){
        if (isSeparator(character)){
            if (!current_string.isEmpty()){
                TokenDTO tempToken = getMatchingTokenDTO(current_string);
                manageComments(tempToken);
                addResultToken(changeStyleIfComment(tempToken));
                current_string = "";
            }
            TokenDTO whitespaceToken = getMatchingTokenDTO(String.valueOf(character));
            manageComments(whitespaceToken);
            addResultToken(whitespaceToken);
        }
        else {
            current_string += character;
        }
    }

    public void printTokens(){
        for (TokenDTO tokenDTO : result) {
            System.out.println(tokenDTO);
        }
    }

    private TokenDTO changeStyleIfComment(TokenDTO tokenDTO){
        if(isCommentLine || isCommentBlock)
            tokenDTO.setStyle(commentStyle);
        return tokenDTO;
    }

    private TokenDTO getMatchingTokenDTO(String value){
        for (Token token : tokenDB.getTokens()) {
            if (token.getPattern().matcher(value).matches()){
                return new TokenDTO(token.getName(), token.getStyle(), value);
            }
        }
        return makeEmptyTokenDTO(value);
    }

    private void manageComments(TokenDTO tokenDTO){
        switch (tokenDTO.getName()) {
            case "COMMENT_LINE" -> {
                if (!isCommentBlock) isCommentLine = true;
            }
            case "COMMENT_BLOCK_START" -> {
                if (!isCommentLine) isCommentBlock = true;
            }
            case "COMMENT_BLOCK_END" -> isCommentBlock = false;
            case "NEW_LINE" -> isCommentLine = false;
            default -> {}
        }
    }

    private TokenDTO makeEmptyTokenDTO(String value){
        return new TokenDTO(value);
    }
    private void addResultToken(TokenDTO tokenDTO){
        result.add(tokenDTO);
    }

    private boolean isSeparator(char character){
        return Pattern.compile("([\\s(){};])").matcher(String.valueOf(character)).matches();
    }
}
