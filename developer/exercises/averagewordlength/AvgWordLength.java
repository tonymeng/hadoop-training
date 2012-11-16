import java.io.IOException;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class AvgWordLength {

  public static void main(String[] args) throws IOException {

    String input, output;
    if(args.length == 2) {
      input = args[0];
      output = args[1];
    } else {
      input = "your-input-dir";
      output = "your-output-dir";
    }

    JobConf conf = new JobConf(AvgWordLength.class);
    conf.setJobName("Average Word Length");

    // TODO implement

    JobClient.runJob(conf);
    return;
  }
}
