package cn.servant.crm.exception;

/**
 * 系统自定义异常类，用户异常
 */
public class CustomerException extends Exception {

    private String message;

    public CustomerException() {

    }

    public CustomerException(String message) {
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
