package cn.servant.crm.exception;

/**
 * 系统自定义异常类，联系人异常
 */
public class LinkmanException extends Exception {

    private String message;

    public LinkmanException() {

    }

    public LinkmanException(String message) {
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
