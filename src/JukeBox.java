import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JukeBox {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SongImpl obj = new SongImpl();
        Podcast podcastObj = new Podcast();
        PodcastDetails podcastDetails = new PodcastDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::Welcome to JukeBox:::::::::::::::::::::::::::::");

        System.out.println("Enter User Name::");
        String userName = scanner.next();
        System.out.println("Enter Password::");
        String password = scanner.next();
        Users user = new Users();
        user.checkUser(userName,password);
        if(user.checkUser(userName,password)){
            System.out.println("Succesfully LoggedIn");
            System.out.println("Welcome "+userName);
        }else System.out.println("Invalid user");
        System.out.println("*****************************************************************************************");
        System.out.println("*************************** Songs in your Catalouge *************************************");
        System.out.println("*****************************************************************************************");

        obj.columnDisplay("Songs");
        System.out.println();
        obj.displayAllSong();

        System.out.println("What You want to do::::  \tPress 1 Search the Songs from the Catalouge::\t Press 2 for search in Podcast::");
        int press = scanner.nextInt();
        if(press==1){
            System.out.println("Enter choice for Search:: \t1.By Name \t2.By Genre \t3.By Artist \t4.By Album");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Enter Song Name: ");
                    String songName = scanner.next();
                    obj.columnDisplay("Songs");
                    System.out.println();
                    List<Song> song1 = new ArrayList<Song>();
                    song1 = obj.searchSongByName(songName);
                    Iterator<Song> songByName = song1.iterator();
                    while (songByName.hasNext()) {
                        System.out.println(songByName.next());
                    }
                    break;
                case 2:
                    System.out.println("Enter Song Genre: ");
                    String genre = scanner.next();
                    System.out.println(genre);
                    obj.columnDisplay("Songs");
                    System.out.println();
                    List<Song> song2 = new ArrayList<Song>();
                    song2 = obj.searchSongByGenre(genre);
                    Iterator<Song> songByGenre = song2.iterator();
                    while (songByGenre.hasNext()) {
                        System.out.println(songByGenre.next());
                    }
                    break;

                case 3:
                    System.out.println("Enter Song Artist: ");
                    String artist = scanner.next();
                    obj.columnDisplay("Songs");
                    System.out.println();
                    List<Song> song3 = new ArrayList<Song>();
                    song3 = obj.searchSongByArtist(artist);
                    Iterator<Song> songByArtist = song3.iterator();
                    while (songByArtist.hasNext()) {
                        System.out.println(songByArtist.next());
                    }
                    break;
                case 4:
                    System.out.println("Enter Song Album: ");
                    String album = scanner.next();
                    obj.columnDisplay("Songs");
                    System.out.println();
                    List<Song> song4 = new ArrayList<Song>();
                    song4 = obj.searchSongByArtist(album);
                    Iterator<Song> songByAlbum = song4.iterator();
                    while (songByAlbum.hasNext()) {
                        System.out.println(songByAlbum.next());
                    }
                    break;


                default:
                    System.out.println("Invalid Selection!");
            }

        }else if(press==2){
            System.out.println("Enter choice for Search:: \t1.By Artist \t2.By Date");
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("Enter artist Name: ");
                    String artistName = scanner.next();
                    obj.columnDisplay("Podcast");
                    System.out.println();
                    List<Podcast> podcasts = podcastObj.searchByArtist(artistName);
                    Iterator<Podcast> podcastByArtist = podcasts.iterator();
                    while (podcastByArtist.hasNext()) {
                        System.out.println(podcastByArtist.next());
                    }
                    break;

                case 2:
                    System.out.println("Enter Date: ");
                    String StrDate = scanner.next();
                    obj.columnDisplay("podcastDetail");
                    System.out.println();
                    Date date = Date.valueOf(StrDate);
                    List<PodcastDetails> podcastDetails1 = podcastDetails.searchPodcastByDate(date);
                    podcastDetails1.stream().forEach(p->System.out.println(p));
            }
        }


    }
}

