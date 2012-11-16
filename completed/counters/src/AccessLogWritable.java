import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Writable;


public class AccessLogWritable implements Writable {

	/*
	 * Input line looks like: 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400]
	 * "GET /cat.jpg HTTP/1.1" 200 12433 "http://google.com" "Firefox/3.6.16"
	 */
	private String ip;
	private Date date;
	private String type; // GET/POST
	private String url; // cat.jpg
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(ip);
		out.writeLong(date.getTime());
		out.writeUTF(type);
		out.writeUTF(url);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		ip = in.readUTF();
		date = new Date(in.readLong());
		type = in.readUTF();
		url = in.readUTF();
	}

	public String getIp() {
		return ip;
	}

	public Date getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

}
