package com.jcfy.xkt.module;

/**
 * @author linzheng
 */
public class RechargeContent {

    private String content;

    private boolean isOddNumber;

    /**
     * 是否是购买类型  初级 or 高级
     */
    private boolean isType;

    public boolean isType() {
        return isType;
    }

    public RechargeContent setType(boolean type) {
        isType = type;
        return this;
    }

    public boolean isOddNumber() {
        return isOddNumber;
    }

    public RechargeContent setOddNumber(boolean oddNumber) {
        isOddNumber = oddNumber;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RechargeContent setContent(String content) {
        this.content = content;
        return this;
    }
}
