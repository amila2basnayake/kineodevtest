package com.e3.test.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.StringJoiner;

public class ApiValidationError extends ApiSubError implements Serializable {
    private static final long serialVersionUID = 1781211130961571205L;
    @JsonIgnore
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiValidationError that = (ApiValidationError) o;

        if (object != null ? !object.equals(that.object) : that.object != null) return false;
        if (!field.equals(that.field)) return false;
        if (!rejectedValue.equals(that.rejectedValue)) return false;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        int result = object != null ? object.hashCode() : 0;
        result = 31 * result + field.hashCode();
        result = 31 * result + rejectedValue.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiValidationError.class.getSimpleName() + "[", "]")
                .add("object='" + object + "'")
                .add("field='" + field + "'")
                .add("rejectedValue=" + rejectedValue)
                .add("message='" + message + "'")
                .toString();
    }
}

