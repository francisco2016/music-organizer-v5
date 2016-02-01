import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;       //----------------------------------------------------------------------- 0057
import java.util.Collections;  //----------------------------------------------------------------------- 0057
/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * Una clase para mantener los detalles de las pistas de audio.
  * Pistas individuales pueden ser reproducidos.
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    //para señalar si un track se está reproduciendo.
    private boolean reproduciendose;   //---------------------------------------------------------------------------0054

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String lugarDeArchivos)// parametro ======== 0057
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(lugarDeArchivos);  //sustituido "audio" por: lugarDeArchivos   ------------------------ 0057
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        reproduciendose = false;     //----------------------------------------------------------------- 0054
    }

    /**
     * permite reproducir los primeros segundos de cada canción en orden aleatorio y cumple los siguientes requisitos:
     *Cada canción debe reproducirse una única vez y deben reproducirse todas las canciones.
     *Los contadores de reproducción deben actualizarse correctamente.
     *Debe mostrar por pantalla los detalles de la canción que está sonando en este momento. -------------------- 0057
     */
    public void playSuffle(){   // ------------------------------------------------------------------------------ 0057
        Collections.shuffle(tracks);
        for(Track track : tracks){
            track.incrementaContadorReproduciones();
            System.out.println("Canción de. " +track.getDetails());
            player.playSample(track.getFilename());
        }

    }

    /*** reproduzce una de las canciones al azar del organizador     ---------------------------------------------- 0057
     */
    public void playRandom(){
        Random numAzar = new Random();   //creo el objeto Random.
        int numeroAzar = numAzar.nextInt(tracks.size()); //le aplico el mt.nextInt() y guardo el valor en una VL.
        playTrack(numeroAzar);    //invoco al mt. de reproducir track, con parámetro el nº creado por el Random.
    }

    /**
     * permita eliminar del organizador los tracks que contengan un determinado artista usando un iterador
     */
    public void removeByArtist(String nombreAr){  //------------------------------------------------- 0055
        Iterator<Track> pistas = tracks.iterator();
        while(pistas.hasNext()){
            Track mostrarPistas = pistas.next();
            if(mostrarPistas.getArtist().contains(nombreAr)){
                pistas.remove();
            }
        }
    }

    /**
     * que permita eliminar del organizador los tracks que contengan una determinada cadena 
     * en el título de la canción 
     */
    public void removeByTitle(String titulo){  //------------------------------------------------- 0055
        Iterator<Track> pistas = tracks.iterator();
        while(pistas.hasNext()){
            Track mostrarPistas = pistas.next();
            if(mostrarPistas.getTitle().contains(titulo)){
                pistas.remove();
            }
        }
    }

    /**
     * muestra los detalles de todos los tracks almacenados en un organizador usando un iterador.   ------------------- 0055
     */
    public void listAllTrackWithIterator(){
        Iterator<Track> pistas = tracks.iterator();
        while(pistas.hasNext()){
            Track mostrarPistas = pistas.next();
            System.out.println(mostrarPistas.getDetails());

        }

    }

    /**
     *  cuando es invocado informa por pantalla de si en este momento se está reproduciendo un track completo o si no  
     */
    public void isPlayin(){        //----------------------------------------------------------------------- 0054
        if(reproduciendose == true){
            System.out.println("Error ya tenemos un track reproduciendose.");
        }
        else{
            System.out.println("No tenemos track reproduciendose.");
        }
    }

    /**
     * mt para poder fijar a un track su duración. 
     * dos parámetros, uno para elegir la posición del track, el otro para asignar la duracion.
     */
    public void setDuracion(int index,int tiempo){     //------------------------------------------------------------------ 0053
        if(index >= 0 && index < tracks.size()){
            tracks.get(index).setTiempoCancion(tiempo);
        }
    }

    /**
     * añade un método a la clase MusicOrganizer llamado findInTitle que tome un único parámetro de tipo String y muestre
     * por pantalla la información de los tracks que contienen dicha cadena en el título de la canción. ------------ 0052
     */
    public void findInTitle(String informaciónDeTracks){
        for(Track datos : tracks){            //--------------------------------------------------------------------- 0052
            if(datos.getTitle().contains(informaciónDeTracks)){
                System.out.println(datos.getDetails());
            }
        }
    }

    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }

    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(indexValid(index) && reproduciendose == false) {
            Track track = tracks.get(index);
            track.incrementaContadorReproduciones();  //----------------------- 0052 para incrementar el nº de reproducciones
            reproduciendose = true;     // --------------------------------------- 0054
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle ());
        }
        else{
            System.out.println("Error ya hay una reproducción en curso ");
        }

    }

    /**
     * Return the number of tracks in the collection.T
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }

    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }

    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(tracks.size() > 0 && reproduciendose == false) {
            tracks.get(0).incrementaContadorReproduciones();//-para que se incremente la reproducción que está en 0------0052
            // le aplicamos el mt. incre... sobre el trac 0 "tracks.get(0)"
            player.startPlaying(tracks.get(0).getFilename());
            reproduciendose = true;     // --------------------------------------- 0054
        }
        else{  // --------------------------------------------------------------------------------- 0054
            System.out.println("Error ya hay una reproducción en curso ");
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        reproduciendose = false;    //-------------------------------------------------------------------- 0054
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }

    
}











