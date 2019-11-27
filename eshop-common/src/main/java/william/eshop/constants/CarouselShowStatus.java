package william.eshop.constants;

/**
 * @Author zhangshenao
 * @Date 2019-11-27
 * @Description 轮播图展示状态
 */
public enum CarouselShowStatus {
    HIDE(0, "隐藏"),
    SHOW(1, "展示");

    private int value;

    private String name;

    CarouselShowStatus(int value, String name) {
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
