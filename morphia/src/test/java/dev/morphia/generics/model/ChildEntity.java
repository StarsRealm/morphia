package dev.morphia.generics.model;

import dev.morphia.annotations.Entity;

@Entity(value = "children")
public class ChildEntity extends FatherEntity<Child> {

}
