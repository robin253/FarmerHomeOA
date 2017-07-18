package com.huike.base.tools.excel;

/**
 * @Author Kent.Wang
 * @Date 2017/6/26
 */
public class ExportMemberVo {
    private String name;

    private Integer gender;

    private String idCard;

    private String bankNo;

    private String bankName;

    private String phone;


    public ExportMemberVo(String name, Integer gender, String idCard, String bankNo, String bankName, String phone) {
        this.name = name;
        this.gender = gender;
        this.idCard = idCard;
        this.bankNo = bankNo;
        this.bankName = bankName;
        this.phone = phone;
    }

    public ExportMemberVo() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getGender() {
        return gender == 0 ? "男" : "女";
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
