package demo.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;



@Service
public class KafkaConsumer {
	
 @KafkaListener(topics = "codeDecode_Topic", groupId = "codeDecode_group")

	    public String getMsg(String events) {

	        System.out.println("The data from Managment side: " + events);

	        return events;

	    }

	 



	 
}
