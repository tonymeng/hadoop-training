import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class IndexMapper extends MapReduceBase implements
		Mapper<Text, Text, Text, Text> {
	
	private static final Text keyOut = new Text();
	private static final Text valueOut = new Text();

	@Override
	public void map(Text key, Text value, OutputCollector<Text, Text> output,
			Reporter reporter) throws IOException {
		FileSplit fs = (FileSplit) reporter.getInputSplit();
		valueOut.set(fs.getPath().getName() + "@" + key.toString());
		for (String word : value.toString().split("\\W+")) {
			if (word.length() > 0) {
				keyOut.set(word);
				output.collect(keyOut, valueOut);
			}
		}
	}
}
