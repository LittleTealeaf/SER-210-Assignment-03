package edu.quinnipiac.ser210.harrypottercharacters.data;

public enum Category {
    GRIFFYNDOR("Griffyndor","characters","house","gryffindor"),
    HUFFLEPUFF("Hufflepuff","characters","house","hufflepuff"),
    SLYTHERIN("Slytherin","characters","house","slytherin"),
    RAVENCLAW("Ravenclaw","characters","house","ravenclaw"),
    PROFESSORS("Professors","characters","staff"),
    ALL("All","characters");

    final String display;
    final String[] endpoint;

    Category(String display, String... endpoint) {
        this.display = display;
        this.endpoint = endpoint;
    }

    public String[] getEndpoint() {
        return endpoint;
    }

    public String toString() {
        return display;
    }
}
