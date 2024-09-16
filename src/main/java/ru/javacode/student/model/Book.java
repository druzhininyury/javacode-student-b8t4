package ru.javacode.student.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Long id;

    private String title;

    private String author;

    private Integer publishingYear;

}
