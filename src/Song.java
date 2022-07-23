public class Song {
    String songId;
    String songName;
    String artist;
    String album;
    String genre;
    String duration;

    public Song(String songId, String songName, String artist, String album, String genre, String duration) {
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%25s%25s%25s%25s%25s%25s", songId, songName, artist, album, genre,duration);
    }
}
