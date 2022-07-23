import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PodcastDetails {
    int podcastDetailId;
    String podcastId;
    int episodeNo;
    String episodeName;
    String episodeDuration;
    Date episodeDate;

    PreparedStatement ppdStmt;
    Connection conn;

    public PodcastDetails(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeboxFinal", "root", "Adityaa8@#");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public PodcastDetails(int podcastDetailId, String podcastId, int episodeNo, String episodeName, String episodeDuration, Date episodeDate) {
        this.podcastDetailId = podcastDetailId;
        this.podcastId = podcastId;
        this.episodeNo = episodeNo;
        this.episodeName = episodeName;
        this.episodeDuration = episodeDuration;
        this.episodeDate = episodeDate;
    }

    public List<PodcastDetails>searchPodcastByDate(Date Date){
        List<PodcastDetails> podcastDetails = new ArrayList<PodcastDetails>();
        List<PodcastDetails> newPodcastDetails = new ArrayList<PodcastDetails>();
        try{
            ppdStmt = conn.prepareStatement("select * from podcastDetail");
            ResultSet resultSet = ppdStmt.executeQuery();
            while(resultSet.next()){
                podcastDetails.add(new PodcastDetails(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4),resultSet.getString(5), resultSet.getDate(6)));
            }
            newPodcastDetails = podcastDetails.stream().filter(p->p.episodeDate.equals(Date)).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return newPodcastDetails;
    }


    @Override
    public String toString() {
        return String.format("%25s%25s%25s%25s%25s%25s",podcastDetailId,podcastId,episodeNo,episodeName,episodeDuration,episodeDate);
    }


}
