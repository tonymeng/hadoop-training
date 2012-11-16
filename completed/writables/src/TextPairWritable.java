import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class TextPairWritable implements WritableComparable<TextPairWritable> {

	private String left, right;
	/* Need an empty constructor for serialization */
	public TextPairWritable() {
	}

	public TextPairWritable(String left, String right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(left);
		out.writeUTF(right);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		left = in.readUTF();
		right = in.readUTF();
	}

	@Override
	public int compareTo(TextPairWritable o) {
		String thisData = left + right;
		String thatData = o.getLeft() + o.getRight();
		return thisData.compareTo(thatData);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextPairWritable other = (TextPairWritable) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

}
