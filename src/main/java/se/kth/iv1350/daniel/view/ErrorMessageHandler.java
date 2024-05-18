package se.kth.iv1350.daniel.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorMessageHandler
{
    void showErrorMsg(String msg)
    {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(createTime());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    private String createTime()
    {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
