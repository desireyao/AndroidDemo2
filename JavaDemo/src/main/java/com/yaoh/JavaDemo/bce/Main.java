package com.yaoh.JavaDemo.bce;


import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdm.IotDmV3Client;
import com.baidubce.services.iotdm.model.v3.device.CreateDeviceRequest;
import com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfile;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceViewRequest;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

/**
 * Created by yaoh on 2019/3/26
 */
public class Main {


    public static void main(String[] args) {

        String ACCESS_KEY_ID = "715741cf46ab47b58416c3ef861f6d61";            // 用户的Access Key ID
        String SECRET_ACCESS_KEY = "cbbe734786394ef39589950265d3e1be";        // 用户的Secret Access Key
        String ENDPOINT = "iotdm.bj.baidubce.com";

        // 创建配置
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
                .withEndpoint(ENDPOINT);
//        // 初始化一个IotDmV3Client
        IotDmV3Client client = new IotDmV3Client(config);
//
        String deviceName = "device_name";    // 设置创建的设备名称
        String schemaId = "490b0423-0ca5-4f70-a538-a4320f23fc25";        // 设置绑定的模型ID
        String description = "description";   // 设置对该设备的描述
        CreateDeviceRequest request = new CreateDeviceRequest()
                .withDeviceName(deviceName)
                .withSchemaId(schemaId)
                .withDescription(description);

        DeviceAccessDetailResponse response = client.createDevice(request);
        System.out.println("response==========>" + response.getUsername());

//        int pageNo = 1;                   // 设置需要获取所有查询结果的第几页
//        int pageSize = 10;                // 设置每页返回的最大个数
//        String order = "desc";            // 设置按照升序、降序排列结果，支持asc/desc
//        String orderBy = "createTime";    // 设置排序的索引列，支持name/createTime/lastUpdatedTime
//        String key = "schema_name";       // 查询关键字，支持模板名称/模板描述

//        SchemaListResponse response = client.getSchemas(pageNo, pageSize, null, null, null);

//        ObjectNode reported = new ObjectMapper().createObjectNode();
//        reported.put("light", "red");

        // 设置需要更新的设备端属性期望值
//        ObjectNode desired = new ObjectMapper().createObjectNode();
//        desired.put("gw_mac", "01020304050607");
//
//        UpdateDeviceViewRequest request = new UpdateDeviceViewRequest()
//                .withDesired(desired);
//        DeviceViewResponse response = client.updateDeviceView("sss", request);


//        int pageNo = 1;                     // 设置需要获取所有查询结果的第几页
//        int pageSize = 10;                  // 设置每页返回的最大个数
////        String orderBy = "name";          // 设置排序的索引列，支持name/createTime/lastActiveTime
////        String order = "asc";             // 设置按照升序、降序排列结果，支持asc/desc
//        String orderBy = null;          // 设置排序的索引列，支持name/createTime/lastActiveTime
//        String order = null;             // 设置按照升序、降序排列结果，支持asc/desc
//
//        String name = "schemaName";           // 设置需要查询的属性名
//        // 支持设备名字查询(name)/模型名字查询(schemaName)/服务端属性查询(attributes.***)/设备端属性查询(device.reported.***)
//        String value = "mode1";  // 设置需要查询的属性值，对于设备端属性查询，如果相应属性值为字符串类型，需要用""将字符串包裹
//        String favourite = "all";         // 设置收藏选择，支持all/true/false
//        DeviceProfileListResponse response = client.getDeviceProfiles(pageNo, pageSize, orderBy, order, name, value, favourite);
//
////        int amount = response.getAmount();                      // 满足查询条件的设备数目
////        int pageNo = response.getPageNo();                      // 当前页页码
////        int pageSize = response.getPageSize();                  // 返回的每页的最大个数
//        List<DeviceProfile> devices = response.getDevices();      // 当前页设备详情列表
//        System.out.print("devices---> " + devices.get(0).);


//        int pageNo = 1;                 // 设置需要获取所有查询结果的第几页
//        int pageSize = 1;              // 设置每页返回的最大个数
//        String order = null;            // 设置按照升序、降序排列结果，支持asc/desc
//        String orderBy = null;    // 设置排序的索引列，支持name/createTime/lastUpdatedTime
//        String key = "gate_model";       // 查询关键字，支持模板名称/模板描述
//        SchemaListResponse response = client.getSchemas(pageNo, pageSize, orderBy, order, key);
//        System.out.print("response---> " + response);

    }


}
