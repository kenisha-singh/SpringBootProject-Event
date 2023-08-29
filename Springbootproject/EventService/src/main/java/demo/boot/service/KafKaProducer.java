package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import demo.boot.model.Event;

@Service
public class KafKaProducer {

	@Autowired
	KafkaTemplate<List<Event>, List<Event>> kafkaTemplate;

	public void sendMsgToTopic(List<Event> events) {
		System.out.println(events);
		kafkaTemplate.send("codeDecode_Topic", events);
	}

}
