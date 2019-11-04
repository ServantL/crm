package cn.servant.crm.exception;

/**
 * 系统自定义异常类，未知异常
 */
public class UnknownException extends Exception {

    private String message;

    public UnknownException() {

    }

    public UnknownException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
