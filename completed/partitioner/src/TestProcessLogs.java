import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.apache.hadoop.thirdparty.guava.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class TestProcessLogs {
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

	@Before
	public void setUp() {
		LogFileMapper mapper = new LogFileMapper();
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
		mapDriver.withInput(new LongWritable(1), new Text("96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapDriver.withOutput(new Text("96.7.4.14:Apr"), new IntWritable(1));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() {
		reduceDriver.withInput(new Text("96.7.4.14:Apr"), Lists.newArrayList(new IntWritable(1), new IntWritable(1), new IntWritable(1)));
		reduceDriver.withOutput(new Text("96.7.4.14"), new IntWritable(3));
		reduceDriver.runTest();
	}

	@Test
	public void testMapReduce() {
		mapReduceDriver.withInput(new LongWritable(1), new Text("96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapReduceDriver.withInput(new LongWritable(1), new Text("96.7.4.15 - - [24/Apr/2011:04:20:11 -0400] \"GET /cat.jpg HTTP/1.1\" 200 12433"));
		mapReduceDriver.withOutput(new Text("96.7.4.14"), new IntWritable(4));
		mapReduceDriver.withOutput(new Text("96.7.4.15"), new IntWritable(1));
		mapReduceDriver.runTest();
	}

	@Test
	public void testPartitioner() {
		MonthPartitioner mp = new MonthPartitioner();
		int numReducers = 12;
		Assert.assertEquals(0, mp.getPartition(new Text("96.7.4.14:Jan"), null, numReducers));
		Assert.assertEquals(1, mp.getPartition(new Text("96.7.4.14:Feb"), null, numReducers));
		Assert.assertEquals(2, mp.getPartition(new Text("96.7.4.14:Mar"), null, numReducers));
		Assert.assertEquals(3, mp.getPartition(new Text("96.7.4.14:Apr"), null, numReducers));
		Assert.assertEquals(4, mp.getPartition(new Text("96.7.4.14:May"), null, numReducers));
		Assert.assertEquals(5, mp.getPartition(new Text("96.7.4.14:Jun"), null, numReducers));
		Assert.assertEquals(6, mp.getPartition(new Text("96.7.4.14:Jul"), null, numReducers));
		Assert.assertEquals(7, mp.getPartition(new Text("96.7.4.14:Aug"), null, numReducers));
		Assert.assertEquals(8, mp.getPartition(new Text("96.7.4.14:Sep"), null, numReducers));
		Assert.assertEquals(9, mp.getPartition(new Text("96.7.4.14:Oct"), null, numReducers));
		Assert.assertEquals(10, mp.getPartition(new Text("96.7.4.14:Nov"), null, numReducers));
		Assert.assertEquals(11, mp.getPartition(new Text("96.7.4.14:Dec"), null, numReducers));
	}
}
