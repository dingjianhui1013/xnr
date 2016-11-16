/**
 *2014年12月25日 下午12:20:39
 */
package com.xnradmin.core.test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cntinker.util.MathHelper;
import com.cntinker.util.StringHelper;

/**
 * @author: liubin
 *
 */
public class T {

	private static void test() {

		int errorNum = 0;
		int countA = 0;
		int countB = 0;
		int[] sysNum = { StringHelper.getRandom(1), StringHelper.getRandom(1),
				StringHelper.getRandom(1), StringHelper.getRandom(1) };
		int[] usrNum = { StringHelper.getRandom(1), StringHelper.getRandom(1),
				StringHelper.getRandom(1), StringHelper.getRandom(1) };
		Set<Integer> set = new HashSet<Integer>(8);
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (sysNum[i] == usrNum[i]) {
				countA++;
			} else {
				count += 2;
				set.add(sysNum[i]);
				set.add(usrNum[i]);
			}
		}
		countB = count - set.size();
		System.out.println(countA + "A" + countB + "B");
		if (countA == 0 && countB == 0) {
			System.out.println("错误数字");
			if (++errorNum == 6) {
				System.out.println("游戏结束");
			}
		}

	}

	private static void say(String v) {
		v = v + "b";
	}

	private static void test2() {
		String v = "a";
		say(v);
		System.out.println(v);
	}

	private static void test3() {
		int[] a = { 1, 3, 5, 7 };
		// StringHelper.splitStr(content, split)
		System.out.println(MathHelper.sort(a,
				MathHelper.TYPE_INSERTSORT, true));
	}

	public static void main(String[] args) throws Exception {
		System.out.println("\u6ce8\u518c\u5931\u8d25\uff0c\u8bf7\u8f93\u5165\u6240\u6709\u5fc5\u586b\u9879\u3002");
		test3();
	}
}
