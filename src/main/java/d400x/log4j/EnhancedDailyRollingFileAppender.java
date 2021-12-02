package d400x.log4j;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.helpers.LogLog;

public class EnhancedDailyRollingFileAppender extends DailyRollingFileAppender {

	private String timezone;

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getTimezone() {
		return timezone;
	}

	@Override
	public void activateOptions() {
		super.activateOptions();
		try {
			if(timezone != null) {
				// apply timezone to DailyRollingFileAppender#sdf
				SimpleDateFormat df = (SimpleDateFormat)getSuperField("sdf").get(this);
				df.setTimeZone(TimeZone.getTimeZone(timezone));
				LogLog.debug("timezone applied: " + df.getTimeZone().getID());

				// apply timezone to DailyRollingFileAppender#scheduledFilename
				Field f = getSuperField("scheduledFilename");
				f.set(this, fileName+df.format(new Date(new File(fileName).lastModified())));
				LogLog.debug("scheduledFileName set: " + f.get(this));
			}
		} catch(Exception e) {
			LogLog.error("failed to apply timezone: " + timezone, e);
			throw new RuntimeException(e);
		}
	}

	private Field getSuperField(String fieldname) {
		try {
			Field f = this.getClass().getSuperclass().getDeclaredField(fieldname);
			f.setAccessible(true);
			return f;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
