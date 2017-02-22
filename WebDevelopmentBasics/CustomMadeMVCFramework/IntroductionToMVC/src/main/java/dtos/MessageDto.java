package dtos;

public class MessageDto {
    private String senderEmail;
    private String subject;
    private String message;

    public MessageDto() {
    }

    public MessageDto(String senderEmail, String subject, String message) {
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.message = message;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
