package william.eshop.exception;

/**
 * @Author zhangshenao
 * @Date 2019-12-10
 * @Description 订单相关异常
 */
public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
