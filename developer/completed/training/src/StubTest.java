

import static org.junit.Assert.fail;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.apache.hadoop.mrunit.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class StubTest {
  MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
  MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
  ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

  @Before
  public void setUp() {
    // TODO implement
//    mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
//    mapDriver.setMapper(mapper);
//    reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
//    reduceDriver.setReducer(reducer);
//    mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
//    mapReduceDriver.setMapper(mapper);
//    mapReduceDriver.setReducer(reducer);
  }

  @Test
  public void testMapper() {
    fail("Please implement test");
  }

  @Test
  public void testReducer() {
    fail("Please implement test");
  }

  @Test
  public void testMapReduce() {
    fail("Please implement test");
  }

}
