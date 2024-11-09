package hh.sof03.forum.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicid;
    @Size(min = 3)
    private String header;

    @ManyToOne
    @JsonIgnoreProperties("categories")
    @JoinColumn(name = "categoryid")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private List<Message> messages;

    public Topic() {
    }

    public Topic(String header, Category category) {
        this.header = header;
        this.category = category;
    }

    public String getLastMessageTime() {
        String result = "No messages";
        if (messages.size() > 0) {
            Instant messageTime = messages.get(messages.size() - 1).getTime();
            Instant timeNow = Instant.now();
            Duration duration = Duration.between(messageTime, timeNow);
            result = "Now";
            if (duration.toDays() > 0) {
                result = duration.toDays() + "d";
            } else if (duration.toHours() > 0) {
                result = duration.toHours() + "h";
            } else if (duration.toMinutes() > 0) {
                result = duration.toMinutes() + "m";
            }
        }
        return result;
    }

    public Long getTopicid() {
        return topicid;
    }

    public void setTopicid(Long topicid) {
        this.topicid = topicid;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Topic [topicid=" + topicid + ", header=" + header + "]";
    }

}
