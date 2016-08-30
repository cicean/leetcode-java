import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
 71	Simplify Path	20.0%	Medium
 Problem:    Simplify Path
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/simplify-path/
 Notes:
 Given an absolute path for a file (Unix-style), simplify it.
 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 Corner Cases:
 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".
 Solution: Add an additional '/' at the end of 'path' for simply detecting the end.
 */
public class SimplifyPath {
	
	/**
	 * 复杂度
	 * 时间 O(N) 空间 O(N)
	 * 思路
	 * 思路很简单，先将整个路径按照/分开来，然后用一个栈，遇到..时弹出一个，遇到.和空字符串则不变，遇到正常路径则压入栈中。
	 * 注意
	 * 如果结果为空，要返回一个/
	 * 弹出栈时要先检查栈是否为空
	 * @param path
	 * @return
	 */
	public class Solution {
	    public String simplifyPath(String path) {
	        Stack<String> stk = new Stack<String>();
	        String[] parts = path.split("/");
	        for(String part : parts){
	            switch(part){
	                case ".":
	                case "" :
	                    break;
	                case "..":
	                    if(!stk.isEmpty()){
	                        stk.pop();
	                    }
	                    break;
	                default:
	                    stk.push(part);
	            }
	        }
	        StringBuilder sb = new StringBuilder();
	        if(stk.isEmpty()){
	            return "/";
	        }
	        while(!stk.isEmpty()){
	            sb.insert(0, "/"+stk.pop());
	        }
	        return sb.toString();
	    }
	    
	    public String simplifyPath_1(String path) {
	        Stack<String> stack = new Stack<String>();
	        //三种需要跳过的情况
	        Set<String> skip = new HashSet<String>(Arrays.asList("..", ".", ""));
	        
	        for (String dir : path.split("/")) {
	            //当遇到..时，需要向前进
	            if (dir.equals("..") && !stack.isEmpty()) {
	                stack.pop();
	            } else if (!skip.contains(dir)) {
	                stack.push(dir);
	            }
	        }
	        String result = "";
	        if (stack.isEmpty()) result += "/";
	        while (!stack.isEmpty()) {
	            //pop出来的顺序是反的，所以加的时候，把最新pop出来的路径加在前面
	            result = "/" + stack.pop() + result;
	        }
	        return result;
	    }
	}
	
	/**
	 * First to first, there is no stack or deque used in my solution. The only thing I did is just to transfer original string into char array.

My idea is:
Key: Use a counter to count how many times ".." appears before we handle a normal string of part of the path.

go through the char array from end to start
ignore all '/' characters
get string between '/'
handle this string in 4 branches:
4.a if it is empty or equals to ".", do nothing
4.b if it is equals to "..", counter++
4.c if the counter is greater than 0, then counter--
4.d else ( counter == 0) do concatenation of result with current part of path.
	 * @param path
	 * @return
	 */
	public String simplifyPath_1(String path) {
        String ans = "";
        char[] chars = path.toCharArray();
        int i = chars.length - 1;
        // a counter to count how many times ".." shows up
        int count = 0;  
        while ( i >=0) {
            int j = i;
            while( j >= 0 && chars[j] == '/')
                j--;
            int k = j;
            while(k >=0 && chars[k] != '/')
                k--;
            String part = String.valueOf(chars, k+1, j-k);
            if (part.isEmpty() || part.equals(".")){
                // do nothing
            }
            // count appearence of ".."
            else if(part.equals("..")) 
                count++;
           // ignore current part, 
           //because there is ".." after it
           else if (count > 0) 
                count--;
           // count == 0, no need to ignore current part 
           // and do result concatenation
            else
                ans = "/" + part + ans;
            i = k;
        }
        return ans.isEmpty()? "/" : ans;
    }
	
	
	public String simplifyPath(String path) {
        if(path.length()==0) return "/";
        if(path.charAt(0)!='/') return "/";
        ArrayList<String> dirs = new ArrayList<String>();
        String[] str = path.split("/");
        for (int i = 0; i < str.length; ++i) {
            if ((i == 0 || i == str.length - 1) && str[i].compareTo("") == 0) continue;
            if (str[i].compareTo("..") == 0) {
                if (dirs.isEmpty() == false) {
                    dirs.remove(dirs.size() - 1);
                }
            } else if ((str[i].compareTo(".") != 0) && (str[i].compareTo("") != 0)) {
                dirs.add(str[i]);
            }
        }
        if (dirs.isEmpty() == true) return "/";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < dirs.size(); ++i) {
            res.append("/");
            res.append(dirs.get(i));
        }
        return res.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimplifyPath slt =  new SimplifyPath();	
		String path = "/a/./b/../../c/";
		System.out.println(slt.simplifyPath(path));
	}

}
