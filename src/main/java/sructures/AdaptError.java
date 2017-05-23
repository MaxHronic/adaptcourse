package sructures;

/**
 * Created by JR on 21.04.2016.
 */
public class AdaptError extends Exception {
    private int errorCode;
    private String errDescription;
    private boolean m_throwMe;

    public AdaptError() {
        errDescription = "Success";
        m_throwMe = true;
    }

    public AdaptError(int code, String description) {
        errDescription = "Success";
        m_throwMe = true;
        errorCode = code;
        errDescription = description;
    }

    public AdaptError(int code, String description, boolean throwMe) {
        this(code, description);
        m_throwMe = throwMe;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    @Override
    public String getMessage() {
        return errDescription;
    }

    public void setErrDescription(String errDescription) {
        this.errDescription = errDescription;
    }


    public boolean isM_throwMe() {
        return m_throwMe;
    }

    public void setM_throwMe(boolean m_throwMe) {
        this.m_throwMe = m_throwMe;
    }
}
