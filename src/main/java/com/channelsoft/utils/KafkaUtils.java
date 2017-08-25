package com.channelsoft.utils;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

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
public class KafkaUtils {
    private static Producer kafkaProducer;
    static {
        init();
    }

    public  static void init(){
        Properties properties=new Properties();
        properties.put("metadata.broker.list","node1:9092,node2:9092,node3:9092");
        properties.put("zookeeper.connect","node1:2181,node2:2181,node3:2181");
        properties.put("serializer.class", StringEncoder.class.getName());
        kafkaProducer = new Producer<Integer, String>(new ProducerConfig(properties));
    }

    public static boolean sendMessage(String topic,String message){
        try{
            kafkaProducer.send(new KeyedMessage<Integer, String>(topic, message));
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
