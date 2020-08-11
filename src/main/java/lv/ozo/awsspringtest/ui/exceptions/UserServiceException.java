package lv.ozo.awsspringtest.ui.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 123123L;
    public UserServiceException(String message)
    {
        super(message);
    }
}
