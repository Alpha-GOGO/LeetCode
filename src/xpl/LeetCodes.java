package xpl;

import java.util.*;

import javax.xml.transform.Templates;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class LeetCodes {
	//_1两数之和
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map=new HashMap<>();
		for(int i=0;i<nums.length;i++) {
			int temp=target-nums[i];
			if(map.containsKey(temp)) {
				return new int[] {map.get(temp), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("");
	}
	
	//_2两数相加     8ms
	public class ListNode {
	     int val;
		 ListNode next;
	     ListNode(int x) { val = x; }
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = l1, b = l2;
        int puls, ex;
        puls = a.val+b.val;
        ListNode c = new ListNode(puls%10);
        ListNode d = c;
        ex = (a.val+b.val)/10;
        a = a.next;
        b = b.next;
        while(true){
            if(a!=null && b!=null){
                puls = ex + a.val + b.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                a = a.next;
                b = b.next;
            }
            else if(a!=null && b==null){
                puls = ex + a.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                a = a.next;
            }
            else if(a==null && b!=null){
                puls = ex + b.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                b = b.next;
            }       
            else
                break;
            c = c.next;
        }
        if(ex!=0)
            c.next = new ListNode(ex);
        return d;
    }
	
	//_3无重复字符的最长子串
	public int lengthOfLongestSubstring(String s) {
		/*int len = s.length();
		int maxlen = 0;
		int temp = 0;
		ArrayList<Character> list = new ArrayList<Character>();
		for(int i = 0; i < len; i++) {
			if(list.contains(s.charAt(i))) {
				if(maxlen < temp) {
					maxlen = temp;
				}
				temp = 1;
				list.clear();
				list.add(s.charAt(i));
			}
			else {
				list.add(s.charAt(i));
				temp++;
			}
		}
		if(maxlen < temp) {
			maxlen = temp;
		}
		return maxlen;
		*/
		int len = s.length();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int start = 0;
		int now = 0;
		int maxlen = 0;
		for(int i = 0; i < len; i++) {
			if(!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), i);
				now++;
			}
			else {
				int index = map.get(s.charAt(i));
				for(int j = start; j <= index; j++) {
					map.remove(s.charAt(j));
				}
				start = index + 1;
				map.put(s.charAt(i), i);
				now = i - index;
			}
			maxlen = Math.max(maxlen, now);
		}
		return maxlen;
	}

	/**
	 * 5 最长回文子串， 马拉车算法
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		int mi = 0;
		int right = 0;
		int maxlength = 0;
		int maxpoint = 0;
		String temp = "@#";
		for(int i = 0; i < s.length(); i++){
			temp += s.charAt(i);
			temp += "#";
		}
		temp += "*";

		int[] p = new int[temp.length()];
		for(int i = 0; i < temp.length(); i++){
			p[i] = 0;
		}
		for(int i = 1; i < temp.length()-1; i++){
			p[i] = right > i? Math.min(p[2*mi-i], right - i) : 1;
			while(temp.charAt(i+p[i]) == temp.charAt(i-p[i])){
				p[i]++;
			}
			if(i + p[i] > right){
				right = i + p[i];
				mi = i;
			}
			if(maxlength < p[i]){
				maxlength = p[i];
				maxpoint = i;
			}
		}
		//(maxpoint - maxlength)/2为最长回文数的起始点，maxlength为最长回文数的长度
		return s.substring((maxpoint - maxlength)/2, (maxpoint - maxlength)/2 + maxlength - 1);

	}

	//6 Z型变化
	public String convert(String s, int numRows) {
        String result = "";
        
        
        
        return result;
    }
	
	//7_整数反转
	public int reverse(int x) {
		long result = 0;
		int flag = 0;
		if(x < 0)
			flag = 1;
		x = Math.abs(x);
		while(x!=0) {
			result = result*10 + x%10;
			x = x/10;
		}
		if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return 0;
		}
		if(flag == 1)
			result = -result;
		return (int)result;
		
	}
	
	//8 字符串转化为整数
	public int myAtoi(String str) {
        long result = 0;
        
        //flag为1表示负数，为0 表示正数
        int flag = 0;
        
		str = str.trim();
		
		int len = str.length();
		if(len == 0)
			return 0;
		if(!Character.isDigit(str.charAt(0)) && str.charAt(0)!='+' && str.charAt(0)!='-') {
			return 0;
		}
		for(int i = 0; i < len; i++) {
			if((str.charAt(i) != '-' && str.charAt(i)!='+' && !Character.isDigit(str.charAt(i))) && i == 0 ) {
				return 0;
			}
			
			if(!Character.isDigit(str.charAt(i)) && i != 0) {
				break;
			}
			
			if(str.charAt(i) == '-' && i == 0) {
				flag = 1;
			}
			
			if(Character.isDigit(str.charAt(i)) ) {
				int temp;
				if(flag == 1) {
					temp = Integer.valueOf(String.valueOf(str.charAt(i)));
					temp = -temp;
				}
				else {
					temp = Integer.valueOf(String.valueOf(str.charAt(i)));
				}
				result = result * 10 + temp;
			}
			if(result > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			else if(result < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		
        return (int)result;
    }
	
	//9 回文数
	public boolean isPalindrome(int x) {
        if(x < 0)
        	return false;
		int temp = 0;
        int y = x;
        boolean result = false;
        while(y != 0) {
        	temp = temp*10 + y%10;
        	y = y/10;
        }
        if(temp == x)
        	result = true;
        return result;
    }

	/**
	 * 11盛水最多的容器
	 * @param height
	 * @return
	 */
//	public int maxArea(int[] height) {
//
//	}
	
	//14最长公共前缀
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 1) {
			return strs[0];
		}
		if(strs == null || strs.length == 0)
			return "";
        String result = "" ;
        int minlength = Integer.MAX_VALUE;
        for(int i = 0 ;i < strs.length; i++) {
        	if(strs[i].length() < minlength)
        		minlength = strs[i].length();
        }
        if(minlength == 0) {
        	return "";
        }
        
        LABEL:
        for(int i = 0; i < minlength; i++) {
        	for(int j = 0; j < strs.length-1; j++) {
        		if(strs[j].charAt(i) != strs[j+1].charAt(i)) {
        			break LABEL;
        		}
        	}
        	result += strs[0].charAt(i);
        }
        
        return result;
    }
	
	//13罗马数字转整数
	public int romanToInt(String s) {
        int result = 0;
        int len = s.length();
        int i = 0;
        for(i = 0; i < len-1; i++) {
        	if(s.charAt(i) == 'I') {
        		if(s.charAt(i+1) == 'V') {
        			result += 4;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'X') {
        			result += 9;
        			i++;
        		}
        		else {
					result += 1;
				}
        	}
        	else if(s.charAt(i) == 'V') {
        		result += 5;
        	}
        	else if(s.charAt(i) == 'X') {
        		if(s.charAt(i+1) == 'L') {
        			result += 40;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'C') {
        			result += 90;
        			i++;
        		}
        		else {
					result += 10;
				}
        	}
        	else if(s.charAt(i) == 'L') {
        		result += 50;
        	}
        	else if(s.charAt(i) == 'C') {
        		if(s.charAt(i+1) == 'D') {
        			result += 400;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'M') {
        			result += 900;
        			i++;
        		}
        		else {
					result += 100;
				}
        	}
        	else if(s.charAt(i) == 'D') {
        		result += 500;
        	}
        	else if(s.charAt(i) == 'M') {
        		result += 1000;
        	}
        }
        if(i == len-1) {
        	if(s.charAt(i) == 'I')result += 1;
        	else if(s.charAt(i) == 'V')result += 5;
        	else if(s.charAt(i) == 'X')result += 10;
        	else if(s.charAt(i) == 'L')result += 50;
        	else if(s.charAt(i) == 'C')result += 100;
        	else if(s.charAt(i) == 'D')result += 500;
        	else if(s.charAt(i) == 'M')result += 1000;
        }
        return result;
    }

	/**
	 * 17 电话号码的组合，采用递归
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(2,"abc");
		map.put(3,"def");
		map.put(4,"ghi");
		map.put(5,"jkl");
		map.put(6,"mno");
		map.put(7,"pqrs");
		map.put(8,"tuv");
		map.put(9,"wxyz");
		return letterCombinations(digits, map);
	}
	public List<String> letterCombinations(String digits, Map<Integer, String> map){

		List<String> result = new LinkedList<>();

		if(digits.length() == 0)
			return result;

		if(digits.length() == 1){
			String s = map.get(Integer.parseInt(digits));
			for(int i = 0; i < s.length(); i++){
				result.add("" + s.charAt(i));
			}
			return result;
		}

		List<String> p = letterCombinations(digits.substring(1), map);
		String h = map.get(Integer.parseInt(digits.substring(0, 1)));
		for(String s : p){
			for(int i = 0; i < h.length(); i++){
				result.add(h.charAt(i) + s);
			}
		}

		return result;
	}

	/**
	 * 19 删除链表的倒数第N个节点
	 * 尝试循环一遍
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode listNode = head;
		ListNode p;
		int len = 0;
		while(listNode != null) {
			len ++;
			listNode = listNode.next;
		}
		if(len == 1)
			return null;
		if(n == len)
			return head.next;
		listNode = head;
		if(n == 1) {
			while(listNode.next.next != null) {
				listNode = listNode.next;
			}
		}
		
		int point = len - n;
		
		for(int i = 0; i < point; i++) {
			listNode = listNode.next;
		}
		return listNode;
    }
	
	//20.有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        if(s == "" || len == 0)
        	return true;
        for(int i = 0; i < len; i++) {
        	char ch = s.charAt(i);
        	if(ch == '(' || ch == '[' || ch == '{'){
        		stack.add(ch);
        	}
        	else if(ch == ')') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '(') {
        			stack.pop();
        		}
        		else {
					return false;
				}
        	}
        	else if(ch == ']') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '[')
        			stack.pop();
        		else {
					return false;
				}
        	}
        	else if(ch == '}') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '{') {
        			stack.pop();
        		}
        		else {
					return false;
				}
        	}
        }
        if(stack.size() != 0)
        	return false;
        
        return true;
    }
    
    //21 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode listNode;
    	if(l1 == null)
    		return l2;
    	if(l2 == null)
    		return l1;
    	if(l1.val < l2.val) {
    		listNode = l1;
    		listNode.next = mergeTwoLists(l1.next, l2);
    	}
    	else {
    		listNode = l2;
    		listNode.next = mergeTwoLists(l1, l2.next);
    	}
    	return listNode;
    }

	/**
	 * 26 删除排序数组中的重复项
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if(nums.length == 0)
			return 0;

		int j = 0;
		for(int i = 1; i  < nums.length; i++){
			if(nums[i] != nums[j]){
				j++;
				nums[j] = nums[i];
			}
		}

		return j+1;
	}

	/**
	 * 27移除元素
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
		if(nums.length == 0){
			return 0;
		}
		int j = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] != val ){
				nums[j] = nums[i];
				j++;
			}
		}
		return j+1;
	}

	/**
	 * 28 实现strStr()
	 * @param haystack
	 * @param needle
	 * @return
	 */
