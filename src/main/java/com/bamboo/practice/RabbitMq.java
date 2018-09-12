package com.bamboo.practice;
import com.bamboo.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMq {
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * =============简单、工作队列模式===============
     * =============没有绑定交换机===============
     */
    public void simple_queue(){
        amqpTemplate.convertAndSend("simple_queue","66666666");
    }

    /**
     * =============发布/订阅模式===============
     * =============绑定fanout_exchange交换机===============
     */
    public void ps_queue_1(){
        this.amqpTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "ps_queue_1", "这就是发布订阅模式");
    }

    /**
     * =============路由模式===============
     * =============绑定direct_exchange交换机===============
     * =============routingKey 非必填===============
     */
    public void routing_queue_1(){
        String routingKey = "123456";
        this.amqpTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, routingKey, "竟然还需要routingKey");
    }


    /**
     * =============主题模式===============
     * =============绑定direct_exchange交换机===============
     * =============routingKey 非必填===============
     */
    public void topic_queue_1(){
        this.amqpTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.TOPIC_QUEUE_1, "主题模式哦");
    }
}
