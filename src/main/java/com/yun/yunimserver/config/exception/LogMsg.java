package com.yun.yunimserver.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yun
 * @createdOn: 2019-07-12 17:03.
 */

@Data
public class LogMsg {
    private String msg;
    private String locMsg;
    private Long userId;
    private String para;

    private List<String> stackTrace;

    @JsonIgnore
    public void updateStack(Exception e) {
        stackTrace = new ArrayList<>();

        StackTraceElement[] stacks = e.getStackTrace();
        if (stacks == null) {
            return;
        }

        for (int i = 0; i < stacks.length; i++) {
            if (i > 10) { // 记录10个
                break;
            }
            stackTrace.add(stacks[i].toString());
        }
    }
}
