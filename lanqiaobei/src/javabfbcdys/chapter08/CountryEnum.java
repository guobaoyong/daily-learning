package javabfbcdys.chapter08;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-14 9:30
 * @description 枚举
 */
public enum CountryEnum {


    ONE(0,"齐"),
    TWO(1,"楚"),
    THREE(2,"燕"),
    FOUR(3,"赵"),
    FIVE(4,"韩"),
    SIX(5,"魏");

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum countryEnum : values){
            if (index == countryEnum.getRetCode()){
                return countryEnum;
            }
        }
        return null;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }
}
