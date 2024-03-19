import java.util.List;

interface Ilayout {

    /**
     @return the children of the receiver.
     */
    List<Ilayout> children();

    boolean isGoal(int h);
    /**
     @return the cost for moving from the input config to the receiver.
     */
    double getH();


}