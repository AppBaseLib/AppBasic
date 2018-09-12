


/**
 * @author 黄卫旗
 * @description HttpResponse
 * @time 2018/09/07
 */
public class HttpResponse<T> {
	private int code;
	private String error;
	private T result;    //泛型T来表示object，可能是数组，也可能是对象

	public int getCode() {
		return code;
	}

	public boolean isSuccess() {
		if(code==0){
			return true;
		}else {
			return false;
		}
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "httpResponse{" +
				"code=" + code +
				", error='" + error + '\'' +
				", result=" + result +
				'}';
	}
}
