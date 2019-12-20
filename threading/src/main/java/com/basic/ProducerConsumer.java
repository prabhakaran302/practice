package com.basic;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
	public static void main(String[] args) {
		List<String> sharedList = new ArrayList<String>();
		ProducerConsumerObject so = new ProducerConsumerObject(sharedList);
		Producer p = new Producer(so);
		Consumer c = new Consumer(so);
		Thread producer = new Thread(p, "producer");
		Thread consumer = new Thread(c, "consumer");
		producer.start();
		consumer.start();
	}
}

class ProducerConsumerObject {
	private List<String> sList;

	public ProducerConsumerObject(List<String> sharedList) {
		sList = sharedList;
	}

	public synchronized void produce() throws InterruptedException {
		while (sList.size() == 1) {
			wait();
		}
		System.out.println("Produced " + sList.add("A"));
		notify();
	}

	public synchronized void consume() throws InterruptedException {
		while (sList.size() == 0) {
			wait();
		}
		System.out.println("Consumed " + sList.remove(0));
		Thread.sleep(500);
		notify();
	}
}

class Producer implements Runnable {

	private ProducerConsumerObject so;

	public Producer(ProducerConsumerObject so2) {
		so = so2;
	}

	public void run() {
		while (true) {
			try {
				so.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class Consumer implements Runnable {
	private ProducerConsumerObject so;

	public Consumer(ProducerConsumerObject so2) {
		so = so2;
	}

	public void run() {
		while (true) {
			try {
				so.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
