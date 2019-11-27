package william.eshop.constants;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 商品分类级别
 */
public enum CategoryLevel {
    LEVEL_1(1, "一级分类"),
    LEVEL_2(2, "二级分类"),
    LEVEL_3(3, "三级分类"),;

    private int level;
    private String name;

    CategoryLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
