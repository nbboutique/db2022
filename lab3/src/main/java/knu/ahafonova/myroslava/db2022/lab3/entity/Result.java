package knu.ahafonova.myroslava.db2022.lab3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {
    private int competition_id;
    private int participant_id;
    private int result;
}
