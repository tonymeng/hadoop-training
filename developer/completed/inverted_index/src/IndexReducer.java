import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class IndexReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	private static final StringBuilder sb = new StringBuilder();
	private static final Text valueOut = new Text("");
	private static final String SEP = ",";

	@Override
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		sb.setLength(0); // empty string builder
		while (values.hasNext()) {
			if (sb.length() > 0)
				sb.append(SEP);
			sb.append(values.next().toString());
		}
		valueOut.set(sb.toString());
		output.collect(key, valueOut);
	}
}
