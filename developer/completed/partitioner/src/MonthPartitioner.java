import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;


public class MonthPartitioner implements Partitioner<Text, IntWritable> {
	
	@Override
	public void configure(JobConf job) {
		
	}
	
	private static DateFormat sdf = new SimpleDateFormat("MMM", Locale.ENGLISH);
	
	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		String[] sa = key.toString().split(":");
		Date date;
		try {
			date = sdf.parse(sa[1]);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(date);
			return cal.get(Calendar.MONTH) % numPartitions;
		} catch (ParseException e) {
			return 0;
		}
	}

}
