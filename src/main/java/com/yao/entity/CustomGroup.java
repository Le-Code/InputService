package com.yao.entity;

public class CustomGroup {
    private String chinese;
    private String pinyin;
    private String translate;
    private boolean isConfirm;

    public CustomGroup() {
    }

    public CustomGroup(String chinese, String pinyin, String translate,boolean isConfirm) {
        this.chinese = chinese;
        this.pinyin = pinyin;
        this.translate = translate;
        this.isConfirm = isConfirm;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomGroup){
            CustomGroup group = (CustomGroup) obj;
            return this.chinese.equals(group.getChinese());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return -1;
    }
}
