import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.apache.hadoop.thirdparty.guava.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

public class TestWordCount {
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

	@Before
	public void setUp() {
		WordMapper mapper = new WordMapper();
		SumReducer reducer = new SumReducer();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
		reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		reduceDriver.setReducer(reducer);
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapper() {
		mapDriver.withInput(new LongWritable(1), new Text("cat"));
		mapDriver.withOutput(new Text("cat"), new IntWritable(1));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() {
		reduceDriver.withInput(new Text("dog"), Lists.newArrayList(new IntWritable(1), new IntWritable(1), new IntWritable(1)));
		reduceDriver.withOutput(new Text("dog"), new IntWritable(3));
		reduceDriver.runTest();
	}

	@Test
	public void testMapReduce() {
		mapReduceDriver.withInput(new LongWritable(1), new Text("dog"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("dog"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("dog"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("dog"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("cat"));
		mapReduceDriver.withOutput(new Text("cat"), new IntWritable(1));
		mapReduceDriver.withOutput(new Text("dog"), new IntWritable(4));
		mapReduceDriver.runTest();
	}

}
