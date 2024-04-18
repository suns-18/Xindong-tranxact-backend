package neu.xindong.xact.dto;

import org.springframework.http.HttpStatus;

public record HttpResponse<T>(
        int code, T data, String msg) {
    public static <T> HttpResponse<T> success(T data) {
        return new HttpResponse<T>(
                HttpStatus.OK.value(), data, "请求成功"
        );
    }

    public static <T> HttpResponse<T> success() {
        return success(null);
    }

    public static <T> HttpResponse<T> failure(
            int code, String msg) {
        return new HttpResponse<T>(
                code, null, msg
        );
    }

    public static <T> HttpResponse<T> failureWhenAccessDB() {
        return failure(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "操作失败，数据库访问错误");
    }

    public static <T> HttpResponse<T> unauthorized(String msg) {
        return failure(
                HttpStatus.FORBIDDEN.value(),
                msg);
    }
}