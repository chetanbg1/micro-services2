package com.gfarm.openfeign.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Entity {

    private String userId;
    private String id;
    private String title;
    private String body;
}
