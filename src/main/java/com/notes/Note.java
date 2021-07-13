package com.notes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * A class describing the Note. contains public fields, public getters and public setters for
 * marshaling in json format
 */

@JsonAutoDetect
public class Note {
    public long id;
    public String text;
    public Date date;
    public Set<String> hashTagSet;
    public String title;

    public Note() {
    }

    public Note(String text, Date date, Set<String> hashTagSet, String title) {
        this.id = date.getTime();
        this.text = text;
        this.date = date;
        this.hashTagSet = hashTagSet;
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<String> getHashTagSet() {
        return hashTagSet;
    }

    public void setHashTagSet(Set<String> hashTagSet) {
        this.hashTagSet = hashTagSet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = getDate().getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                text.equals(note.text) &&
                date.equals(note.date) &&
                Objects.equals(hashTagSet, note.hashTagSet) &&
                Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date, hashTagSet, title);
    }
}
