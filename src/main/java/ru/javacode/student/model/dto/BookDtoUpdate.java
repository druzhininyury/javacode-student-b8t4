package ru.javacode.student.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDtoUpdate {

    private Long id;

    private String title;

    private String author;

    private Integer publishingYear;

}
