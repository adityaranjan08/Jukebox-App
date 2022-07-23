import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class SongImpl {

   Connection conn;
   PreparedStatement ppdStmt;

   public SongImpl(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxFinal", "root", "Adityaa8@#");
       }catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
       }
   }

   public List<Song> searchSongByName(String songName) throws SQLException {
       List<Song> songlist = new ArrayList<Song>();
       List<Song> newSongs = new ArrayList<Song>();
       try {
           ppdStmt = conn.prepareStatement("select * from Songs");
           ResultSet resultSet = ppdStmt.executeQuery();
           while (resultSet.next()) {
               songlist.add(new Song(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
           }
           newSongs = songlist.stream().filter(p -> p.songName.toLowerCase().contains(songName.toLowerCase())).collect(Collectors.toList());

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return newSongs;

   }


    public List<Song> searchSongByGenre(String genre) throws SQLException {
        List<Song> songlist = new ArrayList<Song>();
        List<Song> newSongs = new ArrayList<Song>();
        try {
            ppdStmt = conn.prepareStatement("select * from Songs");
            ResultSet resultSet = ppdStmt.executeQuery();
            while (resultSet.next()) {
                songlist.add(new Song(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
            newSongs = songlist.stream().filter(p -> p.genre.toLowerCase().contains(genre.toLowerCase())).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newSongs;
   }

    public List<Song> searchSongByArtist(String artist) throws SQLException {
        List<Song> songlist = new ArrayList<Song>();
        List<Song> newSongs = new ArrayList<Song>();
        try {
            ppdStmt = conn.prepareStatement("select * from Songs");
            ResultSet resultSet = ppdStmt.executeQuery();
            while (resultSet.next()) {
                songlist.add(new Song(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
            newSongs = songlist.stream().filter(p -> p.artist.toLowerCase().contains(artist.toLowerCase())).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newSongs;

    }


    public List<Song> searchSongByAlbum(String album) throws SQLException {
        List<Song> songlist = new ArrayList<Song>();
        List<Song> newSongs = new ArrayList<Song>();
        try {
            ppdStmt = conn.prepareStatement("select * from Songs");
            ResultSet resultSet = ppdStmt.executeQuery();
            while (resultSet.next()) {
                songlist.add(new Song(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
            newSongs = songlist.stream().filter(p -> p.album.toLowerCase().contains(album.toLowerCase())).collect(Collectors.toList());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newSongs;
   }

    public void displayAllSong(){
        List<Song> songlist = new ArrayList<Song>();
        List<Song> newSongs = new ArrayList<Song>();
        try {
            ppdStmt = conn.prepareStatement("select * from Songs");
            ResultSet resultSet = ppdStmt.executeQuery();
            while (resultSet.next()) {
                songlist.add(new Song(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
            newSongs = songlist.stream().collect(Collectors.toList());
            newSongs.stream().forEach(p->System.out.println(p));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void columnDisplay(String data) {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + data + "");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            for (int index = 1; index <= columnCount; index++) {
                System.out.printf("%25s", resultSetMetaData.getColumnName(index));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}



