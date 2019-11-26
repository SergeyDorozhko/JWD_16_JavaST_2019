package by.dorozhko.poputka.entity;

public enum Role {
    ADMINISTRATOR("администратор"),
    USER("пользователь"),
    GUEST("гость");

    private String name;

    private Role(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return ordinal();
    }

    public static Role getById(final int id) {
        return Role.values()[id];
    }
}
