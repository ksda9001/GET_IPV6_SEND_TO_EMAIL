package ipget;

import jakarta.mail.MessagingException;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class SetTime {
    public void timer1(int time) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    new SendEmail().sendEmail();
                } catch (UnknownHostException | SocketException | MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, time * 3600L);
    }
}
