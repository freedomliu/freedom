package com.simple.freedom.testClass;

public class MyTest extends Thread{
	
	TestBean tb;
	public MyTest(String name,TestBean tb)
	{
		super(name);
		this.tb=tb;
	}
	
	public void run()
	{
		synchronized (tb) {
			tb.setPassword(getName());
			TestZhuti.list.add(tb);
			TestZhuti.sendSms();
		}
		
	}
	
	
	
	public static void main(String[] args)
	{
		TestBean tb=new TestBean();
		for (int i = 0; i <100; i++) {
			Thread mt=new MyTest(i+"",tb);
			mt.start();
		}
	}
}

