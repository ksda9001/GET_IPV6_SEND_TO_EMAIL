package ipget;

import lombok.Data;

import java.io.File;
@Data
public class EmailEntity {
    enum EmailFormat {
        TEXT, HTML
    }

    /**
     * 发件人
     */
    private String from;

    /**
     * 收件人
     */
    private String[] to;

    /**
     * 抄送人
     */
    private String[] cc;

    /**
     * 密送人
     */
    private String[] bcc;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String body;

    /**
     * 邮件格式
     */
    private EmailFormat format;

    /**
     * 附件
     */
    private File[] files;

}
