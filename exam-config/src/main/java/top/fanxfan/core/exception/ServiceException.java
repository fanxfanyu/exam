package top.fanxfan.core.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 业务逻辑异常 Exception
 *
 * @author fanxfan
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {

    /**
     * 业务错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
        this.code = 500;
        this.message = "业务异常";
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message) {
        this.code = 500;
        this.message = message;
    }

}
