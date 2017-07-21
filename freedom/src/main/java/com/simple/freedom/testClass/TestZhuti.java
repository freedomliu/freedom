package com.simple.freedom.testClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestZhuti {
	static List<TestBean> list=new ArrayList<TestBean>();
	public  static void sendSms()
	{
		TestBean tb= list.get(list.size()-1);
		System.out.println(tb.getPassword());
	}
}
