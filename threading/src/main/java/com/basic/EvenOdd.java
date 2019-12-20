package com.basic;

public class EvenOdd {
	public static void main(String[] args) {
		SharedObject printer = new SharedObject();
		OddThread oddThread = new OddThread(20, printer);
		oddThread.setName("Odd");
		EvenThread evenThread = new EvenThread(20, printer);
		evenThread.setName("Even");
		oddThread.start();
		evenThread.start();
	}
}

class OddThread extends Thread {
	int limit;
	SharedObject printer;

	public OddThread(int limit, SharedObject printer) {
		this.limit = limit;
		this.printer = printer;
	}

	@Override
	public void run() {
		int oddNumber = 1;
		while (oddNumber <= limit) {
			printer.printOdd(oddNumber);
			oddNumber = oddNumber + 2;
		}
	}
}

class EvenThread extends Thread {
	int limit;
	SharedObject printer;

	public EvenThread(int limit, SharedObject printer) {
		this.limit = limit;
		this.printer = printer;
	}

	@Override
	public void run() {
		int evenNumber = 2;
		while (evenNumber <= limit) {
			printer.printEven(evenNumber);
			evenNumber = evenNumber + 2;
		}
	}
}

class SharedObject {
	boolean oddWait = false;

	synchronized void printOdd(int number) {
		while (oddWait) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + number);
		oddWait = true;
		notify();
	}

	synchronized void printEven(int number) {
		while (!oddWait) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + number);
		oddWait = false;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
	}
}
