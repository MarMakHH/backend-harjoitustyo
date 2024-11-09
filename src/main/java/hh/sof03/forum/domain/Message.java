package hh.sof03.forum.domain;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageid;
    @Lob
    @NotBlank
    private String desc;
    private Instant time = Instant.now();

    @ManyToOne
    @JoinColumn(name = "id")
    private AppUser sender;

    @ManyToOne
    @JsonIgnoreProperties("messages")
    @JoinColumn(name = "topicid")
    private Topic topic;

    public Message() {
    }

    public Message(String desc, AppUser sender, Topic topic) {
        this.desc = desc;
        this.sender = sender;
        this.topic = topic;
    }

    public String getFormatedTime() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
        return formater.format(time);
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AppUser getSender() {
        return sender;
    }

    public void setSender(AppUser sender) {
        this.sender = sender;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }    

    @Override
    public String toString() {
        return "Message [messageid=" + messageid + ", desc=" + desc + ", sender=" + sender
                + "]";
    }

}
