import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class SumReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, IntWritable> {

	private static final IntWritable valueOut = new IntWritable();

	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {

		int wordCount = 0;
		while (values.hasNext()) {
			IntWritable value = values.next();
			wordCount += value.get();
		}
		valueOut.set(wordCount);
		output.collect(key, valueOut);
	}
}
