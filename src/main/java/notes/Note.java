package notes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonAutoDetect
public class Note {
    public String text;
    public Date date;
    public List<HashTag> hashTagList;
    public String title;

    public Note(){}

    public Note(String text, Date date, List<HashTag> hashTagList, String title) {
        this.text = text;
        this.date = date;
        this.hashTagList = hashTagList;
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public List<HashTag> getHashTagList() {
        return hashTagList;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHashTagList(List<HashTag> hashTagList) {
        this.hashTagList = hashTagList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return text.equals(note.text) &&
                date.equals(note.date) &&
                Objects.equals(hashTagList, note.hashTagList) &&
                Objects.equals(title, note.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, date, hashTagList, title);
    }
}
