package notes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.Objects;

@JsonAutoDetect
public class HashTag {
    public static HashTag hashTag;
    public String string;

    public HashTag() {

    }

    public HashTag(String string) {
        this.string = string;
    }

    public static HashTag getHashTag(String string) {
        String hashTagString = checkSharp(string) ? string : ("#" + string);
        hashTag = new HashTag(hashTagString);
        return hashTag;
    }

    public static void setHashTag(HashTag hashTag) {
        HashTag.hashTag = hashTag;
    }

    private static boolean checkSharp(String string) {
        return string.substring(0, 1).equals("#");
    }

    public static void printHashtags(List<HashTag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            System.out.print(tags.get(i).getString() + ", ");
        }
        System.out.println(tags.get(tags.size() - 1).getString());
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return Objects.equals(string, hashTag.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
