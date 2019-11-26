package by.dorozhko.poputka.entity;

public enum  Gender {
    FEMALE("жен."),
    MALE("муж.");

    private String name;

    private Gender(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return ordinal();
    }

    public static Gender getById(final int id) {
        return Gender.values()[id];
    }
}
