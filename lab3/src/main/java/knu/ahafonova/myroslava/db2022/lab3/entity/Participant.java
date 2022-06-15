package knu.ahafonova.myroslava.db2022.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Participant {
    private int id;
    private int country_id;
    private String name;
    private String surname;
}
