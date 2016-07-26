package cn.louguanyang.imageupload;

public enum ImgCategoryEnum {
    RentContract(4, "租房合同照片"),
    SelfCredentialNo1(8, "本人身份证照片正面"),
    SelfCredentialNo2(9, "本人身份证照片反面"),
    PersonalPhoto(64, "个人头像"),
    RentHouseSupporting(300, "租房配套");

    private final int code;
    private final String desc;

    private ImgCategoryEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
