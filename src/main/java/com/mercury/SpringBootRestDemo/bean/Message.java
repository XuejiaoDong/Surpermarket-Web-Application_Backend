package com.mercury.SpringBootRestDemo.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @Column
    private Integer userId;

    @Column
    private String message;

    @Column
    private String answer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    private User user;

    public Message() {
    }

    public Message(Integer messageId, Integer userId, String message, String answer, User user) {
        this.messageId = messageId;
        this.userId = userId;
        this.message = message;
        this.answer = answer;
        this.user = user;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", answer='" + answer + '\'' +
                ", user=" + user +
                '}';
    }
}
