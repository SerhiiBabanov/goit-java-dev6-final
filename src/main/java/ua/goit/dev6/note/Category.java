package ua.goit.dev6.note;

public enum Category {
    DEFAULT("Default"),
    TODO("ToDo"),
    URGENT("Urgent"),
    PERSONAL("Personal"),
    IMPORTANT("Important");
    final String name;


    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
