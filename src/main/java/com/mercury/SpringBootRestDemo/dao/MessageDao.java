package com.mercury.SpringBootRestDemo.dao;


import com.mercury.SpringBootRestDemo.bean.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Integer> {

}