import java.io.IOException;

import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class StubDriver {

  public static int main(String[] args) throws IOException {
    if(args.length != 2) {
      throw new IllegalArgumentException("Bad number of arguments: "
          + args.length);
    }
    String input = args[0];
    String output = args[1];
    
    JobConf conf = new JobConf(StubDriver.class); // TODO change
    conf.setJobName("Your job name here");

    // TODO implement
    
    JobClient.runJob(conf);
    return 0;
  }

 
}