package com.xnradmin.core.sms136;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SmsBatchSendTest {
	private static List<String> phoneList = null;
	static {
		phoneList = new ArrayList<String>();
	}

	private static void readFile(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				phoneList.add(tempString);
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	private static void sendSms() throws Exception {
		int size = 0;
		int b=0;
		int batchSize = 10;
		String[] phones = new String[batchSize];
		Iterator<String> pi = phoneList.iterator();
		boolean isSendOver = true;
		ClientSmsSend send=new ClientSmsSend();
		while (isSendOver) {
			if (size < batchSize && pi.hasNext()) {
				 String phone=pi.next();
				phones[size++] = phone;
				send.sendSms(2,"18210113786", "验证码为123423"); 
				System.out.println("s:"+phone);
			} else {
				System.out.println("other");
				size = 0;
				if (!pi.hasNext()) {
					break;
				}
				phones = new String[batchSize];
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					
			}
		}

//		for (String phone : phoneList) {
//			System.out.println("phone:" + phone);
//		}
	}

	public static void main(String[] args) {
		SmsBatchSendTest
				.readFile("C:/Users/lnl/Documents/Tencent Files/596946645/FileRecv/phone.txt");
		try {
			SmsBatchSendTest.sendSms();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
