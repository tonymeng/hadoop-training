import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class LogFileMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {
	
	private static final Text keyOut = new Text();
	private static final IntWritable valueOut = new IntWritable(1);
	private static final Matcher ipm = Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}").matcher("");
	/*
	 * Input line looks like: 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400]
	 * "GET /cat.jpg HTTP/1.1" 200 12433
	 */
	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		ipm.reset(value.toString());
		if (ipm.find()) {
			keyOut.set(ipm.group(0));
			output.collect(keyOut, valueOut);
		}
	}
}
