import java.util.regex.Matcher;

import junit.framework.Assert;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class TestImageCounter {

	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

	@Before
	public void setUp() {
		ImageCounterMapper mapper = new ImageCounterMapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
	}

	@Test
	public void testMapper() {
		String inputString = "Input line looks like: 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] \"GET /a/cat.jpg HTTP/1.1\" 200 12433 \"http://google.com\" \"Firefox/3.6.16\"";
		Matcher m = ImageCounterMapper.imagem;
		m.reset(inputString);
		Assert.assertEquals(true, m.find());
		mapDriver.withInput(new LongWritable(1), new Text(inputString));
		mapDriver.runTest();
		Assert.assertEquals(0,
				mapDriver.getCounters().findCounter("FileType", "other")
						.getCounter());
		Assert.assertEquals(0,
				mapDriver.getCounters().findCounter("FileType", "gif")
						.getCounter());
		Assert.assertEquals(1,
				mapDriver.getCounters().findCounter("FileType", "jpg")
						.getCounter());
	}
}
