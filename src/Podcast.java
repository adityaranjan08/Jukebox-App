import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Podcast {
    String podcastId;
    String podcastName;
    String artistName;

    PreparedStatement ppdStmt;
    Connection conn;



    public Podcast(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxFinal", "root", "Adityaa8@#");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public Podcast(String podcastId, String podcastName, String artistName) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return String.format("%25s%25s%25s",podcastId,podcastName,artistName);
    }

    public List<Podcast>searchByArtist(String artistName){
        List<Podcast> podcast = new ArrayList<Podcast>();
        List<Podcast> newPodcast = new ArrayList<Podcast>();
        try{
            ppdStmt = conn.prepareStatement("select * from podcast");
            ResultSet resultSet = ppdStmt.executeQuery();
            while(resultSet.next()){
                podcast.add(new Podcast(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
            newPodcast = podcast.stream().filter(p->p.artistName.toLowerCase().contains(artistName.toLowerCase())).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newPodcast;
    }

    public void createPodcast(String podcastId,String podcastName,String artistName) throws SQLException {
        ppdStmt = conn.prepareStatement("insert into podcast values(?,?,?)");
        ppdStmt.setString(1,podcastId);
        ppdStmt.setString(2,podcastName);
        ppdStmt.setString(3,artistName);
        int row = ppdStmt.executeUpdate();
        if(row>0) System.out.println("Values Inserted");
        else System.out.println("Error!");

        ppdStmt = conn.prepareStatement("select * from Podcast");
        ResultSet resultSet = ppdStmt.executeQuery();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        for(int index=1;index<=columnCount;index++){
            System.out.printf("%25s",resultSetMetaData.getColumnName(index));
        }
        System.out.println();

        while(resultSet.next()){
            for(int index=1;index<=columnCount;index++){
                System.out.printf("%25s",resultSet.getString(index));
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Podcast obj = new Podcast();
        System.out.println("Enter Podcast Id::");
        String podcastId = scanner.nextLine();
        System.out.println("Enter Podcast name::");
        String podcastName = scanner.nextLine();
        System.out.println("Enter artist name::");
        String artistName = scanner.nextLine();
        System.out.println();

        obj.createPodcast(podcastId,podcastName,artistName);
    }

}
