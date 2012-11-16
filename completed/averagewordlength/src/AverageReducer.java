import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class AverageReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, DoubleWritable> {

	private static final DoubleWritable valueOut = new DoubleWritable();

	@Override
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {

		int count = 0;
		int totalSize = 0;
		IntWritable v;
		while (values.hasNext()) {
			v = values.next();
			totalSize += v.get();
			count++;
		}
		
		valueOut.set((double)totalSize / count);
		output.collect(key, valueOut);
	}
}