//	public int strStr(String haystack, String needle) {
//		if(needle == null){
//			return 0;
//		}
//
//	}

	/**
	 * 35 搜索插入位置，使用二分法
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		if(target > nums[nums.length-1])
			return nums.length;
		while(left <= right){
			mid = (left + right) / 2;
			if(nums[mid] == target){
				return mid;
			}
			else if(nums[mid] < target){
				left = mid+1;
			}
			else if(nums[mid] > target){
				right = mid - 1;
			}
		}
		return left;

	}

	/**
	 * 62 不同路径，m*n矩阵中左上角到右下角的路径数量
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		int[][] r = new int[m][n];

		if(m == 1|| n == 1)
			return 1;

		for(int i = 0; i < m; i++){
			r[i][0] = 1;
		}
		for(int i = 0; i < n; i++){
			r[0][i] = 1;
		}
		for (int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				r[i][j] = r[i-1][j] + r[i][j-1];
			}
		}
		return r[m-1][n-1];
	}

	/**
	 * 69 求平方根，牛顿迭代
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if(x == 0 )
			return 0;
		int a = x;
		while(a > x/a){
			a = (a + x/a)/2;
		}
		return (int)a;
	}

	/**
	 * 70 爬楼梯， 斐波那契数列
	 * @param n
	 * @return
	 */
	int[] result = new int[100];
	public int F(int a){
		if(result[a] != -1)
			return result[a];
		return result[a] = F(a-1) + F(a-2);
	}
	public int climbStairs(int n) {
		for(int i = 0; i < result.length; i++){
			result[i] = -1;
		}
		result[0] = result[1] = 1;
		return F(n);
	}

	/**
	 * 73 矩阵置零
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		int[] x = new int[matrix.length];
		int[] y = new int[matrix[0].length];
		for(int i = 0; i < x.length; i++)
			x[i] = 0;
		for(int i = 0; i < y.length; i++)
			y[i] = 0;
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(matrix[i][j] == 0){
					x[i] = 1;
					y[j] = 1;
				}
			}
		}
		for(int i = 0; i < x.length; i++) {
			if(x[i] == 1){
				for(int j = 0; j < matrix[i].length; j++){
					matrix[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < y.length; i++) {
			if(y[i] == 1){
				for(int j = 0; j < matrix.length; j++){
					matrix[j][i] = 0;
				}
			}
		}
	}
}
class ListNode {
    int val;
  	ListNode next;
 	ListNode(int x) { val = x; }
}















