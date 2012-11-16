import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

public class ImageCounter extends Configured implements Tool {

	private static final Logger logger = Logger.getLogger(ImageCounter.class);
	@Override
	public int run(String[] args) throws Exception {

		String input, output;
		if (args.length == 2) {
			input = args[0];
			output = args[1];
		} else {
			input = "weblog";
			output = "weblogcounter";
		}

		JobConf conf = new JobConf(getConf(), ImageCounter.class);
		conf.setJobName(this.getClass().getName());

		FileInputFormat.setInputPaths(conf, new Path(input));
		FileOutputFormat.setOutputPath(conf, new Path(output));

		conf.setMapperClass(ImageCounterMapper.class);

		conf.setNumReduceTasks(0); // map only job

		RunningJob job = JobClient.runJob(conf);
		long jpgCounter = job.getCounters().findCounter("FileType", "jpg")
				.getCounter();
		long gifCounter = job.getCounters().findCounter("FileType", "gif")
				.getCounter();
		long otherCounter = job.getCounters().findCounter("FileType", "other")
				.getCounter();

		logger.info("FileType Counters");
		logger.info("FileType JPG: " + jpgCounter);
		logger.info("FileType GIF: " + gifCounter);
		logger.info("FileType Other: " + otherCounter);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new ImageCounter(), args);
		System.exit(exitCode);
	}
}
