package kafka;

import kafka.consumer.SimpleConsumer;

public class TestKafka {
	
	public static void main(String[] args) {
		
		SimpleConsumer consumer = new SimpleConsumer("localhost", 9092, 100000, 1024000);
		long[] offsets = consumer.getOffsetsBefore("tester", 0, -1, 10);
		
		for (int i = 0; i < offsets.length; i++) {
			System.err.println(offsets[i]);
		}
		System.err.println("done");
	}

}
