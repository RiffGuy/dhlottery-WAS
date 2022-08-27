package com.riffrain.dhlottery.module.lotto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LottoDataDTO {
    private String genType;
    private String arrGameChoiceNum;
    private String alpabet;

    public LottoDataDTO(String genType, String arrGameChoiceNum, String alpabet) {
        this.genType = genType;
        this.arrGameChoiceNum = arrGameChoiceNum;
        this.alpabet = alpabet;
    }

    @Override
    public String toString() {
        return "{" +
                "\"genType\":\"" + genType + '\"' +
                ",\"arrGameChoiceNum\":\"" + arrGameChoiceNum + '\"' +
                ",\"alpabet\":\"" + alpabet + '\"' +
                '}';
    }
}
