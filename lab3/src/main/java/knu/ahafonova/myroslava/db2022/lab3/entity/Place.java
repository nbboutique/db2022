package knu.ahafonova.myroslava.db2022.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Place {
    private int id;
    private String name;
    private int country_id;
}
