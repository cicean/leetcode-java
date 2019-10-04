package PocketGems;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by cicean on 9/9/2016.
 * 第二题：Log Parser
 有事游戏中有Log用于记录各种状态，想知道究竟用户连接的时间是多少。状态除了START, CONNECTED, DISCONNECTED, SHUTDONW外还有ERROR啥的，但是只需要关注前四个。
 输入形式： vector<string> lines
 (11/01/2015-04:00:00) :: START
 (11/01/2015-04:00:00) :: CONNECTED
 (11/01/2015-04:30:00) :: DISCONNECTED
 (11/01/2015-04:45:00) :: CONNECTED
 (11/01/2015-05:00:00) :: SHUTDOWN

 输出形式：75%%(注意两个百分号).
 */
public class LogParser {
	
	private Map<String,Integer> statusmap;
	
	public LogParser() {
		// TODO Auto-generated constructor stub
		statusmap = new HashMap<>();
		statusmap.put("START", 0);
		statusmap.put("CONNECTED", 1);
		statusmap.put("DISCONNECTED", -1);
		statusmap.put("SHUTDOWN", -1);
	}
	
	private Date converToDate(String datastring) {
		DateFormat dFormat = new SimpleDateFormat("MM/dd/yyyy-hh:mm:ss");
		Date date = new Date();
		try {
			date = dFormat.parse(datastring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public String parseLines(String[] lines) {
		long startTime = new Long(0);
		long endTime = new Long(0);
		long connectedTime = 0;
		long lastTimePoint = 0;
		for (int i = 0; i < lines.length; i++) {
			String[] line = lines[i].split(" :: ");
			long tmpTime = converToDate(line[0].substring(1, line[0].length() - 1)).getTime();
			String status = line[1];
			
			if (i == 0) startTime = tmpTime;
			if (i == lines.length - 1) endTime = tmpTime;
			if (status.equals("CONNECTED")) {
				lastTimePoint = tmpTime;
			} else if (status.equals("DISCONNECTED") || status.equals("SHUTDOWN")) {
				if (lastTimePoint > 0) {
					connectedTime += tmpTime - lastTimePoint;
					lastTimePoint = -1;
				}
			}
		}
		if (endTime - startTime == 0) return "0%";
		System.out.println(endTime - startTime);
		System.out.println(connectedTime);
		double ratio = (double) connectedTime/(endTime - startTime) * (new Double(100));
		DecimalFormat df = new DecimalFormat("######0");
		System.out.println(ratio);
		System.out.println(df.format(ratio));
		return String.format("%s%s", df.format(ratio), "%");
	}
	
	public static void main(String[] args) {
		String[] test = {"(11/01/2015-03:30:00) :: START", 
							"(11/01/2015-03:30:00) :: CONNECTED", 
							"(11/01/2015-04:00:00) :: DISCONNECTED", 
							"(11/01/2015-04:15:00) :: SHUTDOWN"};
		LogParser slt = new LogParser();
		System.out.println(slt.parseLines(test));
	}

}
