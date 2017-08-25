package com.channelsoft.kafka;

import com.channelsoft.utils.KafkaUtils;

/**
 * <dl>
 * <dt>${getClass}</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2016</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/8/22</dd>
 * </dl>
 *
 * @author lizhu
 */
public class TestKafka {
    public static void main(String[] args) {
        String message="call_detail:{\n" +
                "    \"_id\" : \"DCS-236:0101212000:70000001:TEL:988822173:TEL:910086:1497337677:1497337678:205:1\",\n" +
                "    \"agent_id\" : \"70000001\",\n" +
                "    \"agent_name\" : \"刘琴70000001\",\n" +
                "    \"end_type\" : 255,\n" +
                "    \"area_id\" : \"\",\n" +
                "    \"start_type\" : 2,\n" +
                "    \"duration\" : 1,\n" +
                "    \"agent_dn\" : \"TEL:988822173\",\n" +
                "    \"has_alerting_event\" : 0,\n" +
                "    \"sub_serviceid\" : \"\",\n" +
                "    \"orig_skill_id\" : \"\",\n" +
                "    \"remote_url_wx\" : \"\",\n" +
                "    \"is_release\" : 1,\n" +
                "    \"call_type\" : 1,\n" +
                "    \"campaign_id\" : \"\",\n" +
                "    \"is_handled\" : 0,\n" +
                "    \"remote_url\" : \"TEL:910086\",\n" +
                "    \"record_addr\" : \"NoRecordAddr\",\n" +
                "    \"end_time_halfhour\" : \"1497337200\",\n" +
                "    \"end_time\" : \"1497337678\",\n" +
                "    \"region_id\" : \"010\",\n" +
                "    \"state_num\" : 1,\n" +
                "    \"session_id\" : \"7374995749399954312\",\n" +
                "    \"main_serviceid\" : \"1\",\n" +
                "    \"serial_num\" : 3,\n" +
                "    \"global_serial_num\" : 4,\n" +
                "    \"start_time\" : \"1497337677\",\n" +
                "    \"ent_id\" : \"0101212000\",\n" +
                "    \"local_url\" : \"TEL:988822173\",\n" +
                "    \"skill_name\" : \"00101212测试组\",\n" +
                "    \"accept_skill_id\" : \"\",\n" +
                "    \"skill_type\" : \"\",\n" +
                "    \"skill_id\" : \"1000002206\",\n" +
                "    \"local_url_wx\" : \"\",\n" +
                "    \"status\" : 205\n" +
                "}";
        boolean bol=KafkaUtils.sendMessage("chatLog",message);
        System.out.println(bol);

    }
}
