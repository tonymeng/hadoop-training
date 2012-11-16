import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class WordCount { 

    public static void main(String[] args) throws IOException { 
        if (args.length != 2) { 
        System.err.println("Usage: WordCount <input dir> <output dir>"); 
        System.exit(-1); 
        } 
    
        JobConf conf = new JobConf(WordCount.class); 
        conf.setJobName("Word Count"); 
    
        FileInputFormat.setInputPaths(conf, new Path(args[0])); 
        FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
//    
//        conf.set("mapred.mapper.regex", "\\W+");
//        conf.set("mapred.mapper.regex.group", "0");
//        conf.setMapperClass(RegexMapper.class); 
//        conf.setMapOutputKeyClass(Text.class); 
//        conf.setMapOutputValueClass(LongWritable.class);
//        
//        conf.setCombinerClass(LongSumReducer.class);
//        
//        conf.setReducerClass(LongSumReducer.class);      
//        conf.setOutputKeyClass(Text.class); 
//        conf.setOutputValueClass(LongWritable.class); 
    
        conf.setMapperClass(WordMapper.class);
		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(LongWritable.class);
		
        conf.setCombinerClass(SumReducer.class);
        
        conf.setReducerClass(SumReducer.class);
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(LongWritable.class);
        JobClient.runJob(conf); 
    } 
} 

