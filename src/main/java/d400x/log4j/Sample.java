package d400x.log4j;

import org.apache.log4j.Logger;

public class Sample {
	static final Logger logger = Logger.getLogger(Sample.class);

	public static void main(String... args) {
		logger.info("test message info");
		logger.warn("test message warn");
	}
}
