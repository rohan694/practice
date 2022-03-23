package com.practice.code.general;

public class SingletonExample {

	private SingletonExample singletonExample;
	
	private SingletonExample() {
		
	}
	public synchronized SingletonExample  getInstance() {
		if(singletonExample==null) {
			return new SingletonExample();
		}
		return singletonExample;
	}
}
//arr.stream().filter(x-> x%2==0).collect(Collectors.toList());

interface animal{
	void eat();
	void run();
}
class Dog implements animal{

	@Override
	public void eat() {
		System.out.println("eating");
		
	}

	@Override
	public void run() {
		System.out.println("running");
	}
	
}
class MyThread implements Runnable{
	
	public void run() {
		System.out.println("unning");
	}
}

class example{
	public static void main(String args[]) {
		//animal a=new Dog();
		Thread t1=new Thread(new MyThread());
		
		
		t1.start();
		
	}
}