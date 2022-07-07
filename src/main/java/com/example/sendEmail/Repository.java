package com.example.sendEmail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<UserDB,Integer> {
}
