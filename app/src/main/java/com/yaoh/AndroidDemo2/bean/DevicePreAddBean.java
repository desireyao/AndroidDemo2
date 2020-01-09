package com.yaoh.AndroidDemo2.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by yaoh on 2019/5/22
 */
public class DevicePreAddBean {

    /**
     * data : {"deviceGroupId":[{"mac123":10},{"mac456":11}]}
     * message : 预安装设备成功
     * result : 0
     */

    private DataBean data;
    private String message;
    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {

        private List<Map<String,Integer>> deviceGroupId;

        public List<Map<String, Integer>> getDeviceGroupId() {
            return deviceGroupId;
        }

        public void setDeviceGroupId(List<Map<String, Integer>> deviceGroupId) {
            this.deviceGroupId = deviceGroupId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "deviceGroupId=" + deviceGroupId +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DevicePreAddBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
