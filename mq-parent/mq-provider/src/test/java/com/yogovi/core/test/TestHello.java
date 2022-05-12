package com.yogovi.core.test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yogovi.core.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHello {

	@Autowired
	private MessageService messageService;
	
	@Test
	public void test() {
		for (;;) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			messageService.sendMessage("TEST_TOPIC_EXCHANGE", "one", UUID.randomUUID().toString());
		}
	}
}
