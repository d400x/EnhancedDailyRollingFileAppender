# EnhancedDailyRollingFileAppender
Timezone enhancement for Log4j 1.2 DailyRollingFileAppender

apply timezone for file-rolling timestamp.

- sample log4j.properties

```Java Properties
# EnhancedDailyRollingFileAppender and "timezone" property enables FileRolling based on specified timezone
log4j.appender.utcfile=d400x.log4j.EnhancedDailyRollingFileAppender
log4j.appender.utcfile.layout=org.apache.log4j.EnhancedPatternLayout
log4j.appender.utcfile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss.SSS}{GMT+0} %-5p %c{1} - %m%n
log4j.appender.utcfile.timezone=UTC
log4j.appender.utcfile.File=logs/test.log
log4j.appender.utcfile.DatePattern='.'yyyy-MM-dd_HH
```
