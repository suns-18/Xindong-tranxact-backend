package neu.xindong.xact.dto;

public record HttpResponse<T>(
        int code, T data, String msg) {
    public static <T> HttpResponse<T> success(T data) {
        return new HttpResponse<T>(
                200, data, "请求成功"
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
        return new HttpResponse<T>(
                0, null, "操作失败，数据库访问错误");
    }
}