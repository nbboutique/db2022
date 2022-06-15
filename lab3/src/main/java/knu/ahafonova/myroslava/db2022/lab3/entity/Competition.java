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
public class Competition {
    private int id;
    private int place_id;
    private int participant_id;
    private Date day;
    private String name;
}
