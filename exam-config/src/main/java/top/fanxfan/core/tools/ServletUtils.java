package top.fanxfan.core.tools;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author fanxfan
 */
public class ServletUtils {

    private ServletUtils() {
    }

    /**
     * 获取request
     *
     * @return 返回request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getRequest();
    }

    /**
     * 获取response
     *
     * @return 返回response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getResponse();
    }
}
