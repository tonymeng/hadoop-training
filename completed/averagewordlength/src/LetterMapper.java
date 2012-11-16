import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class LetterMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {

	private static final Text keyOut = new Text();
	private static final IntWritable valueOut = new IntWritable();

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		for (String s : value.toString().split("\\W+")) {
			if (s.length() > 0) {
				keyOut.set(Character.toLowerCase(s.charAt(0))+"");
				valueOut.set(s.length());
				output.collect(keyOut, valueOut);
			}
		}
	}
}
