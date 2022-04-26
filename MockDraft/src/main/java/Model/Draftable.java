package Model;

import java.util.ArrayList;

public interface Draftable {

    public void drafted(ArrayList<Prospect> prospects, ArrayList<Pick> draftOrder, String pick);
}
