package com.riffrain.dhlottery.module.dhlotto.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DhLottoDataDTO {
    private String genType;
    private String arrGameChoiceNum;
    private String alpabet;

    public DhLottoDataDTO(String genType, String arrGameChoiceNum, String alpabet) {
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
