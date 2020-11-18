package william.eshop.constants;

/**
 * @Author zhangshenao
 * @Date 2020-04-23
 * @Description 商品评价等级
 */
public enum CommentLevel {
    UNKNOWN(0, "未知"),
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    private int value;

    private String name;

    CommentLevel(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
