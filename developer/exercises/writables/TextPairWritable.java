import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPairWritable implements WritableComparable<TextPairWritable> {

  /* Need an empty constructor for serialization */
  public TextPairWritable() {
  }

  public TextPairWritable(String left, String right) {
    // TODO implement
  }

  @Override
  public void write(DataOutput out) throws IOException {
    // TODO implement
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    // TODO implement
  }

  @Override
  public int compareTo(TextPairWritable o) {
    // TODO implement
    return 0;
  }

  // TODO implement equals and hashCode
  /*
   * Both can be implemented by Eclipse. Right click -> Source
   * -> Generate equals/hashCode
   */

}
