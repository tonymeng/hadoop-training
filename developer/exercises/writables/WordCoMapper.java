import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class WordCoMapper extends MapReduceBase implements
    Mapper<LongWritable, Text, TextPairWritable, LongWritable> {

  @Override
  public void map(LongWritable key, Text value,
      OutputCollector<TextPairWritable, LongWritable> output,
      Reporter reporter) throws IOException {
    // TODO implement
  }
}
