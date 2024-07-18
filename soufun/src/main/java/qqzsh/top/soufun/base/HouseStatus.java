package qqzsh.top.soufun.base;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 21:46
 * @Description 房源状态
 */
public enum HouseStatus {
    NOT_AUDITED(0), // 未审核
    PASSES(1), // 审核通过
    RENTED(2), // 已出租
    DELETED(3); // 逻辑删除
    private int value;

    HouseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
