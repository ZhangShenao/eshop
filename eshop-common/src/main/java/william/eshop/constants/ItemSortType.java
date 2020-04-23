package william.eshop.constants;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2020-04-23
 * @Description 商品排序方式
 */
public enum ItemSortType {
    CREATE_TIME_DESC(1, "创建时间降序,默认"),
    SALES_COUNT_DESC(2, "销量降序"),
    PRICE_ASC(3, "价格升序");

    private int value;

    private String name;

    ItemSortType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ItemSortType getByValue(int value) {
        return Arrays.stream(values())
                .filter(t -> t.value == value)
                .findFirst()
                .orElse(CREATE_TIME_DESC);
    }
}
