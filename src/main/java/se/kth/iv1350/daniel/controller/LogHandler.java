package se.kth.iv1350.daniel.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHandler
{
    private static final String LOG_FILE_NAME = "Retail-DB-connection-failure-log.txt";
    private final PrintWriter logFile;

    public LogHandler() throws IOException
    {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }

    /**
     * Logs the exception details to the log file.
     *
     * @param exception the exception to log
     */
    public void logException(Exception exception)
    {
        String logMsgBuilder = createTime() +
                ", Exception was thrown: " +
                exception.getMessage();
         logFile.println(logMsgBuilder);
         exception.printStackTrace(logFile);
    }

    private String createTime()
    {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
