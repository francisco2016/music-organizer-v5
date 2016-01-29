/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * Guarde los detalles de una pista de música,
  * Como el artista, el título y el nombre del archivo.
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    //para lleva la cuenta de las veces que se ha reproducido una canción.
    private int playCount;   //---------------------------------------------------------  0052
    //para indicar la duración de la canción.
    private int duracion;   //-----------------------------------------------------------  0053
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
        playCount = 0;      //---------------------------------------------------------  0052
        duracion = 0;        //---------------------------------------------------------  0053
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
        playCount = 0;      //---------------------------------------------------------  0052
         duracion = 0;        //---------------------------------------------------------  0053
    }
    
    /**
     * mt para asignar la duración a  una canción.
     */
    public void setTiempoCancion(int duracion){//---------------------------------------------------------  0053
        this.duracion = duracion ;
    }
    /**
     * mt para retornar la duración a una canción.
     */
    public int getTiempoCancion(){//---------------------------------------------------------  0053
        return duracion;
    }
    /**
     *  para incrementar el contador de uno en uno cada vez que se reproduce una canción.  ------------------------- 0052
     */
    public void incrementaContadorReproduciones(){
        playCount ++;
         
    }
    
    /**
     *  para poner a 0 el contador de las reproduciones de una cancion.  ------------------------- 0052
     */
    public void poneContadorEnCero(){
        playCount = 0;
     }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ") -Reproducciones " +playCount+
                            " -Duracion de la cancion. " + getTiempoCancion()+ " minutos. ";
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }
    
}
