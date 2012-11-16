import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class IndexMapper extends MapReduceBase implements
    Mapper<Text, Text, Text, Text> {

  @Override
  public void map(Text key, Text value, OutputCollector<Text, Text> output,
      Reporter reporter) throws IOException {

    // TODO implement
  }
}
