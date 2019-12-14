package by.dorozhko.poputka.entity;

import java.util.Objects;

public class ErrorMessageConficurator {
    private String exceptionMessage;
    private String attribute;
    private String messageForUser;

    public ErrorMessageConficurator() {
    }

    public ErrorMessageConficurator(final String newExceptionMessage,
                                    final String newAttribute,
                                    final String newMessageForUser) {
        exceptionMessage = newExceptionMessage;
        attribute = newAttribute;
        messageForUser = newMessageForUser;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }


    public String getAttribute() {
        return attribute;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessageConficurator that = (ErrorMessageConficurator) o;
        return Objects.equals(exceptionMessage, that.exceptionMessage) &&
                Objects.equals(attribute, that.attribute) &&
                Objects.equals(messageForUser, that.messageForUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptionMessage, attribute, messageForUser);
    }
}
