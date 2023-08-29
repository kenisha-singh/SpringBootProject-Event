package demo.boot.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class Consumer {

	@KafkaListener(topics = "codeDecode_Topic", groupId = "codeDecode_group")

	public String getMsg(String events) {

		System.out.println("The data from service  side: " + events);

		return events;

	}

}