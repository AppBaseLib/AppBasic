

/**
 * @描述： @BaseResponse
 * @作者： @黄卫旗
 * @创建时间： @06/09/2018
 */
public class BaseResponse<T> {

    /**
     * 0：成功，1：失败
     */
    private int error_no;

    private String error_msg;

    private T data;

    public int getErrorNo() {
        return error_no;
    }

    public void setErrorNo(int errorCode) {
        this.error_no = errorCode;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public void setErrorMsg(String errorMsg) {
        this.error_msg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}


