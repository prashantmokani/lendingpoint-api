package com.prashant.lendingpoint.api.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String subject;
    private String from;
    @Builder.Default
    private List<String> to = new ArrayList<>();
    @Builder.Default
    private List<String> cc = new ArrayList<>();
    @Builder.Default
    private List<String> bcc = new ArrayList<>();
    private Map<String, Object> model;

}
