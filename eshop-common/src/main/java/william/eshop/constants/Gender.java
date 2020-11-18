package william.eshop.constants;

/**
 * @Author zhangshenao
 * @Date 2019-11-21
 * @Description 性别
 */
public enum Gender {
    MALE(1, "男"),
    FEMALE(2, "女"),
    SECRETE(0, "保密");

    private int value;
    private String name;

    Gender(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
