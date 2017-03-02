package solution_to_algo_problems;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class CodilityTestOfTimeHandling {
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

	@Test
	public void testMaximumTime() {
		try {
			Calendar cal = Calendar.getInstance();
			// int a = 1, b = 8, c = 3, d = 2;
			int a = 9, b = 1, c = 9, d = 7;
			List<String> stringList = getStringList(a, b, c, d);
			long maxTime = -1l;
			String maxTimeInString = "NOT POSSIBLE";
			for (int i = 0; i < stringList.size(); ++i) {
				for (int j = 0; j < stringList.size(); ++j) {
					for (int k = 0; k < stringList.size(); ++k) {
						for (int l = 0; l < stringList.size(); ++l) {
							if (i != j && i != k && i != l && j != k && j != l
									&& k != l) {
								String time = stringList.get(i)
										+ stringList.get(j) + ":"
										+ stringList.get(k) + stringList.get(l);
								if (isValidTime(time)) {

									Date parse = simpleDateFormat.parse(time);

									cal.setTime(parse);
									if (maxTime < cal.getTimeInMillis()) {
										maxTime = cal.getTimeInMillis();
										maxTimeInString = time;
									}
								}
							}
						}
					}
				}
			}
			System.out.println(maxTimeInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static final String TIME_PATTERN = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
	private static final Pattern pattern;
	static {
		pattern = Pattern.compile(TIME_PATTERN);
	}

	@Test
	public void testTimeRegex() {
		int a = 1, b = 8, c = 3, d = 2;
		Matcher matcher = pattern.matcher(String.valueOf(c) + String.valueOf(d)
				+ ":" + String.valueOf(a) + String.valueOf(b));
		System.out.println(matcher.matches());
	}

	private boolean isValidTime(String time) {
		Matcher matcher = pattern.matcher(time);
		return matcher.matches();
	}

	private List<String> getStringList(int a, int b, int c, int d) {
		List<String> list = new ArrayList<String>();
		list.add(String.valueOf(a));
		list.add(String.valueOf(b));
		list.add(String.valueOf(c));
		list.add(String.valueOf(d));
		return list;
	}
	@Test
	public void testParkingLotFee() {
		try {
			Calendar startTime = Calendar.getInstance();
			Date startTimeparse = simpleDateFormat.parse("09:42");		
			startTime.setTime(startTimeparse);
			
			Calendar endTime = Calendar.getInstance();
			Date endTimeParse = simpleDateFormat.parse("11:42");
			endTime.setTime(endTimeParse);
			long timeGapInMillisec = endTime.getTimeInMillis() - startTime.getTimeInMillis();
			double timeGapInSec = (double)(timeGapInMillisec/1000);
			double timeGapInHour = (double)timeGapInSec / 3600;
			int parkingTime = (int)Math.ceil(timeGapInHour) ;
			System.out.println(parkingTime);
			int parkingFee = 0;
			for(int count = 0; parkingTime > 0; parkingTime--, count++){
				if(count == 0){
					parkingFee += 2;
					parkingFee += 3;
				}else{
					parkingFee +=4;
				}
			}
			System.out.println(parkingFee);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
