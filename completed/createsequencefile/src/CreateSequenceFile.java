import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CreateSequenceFile extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {

		String input, output;
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			input = "weblog";
			output = "weblogseq";
		}

		JobConf conf = new JobConf(getConf(), CreateSequenceFile.class);
		conf.setJobName(this.getClass().getName());

		FileInputFormat.setInputPaths(conf, new Path(input));
		SequenceFileOutputFormat.setOutputPath(conf, new Path(output));
		SequenceFileOutputFormat.setCompressOutput(conf, true);
		SequenceFileOutputFormat.setOutputCompressorClass(conf, BZip2Codec.class);
		SequenceFileOutputFormat.setOutputCompressionType(conf, CompressionType.BLOCK);
		
		conf.setOutputFormat(SequenceFileOutputFormat.class);

	    conf.setMapperClass(CreateSequenceFile.IdentityMapper.class);
	    conf.setReducerClass(CreateSequenceFile.IdentityReducer.class);
	    conf.setMapOutputKeyClass(LongWritable.class);
	    conf.setMapOutputValueClass(Text.class);
	    conf.setOutputKeyClass(LongWritable.class);
	    conf.setOutputValueClass(Text.class);

		JobClient.runJob(conf);
		return 0;
	}

	public static class IdentityMapper extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable, Text> {
		@Override
		public void map(LongWritable key, Text value,
				OutputCollector<LongWritable, Text> output, Reporter reporter)
				throws IOException {
			output.collect(key, value);
		}
	}

	public static class IdentityReducer extends MapReduceBase implements
			Reducer<LongWritable, Text, LongWritable, Text> {

		@Override
		public void reduce(LongWritable key, Iterator<Text> values,
				OutputCollector<LongWritable, Text> output, Reporter reporter)
				throws IOException {
			while (values.hasNext()) {
				output.collect(key, values.next());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new CreateSequenceFile(), args);
		System.exit(exitCode);
	}
}
