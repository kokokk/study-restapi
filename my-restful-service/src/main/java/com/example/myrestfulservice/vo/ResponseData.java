package com.example.myrestfulservice.vo;

import com.example.myrestfulservice.bean.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData {
    private int count;
    private List<User> users;

}
