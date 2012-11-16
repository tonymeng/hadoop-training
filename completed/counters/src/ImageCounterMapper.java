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

public class ImageCounterMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, Text, IntWritable> {

	protected static final Matcher imagem = Pattern.compile(
			".(gif|jpg)").matcher("");

	/*
	 * Input line looks like: 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400]
	 * "GET /cat.jpg HTTP/1.1" 200 12433 "http://google.com" "Firefox/3.6.16"
	 */
	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		imagem.reset(value.toString());
		if (imagem.find() && imagem.groupCount() > 0) {
			String type = imagem.group(1);
			if (type.equals("jpg")) {
				reporter.incrCounter("FileType", "jpg", 1);
			} else if (type.equals("gif")) {
				reporter.incrCounter("FileType", "gif", 1);
			} else {
				reporter.incrCounter("FileType", "other", 1);
			}
		} else {
			reporter.incrCounter("FileType", "other", 1);
		}
	}
}
