package com.skumar.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class SendMessageReq {
    private Integer userId;
    private Integer chatId;
    private String content;
}
