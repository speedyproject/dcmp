package org.nicosoft.config.support.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * ResponseBody model
 *
 * @author nico
 * @since 2017.8.27
 */
public class ResultBody implements Serializable {

    private static final long serialVersionUID = -586264308558755306L;

    public static final String SUCCESS = "0";
    public static final String FAILED = "1";

    private String status_code;
    private String result_msg;
    private Object result;

    public ResultBody() {

    }

    public ResultBody(String status_code, String result_msg) {
        this.status_code = status_code;
        this.result_msg = result_msg;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public Object getResult() {
        if (result == null) {
            return "";
        }
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
